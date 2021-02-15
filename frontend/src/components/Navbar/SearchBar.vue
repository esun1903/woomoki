<template>
  <v-autocomplete
  click:append-outer="goSearchedPage"
  v-model="select"
  :loading="loading"
  :items="items"
  :search-input.sync="search"
  append-icon=""
  append-outer-icon="fas fa-search"
  color="success"
  background-color="light-grey"
  @change="onSearch"
  flat
  hide-no-data
  hide-details
  label="검색"
  solo-inverted
  ></v-autocomplete>
</template>

<script>

import axios from "axios";
export default {
  name: 'SearchBar',
  components: {  },
  directives: {  },
  data () {
    return {
      loading: false,
      items: [],
      search: null,
      select: null,
      seeds: [],
    }
  },
  mounted() {
  
  },
  watch: {
    search (val) {
      val && val !== this.select && this.querySelections(val)
      if (this.search) {
        const keyword = this.search
        this.onSearch(keyword)
      }
    },
    select () {
      if (this.select) {
        const keyword = this.select
        this.goSearchedPage(keyword)
      }
    }
  },
  methods: {
    querySelections () {
      this.loading = true
      setTimeout(() => {
        this.items = this.seeds
        this.loading = false
      }, 500)
    },  
    goSearchedPage (val) {
      this.$router.push({ name: "SearchedPage", query: {keyword: val}})
      this.select = ""
    },
    onSearch (val) {
      this.$router.push({ name: "SearchedPage", query: {keyword: val}})
      this.search = ""
    }
  },
  created () {
    axios.get("http://localhost:8080/challenge")
      .then((res) => {
        this.seeds = res.data
      })
      .catch((err) => {
        console.log(err)
      })
  }

};
</script>

<style lang="scss" scoped>
.v-input{
  max-width: 60%;
}

</style>