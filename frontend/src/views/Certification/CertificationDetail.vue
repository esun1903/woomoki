<template>
    <v-container>
        <div class="detail">
            <v-row class="user-id">
                {{CertInfo.user_id}}
            </v-row>
            <v-row class="img">
                <v-img :src="photoUrl + CertInfo.img"></v-img>
            </v-row>
            <v-row class="content">
                {{CertInfo.content}}
            </v-row>
            <v-row class="like-btn">
                <v-btn icon color="pink" x-large @click="active = true">
                    <v-icon>mdi-heart</v-icon>
                </v-btn>
            </v-row>
            <v-row class="edit-del-btn">
                <v-btn class="ma-2" outlined fab color="green">
                    <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-dialog v-model="dialog" persistent max-width="290">
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
                            <v-btn color="red darken-1" text @click="dialog = false">
                                삭제
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>

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
        name: 'CertificationDetail',
        components: {
            CommentList,
            CommentInsert
        },
        directives: {},
        data() {
            return {
                CertInfo: [],
                photoUrl:"https://s3.ap-northeast-2.amazonaws.com/cert-photo-upload/",
                dialog: false,
            };
        },
        mounted() {

        },
        created() {
            this.detailCert();
        },
        methods: {
            detailCert: function () {
                // certid 가져오기 -> store에 계속 저장?


                const certId = 21;
                axios.get(`http://localhost:8080//detailcertification/${certId}`)
                    .then((response) => {
                        this.CertInfo = response.data;
                        console.log(response.data)
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            },
            deleteCert() {
                // 해당 챌린지 인증글 리스트로 돌아가기
                this.$router.push("/");
            }
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
        margin-top:5%;
        margin-bottom:5%;
    }

    .img,
    .content,
    .edit-del-btn {
        justify-content: center;
        margin-top:15%;
    }

    .user-id {
        justify-content: center;
        font-style: italic;
        margin-bottom: 3%;
    }
</style>