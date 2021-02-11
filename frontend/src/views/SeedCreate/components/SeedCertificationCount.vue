<template>
  <v-col class="pa-0">
    <v-text-field
      color="success"
      v-model="certificationCount"
      :rules="certificationCountRules"
      :counter="2"
      label="하루 인증 횟수"
      outlined
      required
    ></v-text-field>
  </v-col>
</template>

<script>
export default {
  name: "SeedCertificationCount",
  data: function () {
    return {
      certificationCount: "",
      certificationCountRules: [
        v => !!v || '인증 횟수 은 필수 항목입니다',
        v => this.valiteateCount(v) || '인증 횟수는 숫자만 입력해야합니다',
        v => v.length <= 2 || '인증 횟수는 2자 이하로 적어주세요',
      ],
    }
  },
  methods: {
    valiteateCount: function(count) {
      const re = /^[0-9]*$/
      return re.test(count)
    }
  }
}
</script>

<style scoped>

</style>