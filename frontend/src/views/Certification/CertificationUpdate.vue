<template>
    <v-container>
        <div class="detail">
            <v-row class="cng-name">
                <!-- 챌린지명: {{ this.$route.params.cngName }} -->
                챌린지명: 챌린지명입니다
            </v-row>
            <v-row class="nickname-date-row">
                <v-col class="user-id">
                    아이디: {{ CertInfo.user_id }}
                </v-col>
                <v-col class="date">
                    인증날짜: {{ CertInfo.create_date }}
                </v-col>
            </v-row>
            <v-row class="img">
                <v-avatar rounded size=auto @click="updateImg()"> 
                    <v-img v-if="CertInfo.img" :src="CertInfo.img">
                        <!-- <div style="background: grey">{{this.explain}}</div> -->
                        </v-img>
                    <input ref="imageInput" type="file" hidden @change="onChangeImages">
                </v-avatar>
            </v-row>
            <v-row class="content">
                <v-textarea v-model="CertInfo.content" outlined rows="10" label="content">
                </v-textarea>
            </v-row>
            <v-row class="back-update-btn">
                <v-btn class="ma-2" outlined fab color="red" v-on:click="back()">
                    <v-icon>mdi-arrow-left</v-icon>
                </v-btn>
                <v-btn class="ma-2" outlined fab color="green" v-on:click="updateCert()">
                    <v-icon>mdi-check-outline</v-icon>
                </v-btn>

            </v-row>
        </div>

        <div>
            <v-divider></v-divider>
            <CommentInsert />
            <CommentList />
        </div>
    </v-container>
</template>

<script>
    import CommentInsert from '@/views/Certification/components/CommentInsert.vue'
    import CommentList from '@/views/Certification/components/CommentList.vue'
    import axios from "axios";

    export default {
        name: 'CertificationUpdate',
        components: {
            CommentList,
            CommentInsert
        },
        directives: {},
        data() {
            return {
                CertInfo: [],
                // photoUrl: "https://s3.ap-northeast-2.amazonaws.com/cert-photo-upload/",
                dialog: false,
                // explain: "사진 변경을 희망한다면 여기를 눌러주세요"
            };
        },
        mounted() {

        },
        created() {
            this.detailCert();
            const cngId = this.$route.params.cngid;
            const certId = this.$route.params.certid;
            const cngName = "챌린지명";
            console.log(cngName);
            console.log("UPDATE PAGE/ cngid: " + cngId + ", certid: " + certId);
            if (cngId === undefined || certId === undefined) {
                this.$router.go(-1);
            }
        },
        methods: {
            detailCert: function () {
                const certId = this.$route.params.certid;
                axios.get(`http://localhost:8080//detailcertification/${certId}`)
                    .then((response) => {
                        this.CertInfo = response.data;
                        console.log(response.data)
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            },
            back() {
                // this.$router.go(-1);
                this.$router.push({
                    name: 'CertificationDetail',
                    params: {
                        cngid: this.$route.params.cngid,
                        certid: this.$route.params.certid,
                    }
                });
            },
            updateImg() {
                this.$refs.imageInput.click();
            },
            onChangeImages(e) {
                console.log(e.target.files)
                const file = e.target.files[0]; // Get first index in files
                this.CertInfo.img = URL.createObjectURL(file);
                this.explain="";
            },
            updateCert() {
                // // AWS Setting Start

                // S3 관련 코드
                // AWS.config.update({

                //         region: this.bucketRegion,
                //         credentials: new AWS.CognitoIdentityCredentials({
                //                 IdentityPoolId: this.IdentityPoolId
                //             }

                //         )
                //     }

                // );

                // const s3 = new AWS.S3({

                //     apiVersion: "2006-03-01",
                //     params: {
                //         Bucket: this.albumBucketName
                //     }
                // });

                // // AWS Setting End

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

                // S3 관련 주소 풀기
                // let photoKey = user_id + "_" + realtime + "_" + this.file.name
                // this.certForm.img = photoURL + photoKey;


                let photoKey = "http://www.topstarnews.net/news/photo/first/201709/img_306795_1.jpg"

                this.CertInfo.img = photoKey;

                const certid = this.$route.params.certid;
                const UpdateCertInfo = {
                    cng_id: this.CertInfo.cng_id,
                    content: this.CertInfo.content,
                    id: certid,
                    img: this.CertInfo.img,
                    user_id: this.CertInfo.user_id,
                    like_cnt: this.CertInfo.like_cnt,
                    result: this.CertInfo.result,
                }
                console.log(UpdateCertInfo);


                axios.put("http://localhost:8080/updateCertification", UpdateCertInfo)
                    .then(res => {
                        console.log(res);
                        this.$router.push({
                    name: 'CertificationDetail',
                    params: {
                        cngid:  this.CertInfo.cng_id,
                        certid: certid,
                    }
                });
                    })
                    .catch(err => {
                        console.log(err);
                    });

                // S3 관련 코드

                // s3.upload({
                //         Key: photoKey,
                //         Body: this.file,
                //         ACL: 'public-read'
                //     }, (err, data) => {
                //         if (err) {
                //             console.log(err)
                //             return alert('There was an error uploading your photo: ', err.message);
                //         }
                //         axios.put("http://localhost:8080/updateCertification", UpdateCertInfo)
                // .then(res => {
                //     console.log(res);
                // })
                // .catch(err => {
                //     console.log(err);
                // });
                //         console.log(this.certForm);
                //         console.log(data);
                //         this.getFiles();
                //     }

                // );

            },
        },
    };
</script>

<style lang="scss" scoped>
    .detail {
        margin: 10% 30% 0% 30%;
    }

    .v-divider {
        margin: 3% !important;
    }

    .like-btn {
        float: right;
        margin-top: 5%;
        margin-bottom: 5%;
    }

    .back-update-btn {
        justify-content: center;
        margin-top: 15%;
    }

    .img,
    .content,
    .nickname-date-row
        {
        justify-content: center;
        margin-top: 5%;
    }


    .cng-name {
        justify-content: center;
        font-style: italic;
        margin-bottom: 3%;
    }
</style>