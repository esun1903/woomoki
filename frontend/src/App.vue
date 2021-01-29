	
<template>
  <v-app>
  <Navbar class="test"></Navbar>
      <router-view></router-view>
  <Footer></Footer>
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

.test {
  margin-bottom: 60px;
}

</style>
