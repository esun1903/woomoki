<template>
  <v-container>
    <div class="searched-option">
      <div v-if="option==='인기'">
        <div v-if="category==='전체'">
          <p> 요즘 인기 있는 씨앗이에요!</p>          
        </div>
        <div v-else>
          <p>
            요즘 인기 있는
            <span>{{category}}</span>
            씨앗이에요!
          </p>
        </div>
      </div>
      <div v-else>
        <div v-if="category==='전체'">
          <p> 새로 들어온 씨앗이에요!</p>          
        </div>
        <div v-else>
          <p>
            새로 들어온
            <span>{{category}}</span>
            씨앗이에요!
          </p>
        </div>
      </div>
    </div>
    <div class="cards">
      <v-row dense>
        <v-col cols="3" class="card" :pagination.sync="pagination"  v-for="(seed, index) in seeds" :key="index">
          <SeedCard :seed="seed"/>
        </v-col>
      </v-row>
    </div>
    <div class="pagination">
      <v-pagination
        v-model="pagination.page"
        :length="pages"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
      ></v-pagination>
    </div>
  </v-container>
</template>

<script>
import SeedCard from "@/views/MainPage/components/SeedCard.vue";
import axios from "axios";
export default {
  name: 'SeedSearched',
  components: { SeedCard },
  directives: {  },
  props: {
    "category": String,
    "option": String,
  },
  data() {
    return {
      seeds: [],
      pagination: {}


    };
  },
  mounted() {
    
  },
  methods: {
    arrangeRule: function (cat, opt) {
      console.log(cat)
      if (cat === 0) {
        this.allCategory(opt)
      } else {
        const cgId_num = cat
        const cgId = {};
        cgId["cgId"] = cgId_num
        axios.get(`http://localhost:8080/categorySort/${cgId_num}`, cgId)
          .then((res) => {
            const seeds = res.data
            console.log(seeds)
            if (opt == "인기") {
              seeds.sort(function(a,b) {
                return a.like_cnt > b.like_cnt ? -1 : a.like_cnt < b.like_cnt ? 1 : 0;
              })
            } else {
              seeds.sort(function(a,b) {
                return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
              })
            }
            // seeds.splice(8)
            this.seeds = seeds
          })
          .catch((err) => {
            console.log(err)
          })
        }
    },
    allCategory: function (opt) {
      axios.get("http://localhost:8080/allChallenge")
        .then((res) => {
          const seeds = res.data
          console.log(seeds)
          if (opt == "인기") {
            seeds.sort(function(a,b) {
              return a.like_cnt > b.like_cnt ? -1 : a.like_cnt < b.like_cnt ? 1 : 0;
            })
          } else {
            seeds.sort(function(a,b) {
              return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
            })
          }
          // seeds.splice(8)
          this.seeds = seeds
        })
        .catch((err) => {
          console.log(err)
        })
    }
  },
  created () {
    this.arrangeRule(this.selectedCategory, this.option)
  },
  computed: {
    selectedCategory: function () {
      if (this.category === '전체') {
        return 0
      }else if (this.category === '건강') {
        return 1
      } else if (this.category === '생활습관') {
        return 2
      } else if (this.category === '독서') {
        return 3
      } else if (this.category === '자산') {
        return 4
      } else if (this.category === '자기계발') {
        return 5
      } else {
        return 6
      }
    },
    pages () {
      return this.pagination.rowsPerPage ? Math.ceil(this.seeds.length / this.pagination.rowsPerPage) : 0
    }
  },
  watch: {
    category(newVal, oldVal) {
      if (newVal !== oldVal) {
        return newVal
      }

    },
    option(newVal, oldVal) {
      if (newVal!== oldVal) {
        this.arrangeRule(this.selectedCategory, newVal)
        return newVal
      }
    },
    selectedCategory(newVal, oldVal) {
      if (newVal!== oldVal) {
        this.arrangeRule(newVal, this.option)
        return newVal
      }
    },
  }
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 100%;
  margin-bottom: 10%;
  .searched-option{
    display: flex;
    justify-content: center;
    margin: 1% 0;
    div {
      p {
        font-size: 1.5em;
        font-weight: bold;
        span {
          font-size: 1.2em;
        }
      }
    }
  }
  .cards {
    width: 100%;
    height: 100%;
    margin-bottom: 500px !important;
    .row {
      .card {
        padding: 1%;
        width: 100%;
        height: 50%;
        .v-card {
          width: 100%;
          height: 50%;
        }
      }    
    }
  }
  .pagination {
  }
}

</style>