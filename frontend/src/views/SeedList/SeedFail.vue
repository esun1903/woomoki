<template>
  <v-container>
    <div class="recommend-title">
      <p>{{ this.user.nickname }}님이 꽃피우지 못한 씨앗</p>
    </div>
    <v-data-iterator
      no-data-text=""
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
          color="#AED864"
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
import SeedCard from "./components/SeedCard.vue"
import {mapState} from "vuex";
import axios from "axios";
export default {
  name: 'SeedCreated',
  components: { SeedCard },
  directives: {  },
  props: {
  },
  data() {
    return {
      seeds: [],
      page: 1,
      pageCount: 0,
      itemsPerPage: 8,
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
    console.log(id.id)
    axios.get(`http://i4a303.p.ssafy.io/api/challengeResultSort/${id.id}/1`)
      .then((res) => {
        const seeds = res.data
        console.log(seeds)
        seeds.sort(function(a,b) {
          return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
        })
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
  height: 100%;
  .recommend-title {
    display: flex;
    justify-content: center;
    margin: 1% 0 3% 0;  
    p {
      font-size: 1.5em;
      font-weight: bold;
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

</style>
