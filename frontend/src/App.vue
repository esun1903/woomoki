<template>
  <v-app id="app" class="font-app">
    <Navbar></Navbar>
    <v-main class="main">
        <router-view :key="$route.fullPath"></router-view>
    </v-main>
    <div class="loader">
      <UpPage></UpPage>
    </div>
    <Footer></Footer>
  </v-app>
</template>


<script>
import Navbar from "@/components/Navbar/Navbar.vue"
import Footer from "@/components/Footer/Footer.vue"
import UpPage from "@/views/Chat/UpPage.vue";

export default {
  name: "App",
  components: {
    Navbar,
    Footer,
    UpPage,
  },

  data: function () {
    return {
      isLogin: this.$store.state.UserStore.isLogin,
    } 
  },
  computed: {
  },
  methods: {

  }
};
</script>

<style lang="scss" scoped>

.loader {
  z-index: 5;
}

.main {
  margin-top: 7.1vh;
}

.font-app {
  font-family: 'S-CoreDream-4Regular';
}

// 로컬에 저장되어 있는 것 사용
@font-face {
  font-family: 'S-CoreDream-4Regular';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}


</style>
