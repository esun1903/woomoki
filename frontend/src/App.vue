<template>
<v-app id="app">
  <Navbar></Navbar>
  <v-main>
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
    UpPage
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

</style>
