	
<template>
  <v-app>
    <Navbar id="nav-bar"></Navbar>
    <Footer class="footer"></Footer>
    <router-view />
  </v-app>
</template>

<script>
import Navbar from "@/components/Navbar/Navbar.vue"
import Footer from "@/components/Footer/Footer.vue"

export default {
  name: "App",
  components: {
    Navbar,
    Footer,
  },

  data: () => ({
    //
  })
};
</script>

<style>
.footer {
  position: relative;
  bottom: -1200px;
  width: 100%;
  z-index: 0;
}
#nav-bar {
  /* margin-bottom: 50px; */
}
</style>
