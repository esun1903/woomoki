<template>
  <v-col class="pa-0">
    <v-text-field
      placeholder="EX) 3000"
      color="success"
      v-model="joinDeposit"
      :rules="joinDepositRules"
      :counter="5"
      label="참여 금액(원)"
      suffix="원"
      outlined
      required
      @input="transferDeposit"
    ></v-text-field>
  </v-col>
</template>

<script>
export default {
  name: "SeedDeposit",
  data: function () {
    return {
      joinDeposit: "",
      joinDepositRules: [
        v => !!v || '참여 금액은 필수 항목입니다',
        v => v.length <= 5 || '금액은 10만원이하로 설정해주세요',
        v => /^[0-9]+$/.test(v) || '숫자만 입력가능합니다',
      ],
    }
  },
  methods: {
    transferDeposit: function () {
      this.$emit('transferDeposit', this.joinDeposit)
    }
  }
}
</script>

<style scoped>

</style>