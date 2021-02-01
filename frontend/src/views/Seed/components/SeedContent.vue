<template>
  <v-row>
      <v-textarea
        v-model="content"
        :rules="contentRules"
        :counter="200"
        outlined
        required
        label="씨앗에 물주는 방법"
        value="어떻게 해야 하나요?"
      ></v-textarea>
    </v-row>
</template>

<script>
export default {
  name: "SeedContent",
  data: function () {
    return {
      content: "",
      contentRules: [
        v => !!v || '내용은 필수 항목입니다',
        v => v.length >= 10 || '참여자들을 위해 내용을 자세히 적어주세요',
        v => v.length <= 200 || '200자를 초과할 수 없습니다',
      ],
    }
  }
}
</script>

<style scoped>

</style>