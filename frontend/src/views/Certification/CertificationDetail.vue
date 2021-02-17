<template>
    <v-container>
        <div class="detail">
            <v-row class="cng-name">
                <!-- 챌린지명: {{ this.$route.params.cngName }} -->
                씨앗 이름: {{ CertInfo.title }}
            </v-row>
            <v-row class="d-flex justify-center">
                <v-chip class="white--text" :color="color">
                    {{ this.category }}
                </v-chip>
            </v-row>
            <v-row class="nickname-date-row">
                <v-col class="user-id">
                    닉네임: {{ CertInfo.nickname }}
                </v-col>
                <v-col class="date">
                    인증날짜: {{ CertInfo.create_date }}
                </v-col>
            </v-row>
             <!-- v-if="showResultBtn" -->
            <v-row class="result-btn-row">
                <v-btn icon color="blue" value="1" v-on:click="successCert()">
                    <v-icon>mdi-thumb-up</v-icon>
                </v-btn>
                <v-btn icon color="red" value="0" v-on:click="failCert()">
                    <v-icon>mdi-thumb-down</v-icon>
                </v-btn>
            </v-row>
            <v-row class="img">
                <!-- s3 주소 주석 풀기
                <v-img :src="photoUrl + CertInfo.img"></v-img> -->
                <div class="div-img">
                    <v-img width="100%" :src="CertInfo.img"></v-img>
                </div>
            </v-row>
            <v-row class="d-flex justify-start content">
                <v-col class="d-flex align-center div-content">
                    {{CertInfo.content}}
                </v-col>
                <v-col class="d-flex justify-end">
                    <v-btn icon x-large @click="getScrap">
                    <v-icon :color="scrapped ? 'red' : '' ">mdi-heart</v-icon>
                    <span>{{ this.likeCount }}</span>
                </v-btn>
                 <v-btn icon x-large @click="getConfirm" class="ml-5">
                    <v-icon :color="confirmed ? '#78909C' : '' ">fas fa-stamp</v-icon>
                </v-btn>
                </v-col>
            </v-row>
            <!-- <v-row class="like-btn"> -->
                <!-- <v-btn icon x-large @click="getScrap">
                    <v-icon :color="scrapped ? 'red' : '' ">mdi-heart</v-icon>
                    <span>{{ this.likeCount }}</span>
                </v-btn>
                 <v-btn icon x-large @click="getConfirm" class="ml-5">
                    <v-icon :color="confirmed ? '#78909C' : '' ">fas fa-stamp</v-icon>
                </v-btn> -->
                <!-- <v-btn v-if="getCheckSeedOwner" icon x-large @click="goStampCard" class="ml-5">
                    <v-icon :color="scrapped ? '#78909C' : '' ">fas fa-stamp</v-icon>
                </v-btn> -->
            <!-- </v-row> -->
            <v-row class="edit-del-btn">
                <v-btn class="ma-2" outlined fab color="green" v-on:click="updateCert()" v-if="checkUser">
                    <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-dialog v-model="dialog" persistent max-width="290" v-if="checkUser">
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn class="ma-2" outlined fab color="red" v-bind="attrs" v-on="on">
                            <v-icon>mdi-trash-can</v-icon>
                        </v-btn>
                    </template>
                    <v-card>
                        <v-card-title class="headline">
                            인증글을 삭제하시겠습니까?
                        </v-card-title>
                        <v-card-text>한번 삭제된 인증글은 되돌릴 수 없습니다! 인증글을 삭제 시, 재인증을 시도해주세요.</v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="green darken-1" text @click="dialog = false">
                                취소
                            </v-btn>
                            <v-btn color="red darken-1" text @click="dialog = false; deleteCert()">
                                삭제
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>

            </v-row>
        </div>

            <v-divider></v-divider>
            <CommentInsert />
            <!-- v-for 돌 때 마다 comment.user_id를 axios get -> userinfo 받아
            이 받은 usernickname을 자식컴포넌트에 보내 -->
            <CommentList v-for="(comment, index) in comments" :key="index" :comment="comment" :nickname="Nicknames[index]" :profileImg="ProfileImgs[index]" />
        
    </v-container>
</template>

<script>
    import CommentInsert from '@/views/Certification/components/CommentInsert.vue'
    import CommentList from '@/views/Certification/components/CommentList.vue'
    import {mapState} from "vuex";
    import axios from "axios";

    export default {
        name: 'CertificationDetail',
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
                // photoUrl:"https://s3.ap-northeast-2.amazonaws.com/cert-photo-upload/",
                dialog: false,
                scrapped: false,
                confirmed: false,
                checkUser: false,
                showResultBtn: false,
                likeCount: 0,
                category: "",
                color: "",
            };
        },
        computed: {
            ...mapState('UserStore', ['user']),
            getCheckSeedOwner () {
                if (this.user.user_id === this.$route.params.cngUserId) {
                    return true
                } else {
                    return false
                }
            }
        },
        mounted() {

        },
        created() {
            this.getCategory()
            this.CheckisLikeSeed();
            this.detailCert();
            this.detailComment();
            const certId = this.$route.params.certId;
            const cngId = this.$route.params.cngId;
            const cngUserId = this.$route.params.cngUserId;
            // const cngName = this.$route.params.cngName;
            // console.log(cngName);
            console.log("detail's cngid: " + cngId);
            console.log("detail's certid: " + certId);
            console.log("cngUserId " + cngUserId);
            if (certId === undefined || cngId === undefined || cngUserId === undefined) {
                this.$router.go(-1);
            }
            // console.log("check create: " + this.checkUser);

        },
        methods: {
            async detailCert() {
                const userId = this.$store.state.UserStore.user.user_id;
                console.log("userid: " + userId);
                const certId = this.$route.params.certId;
                await axios.get(`http://localhost:8080/detailCertification/${certId}`)
                    .then((response) => {
                        this.CertInfo = response.data;
                        const certUserId = this.CertInfo.user_id;
                        const cngUserId = this.$route.params.cngUserId;

                        console.log("cngUserId: " + cngUserId);
                        console.log("certUserId: " + certUserId);
                        if (userId === certUserId) {
                            this.checkUser = true;
                        }

                        console.log("cngUserId: " + cngUserId);
                        if (userId === Number(cngUserId)) {
                            this.showResultBtn = true;
                        }
                        console.log(response.data)
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
            // saveNickname(){
            //     for(const i in this.comments.length){
            //         console.log("nickname value: " + this.UserInfo.nickname[i]);
            //         this.comments.push({key:i, value:this.UserInfo.nickname[i]});
            //     }
            //     // console.log("commments: " + this.comments);
            // },
            updateCert: function () {
                this.$router.push({
                    name: 'CertificationUpdate',
                    params: {
                        cngId: this.$route.params.cngId,
                        certId: this.$route.params.certId,
                        cngUserId: this.$route.params.cngUserId,
                    }
                });
            },
            deleteCert() {
                const certId = this.$route.params.certId;
                axios.delete(`http://localhost:8080/deleteCertification/${certId}`)
                    .then((response) => {
                        console.log(response.data)
                        this.$router.push({
                            name: 'SeedDetail',
                            params: {
                                seedId: this.$route.params.cngId
                            }
                        });
                    })
                    .catch((err) => {
                        console.log(err)
                    })

            },
            getScrap() {
                // 클릭 때 마다 scrapped가 토글
                this.scrapped = !this.scrapped
                const certId = this.$route.params.certId;
                const userId = this.$store.state.UserStore.user.user_id
                // 스크랩이 되어있지 않을 때 스크랩
                if (this.scrapped) {
                    axios.put(`http://127.0.0.1:8080/likeUpCertification/${userId}/${certId}`)
                        .then((res) => {
                            this.likeCount += 1
                            console.log(res)
                        })
                        .catch((err) => {
                            console.log(err)
                        })
                } else {
                    // 스크랩 되어 있을 때 스크랩 취소
                    axios.put(`http://127.0.0.1:8080/likeDownCertification/${userId}/${certId}`)
                        .then((res) => {
                            this.likeCount -= 1
                            console.log(res)
                        })
                        .catch((err) => {
                            console.log(err)
                        })
                }
            },
            CheckisLikeSeed: function () {
                const certId = this.$route.params.certId;
                const userId = this.$store.state.UserStore.user.user_id
                axios.get(`http://127.0.0.1:8080/LikeAndCertification/${userId}`)
                    .then((res) => {
                    const CertList = res.data
                    this.likeCount = CertList.length
                    console.log(this.likeCount)
                    var i;
                    for (i=0; i < CertList.length; i++) {
                        if (CertList[i].id === Number(certId)) {
                        this.scrapped = true
                        }
                    }
                    })
                },
            getCategory: function () {
                const seedId = this.$route.params.cngId
                axios.get(`http://127.0.0.1:8080/detailChallenge/${seedId}`)
                    .then((res) => {
                        if (res.data.category_id === '1') {
                            this.category = "건강"
                            this.color = "light-blue lighten-1"
                        } else if (res.data.category_id === '2') {
                            this.category = "생활습관"
                            this.color = "orange lighten-1"
                        } else if (res.data.category_id === '3') {
                            this.category = "독서"
                            this.color = "teal lighten-1"
                        } else if (res.data.category_id === '4') {
                            this.category = "자산"
                            this.color = "indigo lighten-1"
                        } else if (res.data.category_id === '5') {
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
            successCert() {
                confirm("인증 성공으로 할거냐고 물어볼건데 나중에 dialog로 바꾸기")
                this.showResultBtn = false;
            },
            failCert() {
                confirm("인증 실패로 할거냐고 물어볼건데 나중에 dialog로 바꾸기")
                this.showResultBtn = false;
            }, 
            getConfirm: function () {
                const certInfo = this.CertInfo
                certInfo.user_id = this.user.user_id
                    if (this.confirmed) {   
                        // 확인 도장 취소하기  
                        this.confirmed = false 
                        axios.post(`http://127.0.0.1:8080/cancleConfirmation`, certInfo)
                        .then((res) => {
                            console.log(res)
                        })
                        .catch((err) => {
                            console.log(err)
                        })
                        
                    } else {
                        // 확인 도장 찍기
                        this.confirmed = true
                        axios.post(`http://127.0.0.1:8080/completeConfirmation`, certInfo)
                        .then((res) => {
                            console.log(res)
                            if (res.data === 2) {
                                alert("씨앗을 성공적으로 키워낸 분을 위해 축하 댓글을 달아주세요!")
                            }
                        })
                        .catch((err) => {
                            console.log(err)
                            console.log('확인도장 찍기 실패')
                        })
                        
                    }
            },
        },
    };
</script>

<style lang="scss" scoped>
    .detail {
        width: 70%;
        // margin: 10% 30% 0% 30%;
    }

    .v-divider {
        margin: 3% !important;
    }

    .result-btn-row {
        justify-content: center;
        margin-top: 3%;
    }

    .like-btn {
        float: right;
        margin-top: 5%;
        margin-bottom: 5%;
    }

    .edit-del-btn {
        justify-content: center;
        margin-top: 15%;
    }

    .img,
    .content,
    .nickname-date-row {
        justify-content: center;
        margin-top: 5%;
    }

    .div-content {
        word-break: break-all; // 영어 범위 벗어남 해결
    }

    .div-img {
        max-width: 100%;
        max-height: 100%;
    }

    .cng-name {
        justify-content: center;
        font-style: italic;
        margin-bottom: 3%;
    }
</style>