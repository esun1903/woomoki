<template>
  <v-col
    >
      <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="dates"
        transition="scale-transition"
        offset-y
        min-width="auto"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model="dateRangeText"
            label="인증 기간을 선택해주세요"
            prepend-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker
          range
          v-model="dates"
          no-title
          scrollable
        >
          <v-spacer></v-spacer>
          <v-btn
            text
            color="primary"
            @click="menu = false"
          >
            Cancel
          </v-btn>
          <v-btn
            text
            color="primary"
            @click="$refs.menu.save(dates)"
          >
            OK
          </v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>
  <!-- <v-col class="pa-0">
    <v-date-picker
      color="success"
      :full-width="true"
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
  </v-col> -->
</template>

<script>
export default {
  name: "SeedDate",
  data: function () {
    return {
      dates: [],
      // date: new Date().toISOString().substr(0, 10),
      menu: false,
    }
  },
  methods: {
    transferDate: function () {
      this.$emit('transferDate', this.dates)
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