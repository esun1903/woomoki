<template>
  <!-- 네이버 밴드에 3x3 카드형태로 바꾸기 -->
  <v-col class="pa-0">
    <v-select
      value="category"
      :items="items"
      label="카테고리 선택"
      dense
      outlined
    ></v-select>
  </v-col>
</template>

<script>
export default {
  name: "SeedCategory",
  data: function () {
    return {
      category: "",
      items: ["건강", "생활습관", "독서", "자산", "자기계발", "취미"]
    }
  }
}
</script>

<style scoped>

</style>