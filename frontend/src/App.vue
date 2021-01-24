<template>
  <div id="app">
    <Navbar/>
    <router-view/>
  </div>
</template>

<script>
// import HelloWorld from "./components/HelloWorld";
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
