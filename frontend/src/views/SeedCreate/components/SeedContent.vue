<template>
  <v-col class="pa-0">
    <v-textarea
      color="#AED864"
      placeholder="EX) 코딩을 꾸준히 하지 않으면 까먹기 쉽지 않나요? 각자 조금씩이라도 코딩을 하고 매일매일 커밋하는 씨앗을 심고 싶어요! 함께하실 가꾸미님 구합니다!!"
      v-model="content"
      :rules="contentRules"
      :counter="30000"
      outlined
      no-resize
      required
      label="씨앗에 보살피는 방법"
      value="어떻게 해야 하나요?"
      @input="transferContent"
    ></v-textarea>
  </v-col>
</template>

<script>
export default {
  name: "SeedContent",
  data: function () {
    return {
      content: "",
      contentRules: [
        v => !!v || '내용은 필수 항목입니다',
        v => v.length >= 50 || '참여자들을 위해 내용을 자세히 적어주세요',
        v => v.length <= 30000 || '10000자를 초과할 수 없습니다',
      ],
    }
  },
  methods: {
    transferContent: function () {
      this.$emit('transferContent', this.content)
    }
  }
}
</script>

<style scoped>

</style>