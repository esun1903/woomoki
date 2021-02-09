<template>
  <v-container>
    <div class="recommend-title">
      <p>{{ this.user.nickname }}님, 이런 씨앗은 어때요?</p>
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
  name: 'SeedRecommended',
  components: { SeedCard },
  directives: {  },
  props: {
  },
  data() {
    return {
      seeds: []
    };
  },
  mounted() {
    
  },
  computed: {
   ...mapState('UserStore', ['user']),
  },
  methods: {
    
  },
  created () {
    const id = {};
    id["id"] = this.user.user_id
    axios.post("http://localhost:8080", id)
      .then((res) => {
        const seeds = res.data
        seeds.sort(function(a,b) {
          return a.like_cnt > b.like_cnt ? -1 : a.like_cnt < b.like_cnt ? 1 : 0;
        })
        seeds.splice(20)
        this.seeds = seeds
      })
      .catch((err) => {
        console.log(err)
      })
  }
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 89.5vh;
  margin-bottom: 10%;
  .recommend-title {
    display: flex;
    justify-content: center;
    margin: 1% 0;
    p {
      font-size: 1.5em;
      font-weight: bold;
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