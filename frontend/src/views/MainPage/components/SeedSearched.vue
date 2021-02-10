<template>
  <v-container>
    <div class="cards">
      <v-row dense>
        <v-col cols="3" class="card" v-for="(seed, index) in seeds" :key="index">
          <SeedCard :seed="seed"/>
        </v-col>
      </v-row>
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
    };
  },
  mounted() {
    
  },
  methods: {
    arrangeRule: function (cat, opt) {
      const cgId_num = cat
      const cgId = {};
      cgId["cgId"] = cgId_num
      console.log(cgId_num)
      console.log(cgId)
      axios.get(`http://localhost:8080/categorySort/${cgId_num}`, cgId)
        .then((res) => {
          const seeds = res.data
          console.log('로직 안')
          console.log(opt)
          if (opt == "인기") {
            seeds.sort(function(a,b) {
              return a.like_cnt > b.like_cnt ? -1 : a.like_cnt < b.like_cnt ? 1 : 0;
            })
          } else {
            seeds.sort(function(a,b) {
              return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
            })
          }
          seeds.splice(8)
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
      if (this.category === '건강') {
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
  height: 89.5vh;
  margin-bottom: 10%;
  .cards {
    width: 100%;
    height: 100%;
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

</style>