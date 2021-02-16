<template>
  <!-- <v-container>
    <v-row>
      <v-col>
        <v-tabs
          v-model="tab"
          background-color="transparent"
          color="success"
          right
          >
          <v-tab
            style="font-size: 1vw;"
            v-for="item in items"
            :key="item"
            >
            {{ item }}
          </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item
            v-for="item in items"
            :key="item"
            >
            <v-card
              width="100vw"
              color="basil"
              flat
              >
              <v-card-text v-if="item === '모든 유저의 기록'"><FeedAll/></v-card-text>
              <v-card-text v-if="item === '팔로우한 유저의 기록'"><FeedFollower/></v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-col>
    </v-row>
  </v-container> -->
  <v-container class="container-size">
    <span class="feed-title">모든 유저 피드</span>
      <!-- <v-row>
        <v-col> -->
          <!-- <v-banner></v-banner> -->
          <!-- <v-tabs
            height="5vw"
            v-model="tab"
            slider-size="4"
            background-color="transparent"
            color="#AED864"
            grow
            >
            <v-tab
              class="tab"
              v-for="item in items"
              :key="item"
              >
              {{ item }}
            </v-tab>
          </v-tabs>

          <v-tabs-items v-model="tab">
            <v-tab-item
              v-for="item in items"
              :key="item"
              >
              <v-card
                color="basil"
                flat
                > -->

                <!-- <v-card-text v-if="item === '모든 유저의 기록'"> -->
                  <!-- <FeedAll></FeedAll> -->
                  <v-row>
                    <v-col
                      v-for="(card, $idx) in Allslides"
                      :key="$idx"
                      class="d-flex child-flex"
                      cols="4"
                      >
                      <router-link :to="{ name: 'CertificationDetail', params: { cngUserId: card.user_id, cngId: card.cng_id, certId: card.id } }">
                        <v-img
                          :src="card.img"
                          :lazy-src="card.img"
                          aspect-ratio="1"
                          class="grey lighten-2 cursor_test"
                        >
                      <template v-slot:placeholder>
                        <v-row
                          class="fill-height ma-0"
                          align="center"
                          justify="center"
                        >
                          <v-progress-circular
                            indeterminate
                            color="grey lighten-5"
                          ></v-progress-circular>
                        </v-row>
                      </template>
                      </v-img>
                    </router-link>
                    </v-col>
                  </v-row>
                
                <!-- </v-card-text> -->
                  
                <!-- <v-card-text v-if="item === '팔로우한 유저의 기록'">
                  <v-row>
                    <v-col
                      v-for="(card, $idx) in Followslides"
                      :key="$idx"
                      class="d-flex child-flex"
                      cols="4"
                      >
                      <v-img
                        :src="card.img"
                        :lazy-src="card.img"
                        aspect-ratio="1"
                        class="grey lighten-2 cursor_test"
                      >
                      <template v-slot:placeholder>
                        <v-row
                          class="fill-height ma-0"
                          align="center"
                          justify="center"
                        >
                          <v-progress-circular
                            indeterminate
                            color="grey lighten-5"
                          ></v-progress-circular>
                        </v-row>
                      </template>
                      </v-img>
                    </v-col>
                    <infinite-loading @infinite="FollowinfiniteHandler" spinner="waveDots">
        <div slot="no-more">
          보살핌 후기가 없습니다 씨앗에 물을 줘보세요
        </div>
      </infinite-loading>
                  </v-row>


                </v-card-text> -->
              <!-- </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-col>
        
      </v-row> -->
      <infinite-loading @infinite="AllinfiniteHandler" spinner="waveDots">
        <div class="infinite-margin" slot="no-more">
          "보살핌 후기가 더 이상 없습니다"
        </div>
        <div class="infinite-margin" slot="no-results">
          "보살핌 후기가 없습니다 씨앗에 물을 줘보세요"
        </div>
      </infinite-loading>
      
    </v-container>
</template>

<script>
import axios from "axios"
import {mapState} from "vuex";
// import FeedAll from "@/views/Feed/components/FeedAll.vue"
// import FeedFollower from "@/views/Feed/components/FeedFollower.vue"

export default {
  name: 'FeedAllUser',
  components: { 
    // FeedAll, 
    // FeedFollower
   },
  directives: { 
    
   },
  data: function () {
    return {
      Allslides: [],
      Alltotal: [],
    };
  },
  created () {
    // 모든 유저 피드
    axios.get("http://127.0.0.1:8080/allCertification")
      .then((res) => {
        const certifications = res.data
        certifications.sort(function(a,b) {
          return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
        })
        this.Alltotal = certifications
        // console.log("res",res.data)
        console.log("Alltotal",this.Alltotal)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  methods: {
    AllinfiniteHandler($state) {
        setTimeout(() => {
        if (this.Alltotal.length) {
          // console.log("옮겨지기 전 cards: ", this.cards)
          // console.log("옮겨지기 전 total: ", this.total)
          this.Allslides.push(...this.Alltotal.splice(0, 3))
          // console.log("옮겨진 후 cards: ", this.cards)
          // console.log("옮겨진 후 total: ", this.total)
          $state.loaded();
        } else {
          $state.complete();
        }
        }, 500)
      },

    //   getChallengeOwner: function () {
    //   const cngId = {}
    //   const cngId_num = this.slide.cng_id
    //   cngId["cgId"] = cngId_num 
    //   axios.get(`http://127.0.0.1:8080/detailChallenge/${cngId_num}`, cngId)
    //     .then((res) => {
    //       console.log('오너 잘 받아와지나?')
    //       console.log(res.data)
    //       this.cngOwner = res.data.user_id
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //       console.log('대실패')
    //     })
    // },
    //   goCertDetail: function () {
    //     this.getChallengeOwner()
    //     this.$router.push({ name: 'CertificationDetail', params: { cngUserId: this.cngOwner, cngId: this.slide.cng_id, certId: this.slide.id } })
    // },
  },
  computed: {
    ...mapState('UserStore', ['user']),
  }
};
</script>

<style lang="scss" scoped>
.container {
  width: 80%;
  margin-top: 7%;
}

.feed-title {
  font-size: 2rem;
  margin-bottom: 5vh;
  display: flex;
  justify-content: center;
}

.infinite-margin {
   margin-top: 5vh;
}
</style>