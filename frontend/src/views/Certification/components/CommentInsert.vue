<template>
    <div>
        <v-textarea outlined v-model="comment" label="댓글"></v-textarea>
        <div id="insert-btn-div">
            <v-btn color="warning" id="insert-btn" @click="writeComment">
                등록하기
            </v-btn>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    export default {
        name: 'CommentInsert',
        components: {},
        directives: {},
        data() {
            return {};
        },
        mounted() {

        },
        methods: {
            writeComment() {
                //  댓글 유효성 검사하기

                const certId = this.$route.params.certId;

                const CommentForm = {
                    cert_id: certId,
                    user_id: this.$store.state.UserStore.user.user_id,
                    content: this.comment,
                }
                console.log(CommentForm);
                axios.post("http://localhost:8080/insertComment", CommentForm)
                .then(res => {
                    console.log(res);
                   this.$router.go(this.$router.currentRoute);
                })
                .catch(err => {
                    console.log(err);
                });

                // 해당 게시글로 다시 요청
                // this.$router.push("/");

            },
        },
    };
</script>

<style lang="scss" scoped>
    #insert-btn {
        float: right;
    }
    #insert-btn-div{
        margin-bottom:5%;
    }
</style>