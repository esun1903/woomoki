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
            <span v-if="category === '건강'" class="color-health">{{category}}</span>
            <span v-if="category === '생활습관'" class="color-lifestyle">{{category}}</span>
            <span v-if="category === '독서'" class="color-book">{{category}}</span>
            <span v-if="category === '자산'" class="color-assets">{{category}}</span>
            <span v-if="category === '자기계발'" class="color-grown">{{category}}</span>
            <span v-if="category === '취미'" class="color-hobby">{{category}}</span>
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
            <span v-if="category === '건강'" class="color-health">{{category}}</span>
            <span v-if="category === '생활습관'" class="color-lifestyle">{{category}}</span>
            <span v-if="category === '독서'" class="color-book">{{category}}</span>
            <span v-if="category === '자산'" class="color-assets">{{category}}</span>
            <span v-if="category === '자기계발'" class="color-grown">{{category}}</span>
            <span v-if="category === '취미'" class="color-hobby">{{category}}</span>
            씨앗이에요!
          </p>
        </div>
      </div>
    </div>
    <v-data-iterator
      hide-default-footer
      :items="seeds"
      :items-per-page.sync="itemsPerPage"
      :page.sync="page"
      @page-count="pageCount=$event"
      :total-visible="5"
    >
      <template v-slot:default="{items}">
        <div class="cards">
          <v-row>
            <v-col cols="3" class="card" v-for="(seed, index) in items" :key="index">
              <SeedCard :seed="seed"/>
            </v-col>
          </v-row>
        </div>
      </template>
      <template v-slot:footer>
        <v-pagination
          color="light-green lighten-1"
          v-model="page"
          :length="pageCount"
          prev-icon="mdi-menu-left"
          next-icon="mdi-menu-right"
        ></v-pagination>
      </template>
    </v-data-iterator>
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
      page: 1,
      pageCount: 0,
      itemsPerPage: 16,
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
        axios.get(`http://127.0.0.1:8080/categorySort/${cgId_num}`, cgId)
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
  margin-bottom: 5%;
  .searched-option {
    display: flex;
    justify-content: center;
    margin: 1% 0 3% 0;  
    div {
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
  }
  .v-data-iterator {
    .cards {
      width: 100%;
      height: 100%;
      margin-bottom: 10%;
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
  }
}

.color-health {
  color: #29B6F6
}

.color-lifestyle {
  color: #FFA726;
}

.color-book {
  color: #26A69A;
}

.color-assets {
  color: #5C6BC0;
}

.color-grown {
  color: #AB47BC;
}

.color-hobby {
  color: #EC407A;
}

</style>