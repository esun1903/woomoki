<template>
  <v-col>
    <v-checkbox
        color="#AED864"
        v-model="value"
        :label="'씨앗을 삭제할 경우 참여자들의 보살핌 후기도 모두 지워집니다'"
    ></v-checkbox>
  </v-col>
</template>

<script>
export default {
  name: "SeedCheckbox",
  data: function () {
    return {
      value: false,
      sheet: false,
    }
  },
  methods: {
    transferCheckbox: function () {
      this.$emit('transferCheckbox', this.value)
    }
  },
  watch: {
    value: function () {
      this.transferCheckbox();
    }
  }
}
</script>

<style scoped>

</style>