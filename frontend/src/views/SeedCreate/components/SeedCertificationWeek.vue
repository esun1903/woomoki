<template>
  <v-col class="pa-0">
    <v-select
      v-model="value"
      color="#AED864"
      :items="week"
      label="몇 주 짜리 씨앗인가요? (최대 14주)"
      suffix="주"
      outlined
    ></v-select>
  </v-col>
</template>

<script>
export default {
  name: "SeedCertificationMonth",
  data: function () {
    return {
      value: 0,
      week: [1, 2, 3, 4, 5 ,6, 8, 9, 10, 11, 12, 13, 14],
      monthRules: [
        v => !!v || '필수 항목입니다',
        v => this.valiteateCount(v) || '인증 횟수는 숫자만 입력해야합니다',
        v => v.length <= 2 || '인증 횟수는 2자 이하로 적어주세요',
      ],
    }
  },
  methods: {
    valiteateCount: function(count) {
      const re = /^[0-9]*$/
      return re.test(count)
    },
  },
  watch: {
    value: function () {
      this.$emit('transferWeek', this.value)
    }
  }
}
</script>

<style scoped>

</style>