<template>
  <div id="main">
    <Navbar></Navbar>
    <router-view/>
  </div>
</template>


<script>
import Navbar from "@/components/Navbar/Navbar.vue";

export default {
  name: "App",

  components: {
    Navbar,
  },

  data: () => ({
    //
  })
};
</script>
