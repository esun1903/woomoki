<template>
    <div>
        <validation-observer v-slot="{ invalid }" ref="observer">
            <v-form @submit.prevent="writeComment">
                <validation-provider v-slot="{ errors }" rules="required">
                    <v-textarea outlined v-model="comment" :error-messages="errors" label="댓글"></v-textarea>
                </validation-provider>
                <div id="insert-btn-div">
                    <v-btn color="warning" id="insert-btn" :disabled="invalid" @click="writeComment">
                        등록하기
                    </v-btn>
                </div>
            </v-form>
        </validation-observer>
    </div>
</template>

<script>
    import {
        required
    } from 'vee-validate/dist/rules'
    import {
        extend,
        ValidationProvider,
        setInteractionMode,
        ValidationObserver
    } from 'vee-validate'

    setInteractionMode('eager')

    extend('required', {
        ...required,
        message: '{_field_}은(는) 필수 항목입니다'
    })
    import axios from "axios";
    export default {
        name: 'CommentInsert',
        components: {
            ValidationObserver,
            ValidationProvider
        },
        directives: {},
        data() {
            return {
                comment: null,
            };
        },
        mounted() {

        },
        methods: {
            async writeComment() {
                //  댓글 유효성 검사하기

                const valid = await this.$refs.observer.validate()
                if (valid) {
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
                }
            }

        },
    };
</script>

<style lang="scss" scoped>
    #insert-btn {
        float: right;
    }

    #insert-btn-div {
        margin-bottom: 5%;
    }
</style>