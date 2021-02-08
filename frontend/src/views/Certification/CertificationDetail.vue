<template>
    <v-container>
        <div class="test">
            <v-row class="title">

                제목제목제목제목제목제목제목제목제목제목


            </v-row>
            <v-row class="img">
                <v-img max-height="500" max-width="500" src="https://picsum.photos/500/500/"></v-img>

            </v-row>
            <v-row class="content">
                내용내용내용

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

            <v-divider></v-divider>
            <CommentInsert />
            <CommentList />
        </div>
    </v-container>
</template>

<script>
    import CommentInsert from '@/views/Certification/components/CommentInsert.vue'
    import CommentList from '@/views/Certification/components/CommentList.vue'


    export default {
        name: 'CertificationDetail',
        components: {
            CommentList,
            CommentInsert
        },
        directives: {},
        data() {
            return {
                dialog: false,
            };
        },
        mounted() {

        },
        methods: {
            deleteCert() {
                // 해당 챌린지 인증글 리스트로 돌아가기
                this.$router.push("/");
            }
        },
    };
</script>

<style lang="scss" scoped>
    .test {
        margin-top: 10%
    }

    .v-divider {
        margin: 3% !important;
    }

    .like-btn {
        float: right;
    }

    .title,
    .img,
    .content,
    .edit-del-btn {
        justify-content: center;
    }
</style>