<template>
  <div id="main">
    <UserPageEdit></UserPageEdit>
    <router-view/>
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
