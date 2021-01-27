<template>
  <div class="challenge-results">
    <div @click="goToListPage">
      <div class="challenge-state">참가</div>
      <v-icon class="challenge-icon">mdi-home</v-icon>
      <div class="challenge-count">5</div>
    </div>
    <div @click="goToListPage">
      <div class="challenge-state">성공</div>
      <v-icon class="challenge-icon">mdi-home</v-icon>
      <div class="challenge-count">5</div>
    </div>
    <div @click="goToListPage">
      <div class="challenge-state">실패</div>
      <v-icon class="challenge-icon">mdi-home</v-icon>
      <div class="challenge-count">5</div>
    </div>
    <div>
      <div class="challenge-state">개설</div>
      <v-icon class="challenge-icon">mdi-home</v-icon>
      <div class="challenge-count">5</div>
    </div>
  </div>
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

<style>

.challenge-results {
  display: -webkit-flex;
  display: flex;
  -webkit-justify-content: space-around;
  justify-content: space-around;
  margin-top: 100px;
}

.challenge-results .challenge-state{
  font-size: 30px;
}

.challenge-results .challenge-count{
  font-size: 20px;
  display: flex;
  justify-content: center;
}

.challenge-results .challenge-icon{
  display: flex;
  justify-content: center;
}

</style>