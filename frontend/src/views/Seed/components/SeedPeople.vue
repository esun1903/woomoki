<template>
  <v-col class="pa-0">
    <v-select
      value="value"
      :items="people"
      label="최대 참여 인원 수"
      dense
      outlined
    ></v-select>
        <!-- <v-col class="pr-4">
          <v-slider
            v-model="maxPeople"
            class="align-center"
            :max="max"
            :min="min"
            hide-details
          >
            <template v-slot:append>
              <v-text-field
                v-model="maxPeople"
                class="mt-0 pt-0"
                hide-details
                single-line
                type="number"
              ></v-text-field>
            </template>
          </v-slider>
        </v-col> -->
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
  }
}
</script>

<style scoped>

</style>