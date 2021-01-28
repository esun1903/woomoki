<template>
    <div class="email-btn">
        <router-link :to="'/login/findPasswordByEmail'">
            <v-btn class="ma-2" icon color="white">
        <v-icon x-large="true">mdi-email</v-icon>
      </v-btn>
      <p>이메일</p>
        </router-link>
    </div>
</template>

<script>
    export default {
        name: 'FindPasswordByEmailBtn',
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
    a {
        text-decoration: none;
        color: #ffffff !important;
    }

    .email-btn {
        margin: 0 auto;
    }
</style>