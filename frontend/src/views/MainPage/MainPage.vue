<template>
  <div>
    <div v-if="isSearching">
      <SeedSearched id="mainpage-component-result"/> 
    </div>
    <div v-else>
      <div class="selectbox1">
        <v-select
          v-model="valSelect1"
          :items="select1"
          label="인기/신규"
          dense
          outlined
        ></v-select>
      </div>
      <div class="selectbox2">
        <v-select
          v-model="valSelect2"
          :items="select2"
          label="카테고리"
          dense
          outlined
        ></v-select>
      </div>
      <SeedRecommended id="mainpage-component-result"/>
      <SeedFamous id="mainpage-component-result"/>
      <SeedNew id="mainpage-component-result"/>
    </div>
  </div>
</template>

<script>
import SeedSearched from "@/views/MainPage/components/SeedSearched.vue"
import SeedRecommended from "@/views/MainPage/components/SeedRecommended.vue"
import SeedFamous from "@/views/MainPage/components/SeedFamous.vue"
import SeedNew from "@/views/MainPage/components/SeedNew.vue"
export default {
  name: 'MainPage',
  components: { SeedSearched, SeedRecommended, SeedFamous, SeedNew },
  directives: {  },
  data() {
    return {
      isSearching: false,
      isNotSearching: true,
      searchedKeyword: [],
      select1: ['인기', '신규'],
      select2: ['건강', '생활습관', '독서', '자산', '자기계발', '취미'],
      valSelect1: '',
      valSelect2: '',

    }
  },
  mounted() {
    
  },
  computed() {
    // resultSelect1: function() {
    //   return data.lazyValue
    // }
    // resultSelect2: function() {
    //   return data.lazyValue
    // }
  },
  methods: {
    
  },
};
</script>

<style lang="scss" scoped>
div {
  div {
    .select1{
      .v-input{
        max-width: 7%;
      }
    }
    .select2{
      v-input{
        max-width: 7%;
      }
    }
  }
}
</style>