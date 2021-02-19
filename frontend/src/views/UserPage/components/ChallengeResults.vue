<template>
  <v-row class="challenge-results results">
    <v-col @click="SeedJoin">
      <v-icon 
        class="challenge-icon"
        size="40"
        color="#AED864"
        >
        fas fa-user-plus</v-icon>
      <div class="challenge-state">참가</div>
    </v-col>
    <v-col @click="SeedSuccess">
      <v-icon 
        class="challenge-icon"
        size="40"
        color="#AED864"
        >
        fas fa-seedling</v-icon>
      <div class="challenge-state">성공</div>
    </v-col>
    <v-col @click="SeedFail">
      <v-icon 
        class="challenge-icon"
        size="40"
        color="#AED864"
        >
        fas fa-dizzy</v-icon>
      <div class="challenge-state">실패</div>
    </v-col>
    <v-col @click="SeedCreated">
      <v-icon 
        class="challenge-icon"
        size="40"
        color="#AED864"
        >
        fas fa-pencil-alt</v-icon>
      <div class="challenge-state">개설</div>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "ChallengeResults",
  data: function () {
    return {
    }
  },
  methods: {
    SeedJoin: function () {
      this.$router.push({ name: 'SeedJoin' })
    },
    SeedSuccess: function () {
      this.$router.push({ name: 'SeedSuccess' })
    },
    SeedFail: function () {
      this.$router.push({ name: 'SeedFail' })
    },
    SeedCreated: function () {
      this.$router.push({ name: 'SeedCreated' })
    },
  }
}
</script>

<style scoped>

.challenge-results {
  display: -webkit-flex;
  display: flex;
  -webkit-justify-content: space-around;
  justify-content: space-around;
  margin-top: 2rem;
}

.challenge-results .challenge-state{
  margin-bottom: 1rem;
  font-size: 1rem;
  display: flex;
  justify-content: center;
}

.challenge-results .challenge-count{
  font-size: 1rem;
  display: flex;
  justify-content: center;
}

.challenge-results .challenge-icon{
  margin-bottom: 1rem;
  display: flex;
  justify-content: center;
}

.results {
  cursor: pointer;
}

</style>