<template>
    <div>
        <v-textarea outlined name="comment" label="댓글"></v-textarea>
        <div class="my-2">
            <v-btn color="warning" id="insert-btn" type="submit">
                등록하기
            </v-btn>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'CommentInsert',
        components: {},
        directives: {},
        data() {
            return {
                comment: this.comment
            };
        },
        mounted() {

        },
        methods: {
            submit() {
                //  댓글 유효성 검사하기
                // 해당 게시글로 다시 요청
                this.$router.push("/");

            },
        },
    };
</script>

<style lang="scss" scoped>
    #insert-btn {
        float: right;
    }
</style>