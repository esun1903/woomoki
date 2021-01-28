<template>
  <div id="search-bar">
    <form class="search" @submit="getSearchResult">
      <input type="text" class="searchTerm" placeholder="어떤 씨앗을 심을까요?" @input="searchQuery = $event.target.value" autocomplete="on">
      <v-btn type="submit" class="searchButton">
        <v-icon>mdi-cloud-search-outline</v-icon>
      </v-btn>
    </form>
  </div>
</template>

<script>


export default {
  name: 'SearchBar',
  components: {  },
  directives: {  },
  data() {
    return {
      keyword: [] 
    };
  },
  mounted() {
    
  },
  methods: {
    getSearchResult: function () {
      this.$emit('searched-keyword', this.keyword)
    }
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