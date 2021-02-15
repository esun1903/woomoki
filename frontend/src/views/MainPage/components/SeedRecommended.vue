<template>
  <v-container>
    <div class="recommend-title">
      <p>
        <span class="nickname">{{ this.user.nickname }}</span>
        님, 이런 씨앗은 어때요?
      </p>
    </div>
    <v-data-iterator
      no-data-text="관심카테고리를 설정해주시면 씨앗을 추천해드릴게요"
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
  name: 'SeedRecommended',
  components: { SeedCard },
  directives: {  },
  props: {
  },
  data() {
    return {
      seeds: [],
      page: 1,
      pageCount: 0,
      itemsPerPage: 16,
    };
  },
  computed: {
   ...mapState('UserStore', ['user']),
  },
  methods: {
  },
  created () {
    const id = {};
    id["id"] = this.user.user_id
    console.log(id)
    axios.post("http://127.0.0.1:8080/", id)
      .then((res) => {
        const seeds = res.data
        seeds.sort(function(a,b) {
          return a.like_cnt > b.like_cnt ? -1 : a.like_cnt < b.like_cnt ? 1 : 0;
        })
        // seeds.splice(8)
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

.nickname {
  color: #AED581
}

</style>