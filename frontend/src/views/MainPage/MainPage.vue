<template>
  <div id="main-page">
    <SearchBar id="mainpage-component"/>

    <CategoryButtons id="mainpage-component"/>

    <div v-if="isSearching">
      <ChallengeSearched id="mainpage-component-result" @searched-keyword="searchedKeyword"/> 
    </div>

    <div v-show="isNotSearching">
      <ChallengeRecommended id="mainpage-component-result"/>
      <ChallengeFamous id="mainpage-component-result" @category-searched="categorySearched"/>
      <ChallengeNew id="mainpage-component-result" @category-searched="categorySearched"/> 
    </div>

  </div>
</template>

<script>
import SearchBar from "@/views/MainPage/components/SearchBar.vue"
import CategoryButtons from "@/views/MainPage/components/CategoryButtons.vue"
import ChallengeSearched from "@/views/MainPage/components/ChallengeSearched.vue"
import ChallengeRecommended from "@/views/MainPage/components/ChallengeRecommended.vue"
import ChallengeFamous from "@/views/MainPage/components/ChallengeFamous.vue"
import ChallengeNew from "@/views/MainPage/components/ChallengeNew.vue"
export default {
  name: 'MainPage',
  components: { SearchBar, CategoryButtons, ChallengeSearched, ChallengeRecommended, ChallengeFamous, ChallengeNew },
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
#mainpage-component {
  display: flex;
  justify-content: center;
  height: 98px;
  margin-top: 50px;
}
#mainpage-component-result {
  display: block;
  justify-content: center;
  margin-top: 40px;
  margin-bottom: 800px;
}
</style>