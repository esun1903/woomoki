<template>
  <!-- snackbar 또는 dialog-->
  <v-col>
    <v-checkbox
        color="light-green lighten-2"
        v-model="value"
        :label="'참여자가 있다면 중간에 씨앗을 없앨 수 없으니 신중하게 정해주세요!'"
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