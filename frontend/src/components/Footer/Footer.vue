<template>
  <div></div>
  <!-- <v-bottom-nav
    color="success lighten-1"
    fixed
    app
    class="footer"
  >
    <v-row
      justify="center"
      no-gutters
    >
      <v-btn
        v-for="link in links"
        :key="link"
        color="white"
        text
        rounded
        class="my-2"
      >
        {{ link }}
      </v-btn>
      <v-col
        class="success lighten-2 py-4 text-center white--text"
        cols="12"
      >
        {{ new Date().getFullYear() }} — <strong>Produce 303</strong>
      </v-col>
    </v-row>
  </v-bottom-nav> -->
</template>

<script>
export default {
  name: "Footer",
  data: function () {
    return {
      links: [
        'Home',
        'About Us',
        'Team',
        'Services',
        'Blog',
        'Contact Us',
      ],
    }
  }
}
</script>

<style scoped>


</style>