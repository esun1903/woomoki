<template>
  <v-col class="pa-0">
    <v-text-field
      v-model="title"
      :rules="titleRules"
      :counter="20"
      label="씨앗 이름"
      outlined
      required
    ></v-text-field>
  </v-col>
</template>

<script>
export default {
  name: "SeedTitle",
  data: function () {
    return {
      title: "",
      titleRules: [
        v => !!v || '제목은 필수 항목입니다',
        v => v.length >= 5 || '제목은 5자 이상 적어주세요',
        v => v.length <= 20 || '제목은 20자 이하로 적어주세요',
      ],
    }
  }
}
</script>

<style scoped>

</style>