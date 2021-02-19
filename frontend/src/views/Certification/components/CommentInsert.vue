<template>
    <div>
        <validation-observer v-slot="{ invalid }" ref="observer">
            <v-form @submit.prevent="writeComment">
                <!-- <validation-provider v-slot="{ errors }" rules="required">
                    <v-text-field color="#AED864" dense outlined v-model="comment" :error-messages="errors" label="댓글" required></v-text-field>
                </validation-provider> -->
                <v-row>
                    <v-col cols="10" class="">
                        <validation-provider v-slot="{ errors }" name="댓글" rules="required">
                            <v-text-field color="#AED864" outlined dense v-model="comment" :error-messages="errors" label="댓글"></v-text-field>
                        </validation-provider>
                    </v-col>

                    <v-col cols="1" class="d-flex justify-center ml-5">
                        <div id="insert-btn-div">
                            <v-btn color="#AED864" class="white--text" id="insert-btn" :disabled="invalid" @click="writeComment">
                                댓글등록
                            </v-btn>
                        </div>
                    </v-col>
                </v-row>
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
        message: '{_field_} 내용을 넣어주세요!'
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

                    axios.post("http://i4a303.p.ssafy.io/api/insertComment", CommentForm)
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
        margin-bottom: 10%;
    }
</style>