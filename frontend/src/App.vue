<template>
  <div id="main">
    <!-- <Navbar></Navbar> -->
    <!-- <Signup></Signup> -->
    <FavoriteCategory></FavoriteCategory>
    <router-view />
  </div>
</template>

<script>
// import Navbar from "@/components/Navbar/Navbar.vue";
import FavoriteCategory from "@/views/Signup/components/FavoriteCategory.vue";
export default {
  name: "App",

  components: {
    // Navbar,
    // Signup
    FavoriteCategory,
  },

  data: () => ({
    //
  })
};
</script>
