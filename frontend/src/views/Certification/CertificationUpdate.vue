<template>
    <v-container class="container-size">
        <v-col class="cng-name">
            <router-link :to="{ name: 'SeedDetail', params: { seedId: CertInfo.cng_id }}">
            <span class="d-flex justify-center align-center mb-3 title-size">
                {{ CngInfo.title }}
            </span>
            </router-link>
            <span class="d-flex justify-center">
                <v-chip class="white--text" :color="color">
                    {{ this.category }}
                </v-chip>
            </span>
        </v-col>

        <v-row>
        
        <v-list-item class="border-list">
            <v-list-item-avatar size="55">
                <v-img :src="UserAllInfo.img"></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
                <v-list-item-title>
                    <span class="nickname-bold" style="font-size:15px; color:black;"> {{ UserAllInfo.nickname }}</span>
                </v-list-item-title>
                <v-list-item-title>
                    <span class="nickname-bold" style="font-size:13px; color:grey;"> {{ CertInfo.create_date }}</span>
                </v-list-item-title>
            </v-list-item-content>
        </v-list-item>
        </v-row>
        <v-row class="img-div">
            <CertificationImgUpdate @transferCertImg="receiveCertImg" class="mb-5"
                :currentSelectedImg="currentSelectedImg" />
        </v-row>
        <v-row class="content">
            <v-textarea v-model="CertInfo.content" no-resize color="#AED864" outlined rows="10" label="설명글">
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


        <v-divider></v-divider>
        <CommentInsert />
        <CommentList v-for="(comment, index) in comments" :key="index" :comment="comment" />

    </v-container>
</template>

<script>
    import CommentInsert from '@/views/Certification/components/CommentInsert.vue'
    import CommentList from '@/views/Certification/components/CommentList.vue'
    import CertificationImgUpdate from '@/views/Certification/components/CertificationImgUpdate.vue'

    import axios from "axios";

    export default {
        name: 'CertificationUpdate',
        components: {
            CommentList,
            CommentInsert,
            CertificationImgUpdate
        },
        directives: {},
        data() {
            return {
                CertInfo: [],
                CngInfo: [],
                comments: [],
                UserAllInfo: [], 
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
                category: "",
                currentSelectedImg: "",
                certFile: null,
                certImg: "",
                color: "",
                changedImg: false, 

            };
        },
        mounted() {

        },
        created() {

            this.getCngInfo();
            this.getUserAllInfo();
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

            getUserAllInfo() {
                const userId = this.$store.state.UserStore.user.user_id
                axios.get(`http://i4a303.p.ssafy.io/api/userPage/Id/${userId}`)
                    .then((res) => {
                        this.UserAllInfo = res.data;
                    })
            },
            async detailCert() {
                const certId = this.$route.params.certId;
                axios.get(`http://i4a303.p.ssafy.io/api/detailCertification/${certId}`)
                    .then((response) => {
                        this.CertInfo = response.data;
                        this.currentSelectedImg = this.CertInfo.img;
                        console.log("created되고 imageUrl: " + this.currentSelectedImg);
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            },

            detailComment: function () {
                const certId = this.$route.params.certId;
                axios.get(`http://i4a303.p.ssafy.io/api/commentList/${certId}`)
                    .then((response) => {
                        this.comments = response.data;
                        for (const i in this.comments) {
                            const comment = this.comments[i]
                            const commentUserId = comment["user_id"]
                            this.getUserInfo(commentUserId);
                        }
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            },
            async getUserInfo(commentUserId) {
                  await axios.get(`http://i4a303.p.ssafy.io/api/userPage/Id/${commentUserId}`)
                    .then((response) => {
                        this.UserInfo = response.data;
                        this.Nicknames.push(this.UserInfo.nickname)
                        this.ProfileImgs.push(this.UserInfo.img)
                        
                    })

            },
            back() {
                this.$router.push({
                    name: 'CertificationDetail',
                    params: {
                        cngId: this.$route.params.cngId,
                        certId: this.$route.params.certId,
                        cngUserId: this.$route.params.cngUserId
                    }
                });
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
               
                if (this.changedImg === false) {

                    axios.put("http://i4a303.p.ssafy.io/api/updateCertification", UpdateCertInfo)
                        .then(res => {
                            console.log(res);
                            this.$router.push({
                                name: 'CertificationDetail',
                                params: {
                                    cngId: this.$route.params.cngId,
                                    certId: this.$route.params.certId,
                                    cngUserId: this.$route.params.cngUserId
                                }
                            });
                        })
                        .catch(err => {
                            console.log(err);
                        });
                }

                else {

                    this.photoKey = this.certImg;
                    UpdateCertInfo.img = this.photoURL + this.certImg;
                    

                    AWS.config.update({

                            region: this.bucketRegion,
                            credentials: new AWS.CognitoIdentityCredentials({
                                    IdentityPoolId: this.IdentityPoolId
                                }
                            )}
                    );

                    const s3 = new AWS.S3({

                        apiVersion: "2006-03-01",
                        params: {
                            Bucket: this.albumBucketName
                        }
                    });

                    s3.upload({
                            Key: this.photoKey,
                            Body: this.certFile,
                            ACL: 'public-read'
                        }, (err, data) => {
                            if (err) {
                                console.log(err)
                                return alert('There was an error uploading your photo: ', err.message);
                            }

                        }

                    );

                    axios.put("http://i4a303.p.ssafy.io/api/updateCertification", UpdateCertInfo)
                        .then(res => {
                            this.$router.push({
                                name: 'CertificationDetail',
                                params: {
                                    cngId: this.$route.params.cngId,
                                    certId: this.$route.params.certId,
                                    cngUserId: this.$route.params.cngUserId
                                }
                            });
                        })
                        .catch(err => {
                            console.log(err);
                        });
                }
            },

            receiveCertImg: function (file, certImg, changedImg) {
                this.certFile = file
                this.certImg = certImg
                this.changedImg = changedImg
                
            },
        },
    };
</script>

<style lang="scss" scoped>
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
        margin-top: 5%;
        margin-bottom: 5%;
    }

    .content,
    .nickname-date-row {
        justify-content: center;
        margin-top: 5%;
    }

    .img-div {
        margin-top: 3%
    }

    .cng-name {
        justify-content: center;
        font-style: italic;
        margin-bottom: 3%;
    }

    .container-size {
        width: 40vw;
    }

    .title-size {
        color: black;
        font-size: 25px;
    }

    .border-list {
        border: solid rgb(231, 231, 231);
        margin: 0px;
    }

    a {
        text-decoration: none;
    }
</style>