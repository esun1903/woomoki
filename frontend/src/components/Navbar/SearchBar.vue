<template>
  <!-- <div id="search-bar">
    <form class="search" @submit="getSearchResult">
      <input type="text" class="searchTerm" placeholder="어떤 씨앗을 심을까요?" @input="searchQuery = $event.target.value" autocomplete="on">
      <v-btn type="submit" class="searchButton">
        <v-icon>mdi-cloud-search-outline</v-icon>
      </v-btn>
    </form>
  </div> -->
  <v-autocomplete
  v-model="select"
  :loading="loading"
  :items="items"
  :search-input.sync="search"
  cache-items
  class="mx-4"
  flat
  hide-no-data
  hide-details
  label="검색"
  solo-inverted
></v-autocomplete>
</template>

<script>


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
        states: [
          'Alabama',
          'Alaska',
          'American Samoa',
          'Arizona',
          'Arkansas',
          'California',
          'Colorado',
          'Connecticut',
          'Delaware',
          'District of Columbia',
          'Federated States of Micronesia',
          'Florida',
          'Georgia',
          'Guam',
          'Hawaii',
          'Idaho',
          'Illinois',
          'Indiana',
          'Iowa',
          'Kansas',
          'Kentucky',
          'Louisiana',
        ],
      }
    },
    mounted() {
    
    },
    watch: {
      search (val) {
        val && val !== this.select && this.querySelections(val)
      },
    },
    methods: {
      querySelections (v) {
        this.loading = true
        // Simulated ajax query
        setTimeout(() => {
          this.items = this.states.filter(e => {
            return (e || '').toLowerCase().indexOf((v || '').toLowerCase()) > -1
          })
          this.loading = false
        }, 500)
      },
    },
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