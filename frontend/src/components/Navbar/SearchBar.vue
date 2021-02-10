<template>
  <v-autocomplete
  click:append-outer="goSearchedPage"
  v-model="select"
  :loading="loading"
  :items="items"
  :search-input.sync="search"
  append-icon=""
  append-outer-icon="fas fa-search"
  auto-select-first
  color="success"
  background-color="light-grey"
  click="onSearch"
  class="mx-4"
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
      // Simulated ajax query
      setTimeout(() => {
        this.items = this.seeds
        this.loading = false
      }, 500)
    },  
    goSearchedPage (val) {
      console.log('되나?')
      console.log(val)
      this.$router.push({ name: "SearchedPage", query: {id: val}})
      this.select = ""
    }
  },
  created () {
    axios.get("http://localhost:8080/Challenge")
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
.search {
  width: 50%;
  height:60%;
  position: relative;
  display: flex;
  top: 20%;
}

.searchTerm {
  width: 100%;
  border: 3px solid #04915b;
  border-right: none;
  padding: 20px;
  height: 100%;
  left: 40%;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #9DBFAF;
  font-size: 22px;
}

.searchTerm:focus{
  color: #04915b;
}

.searchButton {
  width: 3%;
  height: 100% !important;
  border: 1px solid #04915b;
  background: #04915b !important;
  text-align: center;
  color: #fff;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
  position: relative;
}

/*Resize the wrap to see the search bar change!*/
#search-bar{
  width: 100%;
  position: absolute;
  top: 20%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 50px;
}

</style>