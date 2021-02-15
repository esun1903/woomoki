<template>
  <v-container>
    <div class="recommend-title">
        <div v-if="getCheckLogin" class="checkLogin">
          <span class="nickname">{{ this.user.nickname }} </span>
          <span v-if="getCheckFavoriteCategory" class="checkFavCategory">님, 이런 씨앗은 어때요?</span>
          <span v-else class="checkFavCategory">님, 관심 카테고리를 설정해주시면 씨앗을 추천해드릴게요!</span>
        </div>
        <span v-else class="notLogined">익명의 방문자님, 로그인 먼저 진행해주시겠어요??</span>
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
    getCheckLogin () {
      return this.$store.getters["UserStore/getCheckLogin"];
    },
    getCheckFavoriteCategory () {
      if (this.seeds.length > 0) {
        return true
      }
    }
  },
  methods: {
  },
  created () {
    this.id = this.user.user_id
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
  },
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
    .checkLogin{
      .nickname{
        color: #AED581;
        font-weight: bold;
        font-size: 2rem;
      }
      .checkFavCategory{
        font-size: 1.2rem;
        font-weight: bold;
        color: rgb(82, 81, 81);
      }
    }
    .notLogined{
      font-size: 1.2rem;
      font-weight: bold;
      color: rgb(82, 81, 81);
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

}

</style>