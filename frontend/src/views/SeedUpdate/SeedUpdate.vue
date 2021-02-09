<template>
  <v-container class="container-size">
    <v-row>
      <div>업데이트하는곳</div>
    </v-row>
    <v-row>
      <v-text-field
        v-model="message2"
        solo
        label="Solo"
        clearable
      ></v-text-field>
    </v-row>
    <v-row>
      <v-text-field
        v-model="message2"
        solo
        label="Solo"
        clearable
      ></v-text-field>
    </v-row>
    <v-row>
      <v-text-field
        v-model="message2"
        solo
        label="Solo"
        clearable
      ></v-text-field>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "SeedUpdate",
  data: function () {
    return {
      message2: 'Hey!',
    }
  }
}
</script>

<style lang="scss" scoped>

.container-size {
  width: 50%;
}

</style>