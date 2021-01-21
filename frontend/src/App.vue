<template>
  <div id="main">
    <UserPage></UserPage>
    <router-view/>
  </div>
</template>

<script>
//김용민
import UserPage from './views/UserPage/UserPage.vue';

export default {
  name: "App",

  components: {
    //김용민
    UserPage
  },

  data: () => ({
    //
  })
};
</script>
