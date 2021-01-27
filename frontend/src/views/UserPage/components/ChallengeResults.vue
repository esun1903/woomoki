<template>
  <div class="challenge-results">
    <div>
      <span class="challenge-state">참가</span>
      <div class="challenge-count">5</div>
    </div>
    <div>
      <span class="challenge-state">성공</span>
      <div class="challenge-count">5</div>
    </div>
    <div>
      <span class="challenge-state">실패</span>
      <div class="challenge-count">5</div>
    </div>
    <div>
      <span class="challenge-state">개설</span>
      <div class="challenge-count">5</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ChallengeResults"
}
</script>

<style scoped>

.challenge-results {
  display: -webkit-flex;
  display: flex;
  -webkit-justify-content: space-around;
  justify-content: space-around;
}

.challenge-results .challenge-state{
  font-size: 30px;
}

.challenge-results .challenge-count{
  font-size: 20px;
  display: flex;
  justify-content: center;
}

</style>