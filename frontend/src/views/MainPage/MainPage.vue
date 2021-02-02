<template>
  <div id="fullpage">
    <div v-if="isSearching">
      <div class="section">
        <ChallengeSearched id="mainpage-component-result"/> 
      </div>
    </div>

    <div v-show="isNotSearching">
      <div class="section">
        <ChallengeRecommended id="mainpage-component-result"/>
      </div>
      <div class="section">
        <ChallengeFamous id="mainpage-component-result"/>
      </div>
      <div class="section">
        <ChallengeNew id="mainpage-component-result"/>
      </div>
 
    </div>
  </div>
</template>

<script>
import ChallengeSearched from "@/views/MainPage/components/ChallengeSearched.vue"
import ChallengeRecommended from "@/views/MainPage/components/ChallengeRecommended.vue"
import ChallengeFamous from "@/views/MainPage/components/ChallengeFamous.vue"
import ChallengeNew from "@/views/MainPage/components/ChallengeNew.vue"
export default {
  name: 'MainPage',
  components: { ChallengeSearched, ChallengeRecommended, ChallengeFamous, ChallengeNew },
  directives: {  },
  data() {
    return {
      isSearching: false,
      isNotSearching: true,
      searchedKeyword: [],
    }
  },
  mounted() {
    
  },
  methods: {
    
  },
};
</script>

<style lang="scss" scoped>
// #mainpage-component {
//   display: flex;
//   justify-content: center;
//   height: 98px;
//   margin-top: 50px;
// }
// #mainpage-component-result {
//   display: block;
//   justify-content: center;
//   margin-top: 40px;
//   margin-bottom: 800px;
// }
</style>