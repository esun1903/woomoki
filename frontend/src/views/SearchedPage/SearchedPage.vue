<template>
  <v-container>
    <div class="searched-keyword">
      <p>
        <span>{{ searched }}</span>
        에 관련된 검색 결과입니다.
      </p>
    </div>
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
import {mapState} from "vuex";
export default {
  name: 'SearchedPage',
  components: { SeedCard },
  directives: {  },
  data() {
    return {
      searched: this.$route.query.keyword,
      seeds: []
    };
  },
  mounted() {
    
  },
  computed: {
   ...mapState('UserStore', ['user']),
  },
  methods: {
    arrangeRule: function (val) {
      axios.get("http://localhost:8080/allChallenge")
        .then((res) => {
          const seeds = res.data
          const searchedSeeds=[]
          for (const i in seeds) {
            const seed = seeds[i]
            const seedTitle = seed["title"]
            if (seedTitle.indexOf(val) != -1) {
              searchedSeeds.push(seed)
            }
          }
          this.seeds = searchedSeeds
        })
        .catch((err) => {
          console.log(err)
        })
    }  
  },
  created () {
  },
  watch: {
    '$route' () {
      this.searched = this.$route.query.keyword
      this.arrangeRule(this.searched)
    },
  }
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 89.5vh;
  margin-bottom: 10%;
  .searched-keyword {
    display: flex;
    justify-content: center;
    margin: 1% 0;
    p {
      font-size: 1.5em;
      font-weight: bold;
      span {
        font-size: 1.2em;
      }
    }
  }
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