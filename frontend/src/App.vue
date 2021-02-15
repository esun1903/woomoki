<template>
  <v-app id="app" class="font-app">
    <Navbar></Navbar>
    <v-main class="main-css">
        <router-view :key="$route.fullPath"></router-view>
    </v-main>
    <div class="loader">
      <UpPage></UpPage>
      <Chat v-if="this.isLogin"></Chat>
    </div>
    <Footer></Footer>
  </v-app>
</template>


<script>
import Navbar from "@/components/Navbar/Navbar.vue"
import Footer from "@/components/Footer/Footer.vue"
import Chat from "@/views/Chat/Chat.vue";
import UpPage from "@/views/Chat/UpPage.vue";

export default {
  name: "App",
  components: {
    Navbar,
    Footer,
    Chat,
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

// 웹폰트 사용
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Hi+Melody&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Poor+Story&display=swap');

.loader {
  z-index: 5;
}

.main {
  margin-top: 7.1vh;
}

.font-app {
  // font-family: 'Jua', sans-serif;
  // font-family: 'Do Hyeon', sans-serif;
  // font-family: 'Nanum Gothic Coding', monospace;
  // font-family: 'Noto Serif KR', serif;
  // font-family: 'Sunflower', sans-serif;
  // font-family: 'Hi Melody', cursive;
  // font-family: 'Gamja Flower', cursive;
  // font-family: 'Poor Story', cursive;
  font-family:'훈새마을운동', 'HoonSaemaulundongR', HoonSaemaulundong
}

// 로컬에 저장되어 있는 것 사용
@font-face {
  font-family: HoonSaemaulundong;
  src: local("HoonSaemaulundongR"),
  local("HoonSaemaulundongR"),
  url(HoonSaemaulundongR.ttf);
  font-weight: bold;
}


</style>
