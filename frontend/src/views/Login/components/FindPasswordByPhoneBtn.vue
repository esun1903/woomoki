<template>
  <div class="phone-btn">
    <router-link :to="'/login/findPasswordByPhone'">
      <v-btn class="ma-2" icon color="white">
        <v-icon x-large="true">mdi-cellphone</v-icon>
      </v-btn>
      <p>휴대폰</p>
    </router-link>

  </div>
</template>

<script>
  export default {
    name: 'FindPasswordByPhoneBtn',
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

  .phone-btn {
    margin: 0 auto;
  }
</style>