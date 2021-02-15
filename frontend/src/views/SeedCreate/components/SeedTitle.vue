<template>
  <v-col class="pa-0">
    <v-text-field
      placeholder="EX) 매일매일 코딩 공부하기"
      color="light-green lighten-1"
      v-model="title"
      :rules="titleRules"
      :counter="20"
      label="씨앗 이름"
      outlined
      required
      @input="transferTitle"
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
  },
  methods: {
    transferTitle: function () {
      this.$emit('transferTitle', this.title)
    }
  }
}
</script>

<style scoped>

</style>