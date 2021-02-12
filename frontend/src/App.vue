<template>
<v-app id="app">
  <Navbar></Navbar>
  <v-main>
      <router-view :key="$route.fullPath"></router-view>
  </v-main>
  <div class="loader">
    <Chat></Chat>
  </div>
  <Footer></Footer>
</v-app>
</template>


<script>
import Navbar from "@/components/Navbar/Navbar.vue"
import Footer from "@/components/Footer/Footer.vue"
import Chat from "@/views/Chat/Chat.vue";

export default {
  name: "App",
  components: {
    Navbar,
    Footer,
    Chat
  },

  data: function () {
    return {
      // login: false,
    } 
  },
  computed: {
  },
  methods: {

  }
};
</script>

<style>

.loader {
  z-index: 5;
}

</style>
