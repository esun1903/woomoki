<template>
<<<<<<< HEAD
  <v-app>
    <Navbar class="test"></Navbar>
    <router-view></router-view>
    <Footer></Footer>
  </v-app>
=======
<v-app id="app">
  <Navbar></Navbar>
      <router-view></router-view>
  <Footer></Footer>
</v-app>
>>>>>>> ac5333e9bb745305f6ab58dc51da501c444cef3c
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
