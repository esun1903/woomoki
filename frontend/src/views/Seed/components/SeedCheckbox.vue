<template>
  <!-- snackbar 또는 dialog-->
  <v-col>
    <v-checkbox
        color="success lighten-2"
        v-model="value"
        :label="`함께하는 사람이 있으면 씨앗을 없앨 수 없습니다 ${value.toString()}`"
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