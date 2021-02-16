<template>
    <v-container class="container-size">
      <v-row column class="user-margin">
        <v-col>
          <BasicUserInfo></BasicUserInfo>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <!-- <v-banner></v-banner> -->
          <v-tabs
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
              @click="CheckisUserstat(item)"
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
                >

                <v-card-text v-if="item === '피드'">
     
                  <v-row>
                    <v-col
                      v-for="(card, $idx) in cards"
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
                
                </v-card-text>
                  <!-- <v-card-text v-if="item === '챌린지'"><ChallengeResults></ChallengeResults></v-card-text> -->
                <!-- <v-card-text v-if="item === '통계'">
                  <UserStat></UserStat>
                </v-card-text> -->
              </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-col>
      </v-row>
      <infinite-loading v-if="isUserstat === false" @infinite="infiniteHandler" spinner="waveDots">
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
import BasicUserInfo from "./components/BasicUserInfo"
// import ChallengeResults from "./components/ChallengeResults"
import UserStat from "./components/UserStat"
// import {mapState} from "vuex"
import axios from 'axios'

export default {
    name: "UserPage",
    components: {
        BasicUserInfo,
        // ChallengeResults,
        UserStat,
    },
    data: function () {
        return {
          userId: "",
          tab: null,
          items: ['피드', '통계'],
          total: [],
          cards: [],
          isUserstat: false,
        };
    },
    methods: {
      async UserCertification() {
      const UserNickname = this.$route.params.userNickname
      await axios.get(`http://127.0.0.1:8080/userPage/${UserNickname}`)
        .then((res) => {
          this.userId = res.data.id
          // console.log("??", res.data)
        })
      const userid = this.userId
      await axios.get(`http://127.0.0.1:8080/userCertification/${userid}`)
        .then((res) => {
        this.total = res.data
        console.log("생성되었을 때 tmp: ", this.total)
        // console.log("isUserstat: ", this.isUserstat)
        })
      },
      infiniteHandler($state) {
        setTimeout(() => {
        if (this.total.length) {
          // console.log("옮겨지기 전 cards: ", this.cards)
          // console.log("옮겨지기 전 total: ", this.total)
          this.cards.push(...this.total.splice(0, 3))
          // console.log("옮겨진 후 cards: ", this.cards)
          // console.log("옮겨진 후 total: ", this.total)
          $state.loaded();
        } else {
          $state.complete();
        }
        }, 500)
      },
      CheckisUserstat: function (item) {
        if (item === "피드") {
          this.isUserstat = false
        } else {
          this.isUserstat = true
        }
      }
    },
    created() {
      this.UserCertification();
  },
  computed: {
    // ...mapState('UserStore', ['user']),
  }
};
</script>

<style scoped>

.container-size {
    width: 60%;
}

.tab {
  font-size: 1.5vw;
}

.infinite-margin {
  margin-top: 5vh;
}
</style>
