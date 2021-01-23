<template>
  <div id="main">
    <!-- <Navbar></Navbar> -->
    <Login></Login>
    <router-view/>
  </div>
</template>

<script>
// import HelloWorld from "./components/HelloWorld";
import Login from "@/views/Login/Login";

export default {
  name: "App",

  components: {
Login,
  },

  data: () => ({
    //
  })
};
</script>
