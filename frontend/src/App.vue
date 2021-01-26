<template>
  <v-app>
    <MainPage/>
  </v-app>
</template>

<script>
// import HelloWorld from "./components/HelloWorld";
import MainPage from "@/views/MainPage/MainPage.vue";
export default {
  name: "App",

  components: {
    MainPage,
  },

  data: () => ({
    //
  })
};
</script>
