<template>
  <v-col class="d-flex justify-center">
    <v-btn
      plain
      text
      :ripple="false"
      class="btn-cursor"
    >
      <div>
        <h2>나의 캐시</h2>
        <div>{{ UserInfo.deposit }}</div>
      </div>
    </v-btn>
  </v-col>
</template>

<script>
export default {
  name: "UserWallet",
  props: {
    UserInfo: [Object, Array],
  },
  data: function () {
    return {
      dialog: {
        dialogm1: "",
        dialog: false,
      },
    }
  }
}
</script>

<style lang="scss" scoped>
.btn-cursor {
  cursor: default;
}
</style>