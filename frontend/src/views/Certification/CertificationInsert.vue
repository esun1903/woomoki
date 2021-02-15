<template>
    <v-app>
        <v-container>
            <h2>인증하기</h2>
            <v-row>
                <v-col cols="6" class="left">
                    <!-- <v-layout wrap align-center> -->
                    <v-col>
                        <v-textarea v-model="certForm.content" outlined rows="10" label="설명글"
                            placeholder="자유롭게 인증에 대한 설명을 넣어주세요">
                        </v-textarea>
                        <!-- </v-col>
                        <v-file-input v-model="certForm.photo" label="인증사진" outlined multiple dense :rules="rules"
                            accept="image/png, image/jpeg, image/bmp, image/jpg" prepend-icon="mdi-camera">
                        </v-file-input>
                    <v-col> -->
                        <v-col>
                            <input id="file-selector" ref="file" type="file" @change="handleFileUpload()">
                        </v-col>
                        <!-- <v-combobox multiple v-model="certForm.select" label="Tags" small-chips deletable-chips
                            class="tag-input" :search-input.sync="search"></v-combobox> -->
                    </v-col>
                </v-col>
                <v-col cols="6" class="right">
                    <v-img v-if="selectedImage" :src="selectedImage">
                    </v-img>
                </v-col>
            </v-row>

            <v-row cols="12">
                <v-col cols="6">
                    <router-link :to="'/'">
                        <BackBtn />
                    </router-link>
                </v-col>

                <v-col cols="6">
                    <v-btn class="cert-insert-btn" @click="writeCert" rounded color="white" type="upload">
                        인증글 등록하기
                    </v-btn>

                </v-col>
            </v-row>

            <!-- </v-layout> -->



            <!-- <h1>파일 리스트 </h1>
            <div v-for="(file, index) in fileList" :key="file.Key">#{{index+1}} {{file.Key}}

                <div>
                    <v-img v-bind:src="photoURL" />
                </div>
                <v-btn @click="deleteFile(file.Key)" color="red" flat icon>X</v-btn>
            </div> -->
            <!-- <h1>파일 업로더 </h1><input id="file-selector" ref="file" type="file" @change="handleFileUpload()">
            <v-btn @click="writeCert" color="primary">업로드</v-btn> -->

        </v-container>
    </v-app>
</template>
<script>
    // import AWS from 'aws-sdk'
    import BackBtn from '@/views/Certification/components/BackBtn.vue'
    import {
        mapState
    } from "vuex";

    export default {

        name: 'CertificationInsert',
        components: {
            BackBtn
        }

        ,
        directives: {}

        ,
        data() {
            return {
                // S3 관련 코드

                // file: null,
                // // albumBucketName, bucketRegion, IdentityPoolId = AWS S3 bucket value -> .env save 
                // albumBucketName: "cert-photo-upload",
                // bucketRegion: "ap-northeast-2",
                // IdentityPoolId: "ap-northeast-2:8cf7cb29-d051-4f38-885f-09b1e4dd8153",
                // // 
                selectedImage: null,
                // photoURL: "https://s3.ap-northeast-2.amazonaws.com/cert-photo-upload/",
                // fileList: [],
                // select: [],
                // items: [],
                // search: "", //sync search
                certForm: {
                    cng_id: "",
                    user_id: "",
                    content: "",
                    img: "",
                    // select: [],
                }
            };
        },
        created() {
            // this.getFiles();
            // console.log(this.photoURL);
        },
        computed: {
            ...mapState('UserStore', ['user']),
        },
        mounted() {},
        methods: {
            handleFileUpload() {
                this.file = this.$refs.file.files[0]
                console.log(this.file, '파일이 선택되었음')
                // 사진 보여줄 거
                this.selectedImage = URL.createObjectURL(this.file);
            },
            writeCert() {
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

                const user_id = this.$store.state.UserStore.user.user_id;
                this.certForm.user_id = user_id;
                //챌린지 id값 받아오기
                this.certForm.cng_id = this.$route.params.cngId;

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

                this.certForm.img = photoKey;

                console.log(this.certForm.content);
                console.log(this.certForm.img);
                console.log(this.certForm.user_id);
                console.log(this.certForm.cng_id);
                // console.log(this.certForm.select);

                // S3 관련 코드 풀면서 제거하기
                this.$store.dispatch("CertStore/writeCert", this.certForm);
                console.log(this.certForm);

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
                //         this.$store.dispatch("CertStore/writeCert", this.certForm);
                //         console.log(this.certForm);
                //         console.log(data);
                //         this.getFiles();
                //     }

                // );

            },
            // S3 관련 코드
            // getFiles() {
            //     // AWS Setting Start

            //     AWS.config.update({
            //         region: this.bucketRegion,
            //         credentials: new AWS.CognitoIdentityCredentials({
            //             IdentityPoolId: this.IdentityPoolId
            //         })
            //     });

            //     const s3 = new AWS.S3({
            //         apiVersion: "2006-03-01",
            //         params: {
            //             Bucket: this.albumBucketName
            //         }
            //     });

            //     // AWS Setting End
            //     s3.listObjects({
            //             Delimiter: '/'
            //         }, (err, data) => {

            //             // [ (err, data) => ]의 형태의 arrow function 으로 해주지 않고 [ function (err, data) ]으로 사용하면 function{...}의 ... 부분으로 인식되어 this가 상단부에서 선언한 값으로 처리되지 않는다. 
            //             if (err) {
            //                 return alert('There was an error listing your albums: ' + err.message);
            //             } else {
            //                 this.fileList = data.Contents;
            //                 console.log(data);
            //             }
            //         }

            //     );
            // }

            // ,
            // deleteFile(key) {
            //     // AWS Setting Start

            //     AWS.config.update({

            //             region: this.bucketRegion,
            //             credentials: new AWS.CognitoIdentityCredentials({
            //                     IdentityPoolId: this.IdentityPoolId
            //                 }

            //             )
            //         }

            //     );

            //     const s3 = new AWS.S3({

            //             apiVersion: "2006-03-01",
            //             params: {
            //                 Bucket: this.albumBucketName
            //             }
            //         }

            //     );

            //     // AWS Setting End

            //     s3.deleteObject({
            //             Key: key
            //         }

            //         , (err, data) => {
            //             if (err) {
            //                 return alert('There was an error deleting your photo: ', err.message);
            //             }

            //             alert('Successfully deleted photo.');
            //             console.log(data)
            //             this.getFiles()
            //         }

            //     );

            // }
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

    h2 {
        text-align: center;
        margin-top: 5%;
    }



    .tag-input span.v-chip::before {
        content: "label";
        font-weight: normal;
        font-style: normal;
        font-size: 20px;
        line-height: 1;
        letter-spacing: normal;
        text-transform: none;
        display: inline-block;
        white-space: nowrap;
        word-wrap: normal;
        direction: ltr;
        -webkit-font-feature-settings: 'liga';
        -webkit-font-smoothing: antialiased;
    }

    .cert-insert-btn {
        width: 100%;
        color: #9CCC65;
        margin-top: 20px;
        text-decoration: none;
    }
</style>