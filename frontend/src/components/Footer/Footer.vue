<template>
  <v-footer
    class="mt-5 mb-5"
    height="auto"
    color="white"
    padless
  >
    <v-row
      justify="center"
      no-gutters
    >
      <v-btn
        v-for="link in links"
        :key="link"
        text
        rounded
        class="my-2"
      >
        {{ link }}
      </v-btn>
      <v-col
        class="white py-4 text-center grey--text"
        cols="12"
      >
        @{{ new Date().getFullYear() }} — <strong>copyright A303</strong>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script>
export default {
  name: "Footer",
  data: function () {
    return {
      links: [
        '홈',
        '팀 소개',
        '서비스',
        '연락처',
        '도움말',
        '약관',
      ],
    }
  }
}
</script>

<style scoped>


</style>