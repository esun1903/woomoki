<template>
  <div id="main">
    <v-app>
      <UserPageEdit></UserPageEdit>
    </v-app>
    <router-view />
  </div>
</template>

<script>
import UserPageEdit from "./views/UserPageEdit/UserPageEdit";

export default {
  name: "App",

  components: {
    UserPageEdit
  },

  data: () => ({
    //
  })
};
</script>
