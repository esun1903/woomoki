<template>
  <v-footer
    class="mt-5 mb-5 footer"
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
        class="home-link my-2"
      >
        <span v-if="link === '홈'">
          <router-link class="home-link" to="/">
            {{ link }}
          </router-link>
        </span>
        <span v-if="link === '팀 소개'">
          {{ link }}
        </span>
        <span v-if="link === '서비스'">
          <a href="https://lab.ssafy.com/s04-webmobile2-sub3/s04p13a303" class="home-link">{{ link }}</a>          
        </span>
        <span v-if="link === '연락처'">
          {{ link }}
        </span>
        <span v-if="link === '도움말'">
          {{ link }}
        </span>
        <span v-if="link === '약관'">
          {{ link }}
        </span>
        
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

a { text-decoration: none; }

.footer {
  z-index: 1;
}

.home-link {
  color: #AED864
}

</style>