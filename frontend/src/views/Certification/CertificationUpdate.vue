<template>
    <v-container>
        <div class="detail">
            <v-row class="cng-name">
                챌린지명: {{ CertInfo.title}}
            </v-row>
            <v-row class="nickname-date-row">
                <v-col class="user-id">
                    아이디: {{ CertInfo.nickname}}
                </v-col>
                <v-col class="date">
                    인증날짜: {{ CertInfo.create_date }}
                </v-col>
            </v-row>
            <v-row class="img-div">
                <v-avatar rounded size=auto @click="updateImg()">
                    <v-img v-if="imageUrl" :src="imageUrl">
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
            <CommentList v-for="(comment, index) in comments" :key="index" :comment="comment"
                :nickname="Nicknames[index]" :profileImg="ProfileImgs[index]" />

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
                comments: [],
                UserInfo: [],
                Nicknames: [],
                ProfileImgs: [],
                photoURL: "https://s3.ap-northeast-2.amazonaws.com/cert-photo-upload/",
                albumBucketName: "cert-photo-upload",
                bucketRegion: "ap-northeast-2",
                IdentityPoolId: "ap-northeast-2:8cf7cb29-d051-4f38-885f-09b1e4dd8153",
                dialog: false,
                file: null,
                photoKey: "",
                imageURL: "",

            };
        },
        mounted() {

        },
        created() {
            this.detailCert();
            this.detailComment();
            const cngId = this.$route.params.cngId;
            const certId = this.$route.params.certId;
            const cngUserId = this.$route.params.cngUserId;
            console.log("UPDATE PAGE/ cngid: " + cngId + ", certid: " + certId + ", cngUserId: " + cngUserId);
            if (cngId === undefined || certId === undefined || cngUserId === undefined) {
                this.$router.go(-1);
            }
        },
        methods: {
            async detailCert() {
                const certId = this.$route.params.certId;
                axios.get(`http://localhost:8080/detailCertification/${certId}`)
                    .then((response) => {
                        this.CertInfo = response.data;
                        this.imageUrl = this.CertInfo.img;
                        console.log("created되고 imageUrl: " + this.imageUrl);
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            },

            detailComment: function () {
                const certId = this.$route.params.certId;
                console.log(certId);
                axios.get(`http://localhost:8080/commentList/${certId}`)
                    .then((response) => {
                        // console.log(response.data);
                        this.comments = response.data;
                        for (const i in this.comments) {
                            const comment = this.comments[i]
                            const commentUserId = comment["user_id"]
                            // console.log("cmt user id: " + commentUserId);
                            this.getUserInfo(commentUserId);
                        }
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            },
            async getUserInfo(commentUserId) {
                // console.log("getUserInfo를 들어왔ㅇㅓ : " + commentUserId);
                await axios.get(`http://localhost:8080/userPage/Id/${commentUserId}`)
                    .then((response) => {
                        // console.log(response.data);
                        this.UserInfo = response.data;
                        this.Nicknames.push(this.UserInfo.nickname)
                        this.ProfileImgs.push(this.UserInfo.img)
                        // console.log("nickname: " + this.UserInfo.nickname);
                        // console.log(this.Nicknames);
                        // this.saveNickname();
                    })

            },
            back() {
                // this.$router.go(-1);
                this.$router.push({
                    name: 'CertificationDetail',
                    params: {
                        cngId: this.$route.params.cngId,
                        certId: this.$route.params.certId,
                        cngUserId: this.$route.params.cngUserId
                    }
                });
            },
            updateImg() {
                this.$refs.imageInput.click();
            },
            onChangeImages(e) {
                console.log(e.target.files)
                this.file = e.target.files[0]; // Get first index in files
                this.imageURL = URL.createObjectURL(this.file);
                // this.CertInfo.img = this.imageURL
                console.log("imageurl: "+this.imageURL)
                console.log("certinfo: "+this.CertInfo.img)
                
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
                console.log(this.file.name);

                this.photoKey = this.CertInfo.user_id + "_" + realtime + "_" + this.file.name
                this.CertInfo.img = this.photoURL + this.photoKey;
                console.log(this.CertInfo.img);

                // AWS Setting Start

                console.log("updateCert 들어옴");

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


                // S3 관련 코드

                s3.upload({
                        Key: this.photoKey,
                        Body: this.file,
                        ACL: 'public-read'
                    }, (err, data) => {
                        if (err) {
                            console.log(err)
                            return alert('There was an error uploading your photo: ', err.message);
                        }

                    }

                );


            },
            updateCert() {

                const certId = this.$route.params.certId;
                const UpdateCertInfo = {
                    cng_id: this.CertInfo.cng_id,
                    content: this.CertInfo.content,
                    id: certId,
                    img: this.CertInfo.img,
                    user_id: this.CertInfo.user_id,
                    like_cnt: this.CertInfo.like_cnt,
                    result: this.CertInfo.result,
                    current_week: this.CertInfo.current_week,
                    current_day: this.CertInfo.current_day,
                }
                console.log(UpdateCertInfo.img);


                axios.put("http://localhost:8080/updateCertification", UpdateCertInfo)
                    .then(res => {
                        console.log(res);
                        this.$router.push({
                            name: 'SeedDetail',
                            params: {
                                seedId: this.CertInfo.cng_id
                            }
                        })
                    })
                    .catch(err => {
                        console.log(err);
                    });
                console.log(this.certForm);

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

    .content,
    .nickname-date-row {
        justify-content: center;
        margin-top: 5%;
    }

    .img-div:hover {
        opacity: 0.4;
    }





    .cng-name {
        justify-content: center;
        font-style: italic;
        margin-bottom: 3%;
    }
</style>