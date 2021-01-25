<template>
  <div id="main">
    <Footer></Footer>
    <router-view/>
  </div>

</template>

<script>
import Footer from "./components/Footer/Footer";

export default {
  name: "App",
  components: {
    Footer,
  },

  data: () => ({
    //
  })
};
</script>
