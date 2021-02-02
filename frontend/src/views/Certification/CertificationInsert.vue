<template>
    <v-file-input label="인증사진" outlined multiple small-chips dense :rules="rules"
        accept="image/png, image/jpeg, image/bmp, image/jpg" prepend-icon="mdi-camera"></v-file-input>
</template>

<script>
    export default {
        name: 'CertificationInsert',
        components: {},
        directives: {},
        data() {
            return {

            };
        },
        mounted() {

        },
        methods: {

        },
    };
</script>

<style lang="scss" scoped>

</style>