<template>
  <v-container>
    <div class="searched-keyword">
      <p>
        <span>{{ searched }}</span>
        에 관련된 씨앗이에요.
      </p>
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
          <v-row dense>
            <v-col cols="3" class="card" v-for="(seed, index) in items" :key="index">
              <SeedCard :seed="seed"/>
            </v-col>
          </v-row>
        </div>
      </template>
      <template v-slot:footer>
        <v-pagination
          color="light-green lighten-2"
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
import {mapState} from "vuex";
export default {
  name: 'SearchedPage',
  components: { SeedCard },
  directives: {  },
  data() {
    return {
      searched: this.$route.query.keyword,
      seeds: [],
      page: 1,
      pageCount: 0,
      itemsPerPage: 16,
    };
  },
  mounted() {
    
  },
  computed: {
   ...mapState('UserStore', ['user']),
  },
  methods: {
    arrangeRule: function (val) {
      axios.get("http://127.0.0.1:8080/allChallenge")
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
          console.log(this.seeds)
        })
        .catch((err) => {
          console.log(err)
        })
    }  
  },
  created () {
    this.arrangeRule(this.searched)
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