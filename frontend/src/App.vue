<template>
<div>
  <Navbar class="test"></Navbar>
  <v-app>
      <router-view></router-view>
  </v-app>
  <Footer></Footer>
</div>
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