<template>
    <v-container class="container-size">
        <!-- <v-row> -->
        <v-col class="cng-name">
            <span class="d-flex justify-center align-center mb-3 title-size">
                {{ CngInfo.title }}
            </span>
            <span class="d-flex justify-center">
                <v-chip class="white--text" :color="color">
                    {{ this.category }}
                </v-chip>
            </span>
        </v-col>
        <!-- </v-row> -->
        <v-list-item class="border-list">
            <v-list-item-avatar size="55">
                <v-img :src="UserInfo.img"></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
                <v-list-item-title>
                    <span class="nickname-bold" style="font-size:15px; color:black;"> {{ UserInfo.nickname }}</span>
                </v-list-item-title>
            </v-list-item-content>
        </v-list-item>

        <div class="insert">
            <v-row class="img">
                <CertificationImg @transferCertImg="receiveCertImg" class="mb-5" />
            </v-row>

            <v-row>
                <v-textarea color="#AED864" no-resize v-model="certForm.content" outlined rows="10" label="설명글"
                    placeholder="자유롭게 인증에 대한 설명을 넣어주세요">
                </v-textarea>
            </v-row>

            <v-row class="cancel-done-btn">
                <router-link to="/">
                    <v-btn class="ml-2 mr-2" outlined>
                        취소
                    </v-btn>
                </router-link>

                <v-btn color="#AED864" class="create-btn" @click="writeCert">
                    생성
                </v-btn>
            </v-row>
        </div>

    </v-container>
</template>
<script>
    // import AWS from 'aws-sdk'
    import CertificationImg from "@/views/Certification/components/CertificationImg.vue"

    import {
        mapState
    } from "vuex";

    import axios from "axios"

    export default {

        name: 'CertificationInsert',
        components: {
            CertificationImg
        }

        ,
        directives: {}

        ,
        data() {
            return {
                // S3 관련 코드

                file: null,
                // albumBucketName, bucketRegion, IdentityPoolId = AWS S3 bucket value -> .env save 
                albumBucketName: "cert-photo-upload",
                bucketRegion: "ap-northeast-2",
                IdentityPoolId: "ap-northeast-2:8cf7cb29-d051-4f38-885f-09b1e4dd8153",
                // 
                selectedImage: null,
                photoURL: "https://s3.ap-northeast-2.amazonaws.com/cert-photo-upload/",
                // fileList: [],
                // select: [],
                // items: [],
                // search: "", //sync search
                certForm: {
                    cng_id: "",
                    user_id: "",
                    content: "",
                    img: "",
                    current_week: "",
                    current_day: "",

                },
                dateInfo: [],
                CngInfo: [],
                UserInfo: [],
                category: "",
                certFile: null,
                certImg: "",
                color: "",
            };
        },
        created() {
            this.getCngInfo();
            this.getUserInfo();
        },
        computed: {
            ...mapState('UserStore', ['user']),
        },
        mounted() {},
        methods: {

            getCngInfo() {
                const cngId = this.$route.params.cngId;
                axios.get(`http://i4a303.p.ssafy.io/api/detailChallenge/${cngId}`)
                    .then((response) => {
                        console.log(response.data);
                        this.CngInfo = response.data;

                        if (this.CngInfo.category_id === '1') {
                            this.category = "건강"
                            this.color = "light-blue lighten-1"
                        } else if (this.CngInfo.category_id === '2') {
                            this.category = "생활습관"
                            this.color = "orange lighten-1"
                        } else if (this.CngInfo.category_id === '3') {
                            this.category = "독서"
                            this.color = "teal lighten-1"
                        } else if (this.CngInfo.category_id === '4') {
                            this.category = "자산"
                            this.color = "indigo lighten-1"
                        } else if (this.CngInfo.category_id === '5') {
                            this.category = "자기계발"
                            this.color = "purple lighten-1"
                        } else {
                            this.category = "취미"
                            this.color = "pink lighten-1"
                        }
                        console.log("카테고리", this.category)
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            },

            getUserInfo() {
                const userId = this.$store.state.UserStore.user.user_id
                axios.get(`http://i4a303.p.ssafy.io/api/userPage/Id/${userId}`)
                    .then((res) => {
                        this.UserInfo = res.data;
                    })
            },

            handleFileUpload() {
                this.file = this.$refs.file.files[0]
                console.log(this.file, '파일이 선택되었음')
                // 사진 보여줄 거
                this.selectedImage = URL.createObjectURL(this.file);
            },
            writeCert() {
                // AWS Setting Start

                // S3 관련 코드
                AWS.config.update({

                        region: this.bucketRegion,
                        credentials: new AWS.CognitoIdentityCredentials({
                                IdentityPoolId: this.IdentityPoolId
                            }

                        )
                    }

                );

                const s3 = new AWS.S3({

                    apiVersion: "2006-03-01",
                    params: {
                        Bucket: this.albumBucketName
                    }
                });

                // AWS Setting End

                const user_id = this.$store.state.UserStore.user.user_id;
                this.certForm.user_id = user_id;
                this.certForm.cng_id = this.$route.params.cngId;
                this.certForm.current_week = this.$route.params.currentWeek;
                this.certForm.current_day = this.$route.params.currentDay;


                var now = new Date();

                var year = now.getFullYear(); // 연도
                var month = now.getMonth() + 1; // 월
                var date = now.getDate(); // 일
                var hours = now.getHours(); // 시간
                var minutes = now.getMinutes(); // 분
                var seconds = now.getSeconds(); // 초
                var milliseconds = now.getMilliseconds(); // 밀리초

                // console.log("현재 : ", now);
                var realtime = year + "" + month + "" + date + "_" + hours + minutes + seconds + milliseconds;
                console.log(realtime);

                // S3 관련 url값
                let photoKey = user_id + "_" + realtime + "_" + this.certImg
                this.certForm.img = this.photoURL + photoKey;

                // let photoKey = "http://www.topstarnews.net/news/photo/first/201709/img_306795_1.jpg"

                console.log(this.certForm.content);
                console.log(this.certForm.img);
                console.log(this.certForm.user_id);
                console.log(this.certForm.cng_id);
                // console.log(this.certForm.select);

                // // S3 관련 코드 풀면서 제거하기
                // this.$store.dispatch("CertStore/writeCert", this.certForm);
                // console.log(this.certForm);

                // S3 관련 코드

                s3.upload({
                        Key: photoKey,
                        Body: this.certFile,
                        ACL: 'public-read'
                    }, (err, data) => {
                        if (err) {
                            console.log(err)
                            return alert('There was an error uploading your photo: ', err.message);
                        }
                        this.$store.dispatch("CertStore/writeCert", this.certForm);
                        console.log(this.certForm);
                        console.log(data);
                    }

                );

            },
            receiveCertImg: function (file, certImg) {
                this.certFile = file
                this.certImg = certImg
                console.log("넘어온 file정보: " + this.certFile)
                console.log("넘어온 thumbnail이름 : " + this.certImg)
            },
        }
    };
</script>
<style lang="scss" scoped>
    a {
        text-decoration: none;
        color: #ffffff;
    }

    .container {
        margin-top: 10%;
        margin: 0 auto;
        align-content: center;
    }

    .left,
    .right {
        margin-top: 5%;
    }


    // 해시태그

    // .tag-input span.v-chip::before {
    //     content: "label";
    //     font-weight: normal;
    //     font-style: normal;
    //     font-size: 20px;
    //     line-height: 1;
    //     letter-spacing: normal;
    //     text-transform: none;
    //     display: inline-block;
    //     white-space: nowrap;
    //     word-wrap: normal;
    //     direction: ltr;
    //     -webkit-font-feature-settings: 'liga';
    //     -webkit-font-smoothing: antialiased;
    // }

    .container-size {
        border: 5px;
        width: 40vw;
    }

    .insert {
        margin-top: 3%
    }

    .cancel-done-btn {
        justify-content: center;
        width: 100%;
        color: #9CCC65;
        margin-top: 20px;
        text-decoration: none;
    }

    .create-btn {
        color: #ffffff;

    }

    .cng-name {
        justify-content: center;
        font-style: italic;
        margin-bottom: 3%;
    }

    .title-size {
        font-size: 25px;
    }

    .border-list {
        border: solid rgb(231, 231, 231);
    }
</style>