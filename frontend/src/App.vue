	
<template>
  <v-app>
    <Navbar></Navbar>
    <v-main>
      <router-view></router-view>
    </v-main>
    <Footer class="footer"></Footer>
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
  
  /* position: absolute; */
  /* bottom: -1200px; */
  /* width: 100%; */
  z-index: 1;
}

</style>
