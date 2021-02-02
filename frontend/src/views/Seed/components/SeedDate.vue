<template>
  <v-row>
    <v-date-picker
      full-width="100%"
      v-model="dates"
      range
      event-color="green lighten-1"
    ></v-date-picker>

    <v-text-field
      v-model="dateRangeText"
      label="인증할 기간을 정해주세요"
      prepend-icon="mdi-calendar"
      readonly
    ></v-text-field>
  </v-row>
</template>

<script>
export default {
  name: "SeedDate",
  data: function () {
    return {
      dates: [],
    }
  },
  computed: {
    dateRangeText () {
      console.log(this.dates)
      return this.dates.join(' ~ ')
    },
  },
}
</script>

<style scoped>

</style>