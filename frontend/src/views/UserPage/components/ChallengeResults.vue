<template>

  <v-row class="challenge-results">
    <v-col @click="goToListPage">
      <v-icon 
        class="challenge-icon"
        size="50"
        color="blue lighten-2"
        >
        fas fa-running</v-icon>
      <div class="challenge-state">참가</div>
      <div class="challenge-count">5</div>
    </v-col>
    <v-col @click="goToListPage">
      <v-icon 
        class="challenge-icon"
        size="50"
        color="success lighten-2"
        >
        fas fa-check-circle</v-icon>
      <div class="challenge-state">성공</div>
      <div class="challenge-count">5</div>
    </v-col>
    <v-col @click="goToListPage">
      <v-icon 
        class="challenge-icon"
        size="50"
        color="red lighten-2"
        >
        fas fa-ban</v-icon>
      <div class="challenge-state">실패</div>
      <div class="challenge-count">5</div>
    </v-col>
    <v-col>
      <v-icon 
        class="challenge-icon"
        size="50"
        color="yellow lighten-2"
        >
        fas fa-pencil-alt</v-icon>
      <div class="challenge-state">개설</div>
      <div class="challenge-count">5</div>
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
    goToListPage: function () {
      console.log("go to list")
      // this.$router.push()
    }
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

</style>