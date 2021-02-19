<template>
  <v-col class="pa-0">
    <v-slider
      v-model="value"
      label="최대 인원"
      color="grey"
      track-color="grey"
      thumb-color="#AED864"
      :thumb-label="true"
      min="0"
      max="50"
    ></v-slider>
  </v-col>
</template>

<script>
export default {
  name: "SeedPeople",
  data: function () {
    return {
      value: 0,
      people: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    }
  },
  methods: {
    transferPeople: function () {
      this.$emit('transferPeople', this.value)
    }
  },
  watch: {
    value: function() {
      this.transferPeople();
    }
  }
}
</script>

<style scoped>

</style>