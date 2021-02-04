<template>
    <v-app>
        <v-container>
            <h2>인증하기</h2>
            <v-row>
                 <v-col cols="6" class="left">
                <!-- <v-layout wrap align-center> -->
                            <v-col cols="4" md="6">
                                <v-text-field dense label="Title"></v-text-field>
                            </v-col>
                            <v-col cols="4" md="6">
                                <v-textarea outlined name="input-7-4" label="Content" value=""></v-textarea>
                            </v-col>
                            <v-col cols="4" md="6">
                                <v-file-input label="인증사진" outlined multiple dense :rules="rules"
                                    accept="image/png, image/jpeg, image/bmp, image/jpg" prepend-icon="mdi-camera">
                                </v-file-input>
                            </v-col>
                            <v-col cols="4" md="6">
                                <v-combobox multiple v-model="select" label="Tags" small-chips deletable-chips
                                    class="tag-input" :search-input.sync="search"></v-combobox>
                            </v-col>
                            <v-col cols="4" md="6">
                                <router-link :to="'/'">
                                    <BackBtn />
                                </router-link>
                            </v-col>
                            
                <!-- </v-layout> -->
                 </v-col>
                  <v-col cols="6" class="right">
                 <v-img lazy-src="https://picsum.photos/id/11/10/6" max-height="750" max-width="600"
                                src="https://picsum.photos/id/11/500/300"></v-img>
                  </v-col>

                <!-- <h1>파일 리스트 </h1>
                <div v-for="(file, index) in fileList" :key="file.Key">#{{index+1}} {{file.Key}}

                    <div>
                        <v-img v-bind:src="photoURL" />
                    </div>
                    <v-btn @click="deleteFile(file.Key)" color="red" flat icon>X</v-btn>
                </div>
                <h1>파일 업로더 </h1><input id="file-selector" ref="file" type="file" @change="handleFileUpload()">
                <v-btn @click="uploadFile" color="primary">업로드</v-btn> -->
            </v-row>
        </v-container>
    </v-app>
</template>
<script>
    import AWS from 'aws-sdk'
    import BackBtn from '@/views/Certification/components/BackBtn.vue'

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
                file: null,
                // albumBucketName, bucketRegion, IdentityPoolId = AWS S3 bucket value -> .env save 
                albumBucketName: "cert-photo-upload",
                bucketRegion: "ap-northeast-2",
                IdentityPoolId: "ap-northeast-2:8cf7cb29-d051-4f38-885f-09b1e4dd8153",
                // 
                photoURL: "https://s3.ap-northeast-2.amazonaws.com/cert-photo-upload/",
                fileList: [],
                select: [],
                items: [],
                search: "" //sync search
            }

            ;
        }

        ,
        created() {
            this.getFiles()
            console.log(this.photoURL)
        },
        mounted() {},
        methods: {
            handleFileUpload() {
                this.file = this.$refs.file.files[0]
                console.log(this.file, '파일이 업로드 되었습니다.')
            },
            uploadFile() {
                // AWS Setting Start

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

                let photoKey = this.file.name
                s3.upload({
                        Key: photoKey,
                        Body: this.file,
                        ACL: 'public-read'
                    }, (err, data) => {
                        if (err) {
                            console.log(err)
                            return alert('There was an error uploading your photo: ', err.message);
                        }
                        alert('Successfully uploaded photo.');
                        console.log(data)
                        this.getFiles()
                    }

                );

            },
            getFiles() {
                // AWS Setting Start

                AWS.config.update({
                    region: this.bucketRegion,
                    credentials: new AWS.CognitoIdentityCredentials({
                        IdentityPoolId: this.IdentityPoolId
                    })
                });

                const s3 = new AWS.S3({
                    apiVersion: "2006-03-01",
                    params: {
                        Bucket: this.albumBucketName
                    }
                });

                // AWS Setting End
                s3.listObjects({
                        Delimiter: '/'
                    }, (err, data) => {

                        // [ (err, data) => ]의 형태의 arrow function 으로 해주지 않고 [ function (err, data) ]으로 사용하면 function{...}의 ... 부분으로 인식되어 this가 상단부에서 선언한 값으로 처리되지 않는다. 
                        if (err) {
                            return alert('There was an error listing your albums: ' + err.message);
                        } else {
                            this.fileList = data.Contents;
                            console.log(data);
                        }
                    }

                );
            }

            ,
            deleteFile(key) {
                // AWS Setting Start

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
                    }

                );

                // AWS Setting End

                s3.deleteObject({
                        Key: key
                    }

                    , (err, data) => {
                        if (err) {
                            return alert('There was an error deleting your photo: ', err.message);
                        }

                        alert('Successfully deleted photo.');
                        console.log(data)
                        this.getFiles()
                    }

                );

            }
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
</style>