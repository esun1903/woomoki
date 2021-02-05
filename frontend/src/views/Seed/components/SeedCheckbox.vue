<template>
  <!-- snackbar 또는 dialog-->
  <v-col>
    <v-checkbox
        color="success lighten-2"
        v-model="value"
        :label="'참여자가 있다면 중간에 씨앗을 없앨 수 없어요!'"
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
  }
}
</script>

<style scoped>

</style>