<template>
    <v-container class="container-size">
      <v-row column>
        <v-col>
          <BasicUserInfo></BasicUserInfo>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-banner></v-banner>
          <v-tabs
            v-model="tab"
            background-color="transparent"
            color="success"
            grow
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
                color="basil"
                flat
                >
              
                <v-card-text v-if="item === '피드'">
                  <div>
                    <v-row>
                      <v-col
                        v-for="(card, $idx) in cards"
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
                      
                    </v-row>
                  </div>
                </v-card-text>
                  <!-- <v-card-text v-if="item === '챌린지'"><ChallengeResults></ChallengeResults></v-card-text> -->
                <v-card-text v-if="item === '통계'">
                  <UserStat></UserStat>
                </v-card-text>
              </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-col>
      </v-row>
      <infinite-loading @infinite="infiniteHandler"></infinite-loading>
    </v-container>
</template>

<script>
import BasicUserInfo from "./components/BasicUserInfo"
// import ChallengeResults from "./components/ChallengeResults"
// import UserCertifications from "./components/UserCertifications"
import UserStat from "./components/UserStat"
import axios from 'axios'

export default {
    name: "UserPage",
    components: {
        BasicUserInfo,
        // ChallengeResults,
        // UserCertifications,
        UserStat,
    },
    data: function () {
        return {
          tab: null,
          items: ['피드', '통계'],
          page : 1,
          total: [],
          tmp: [],
          cards: [],
        };
    },
    methods: {
      UserCertification() {
      const userid = this.$store.state.UserStore.user.user_id
      axios.get(`http://127.0.0.1:8080/userCertification/${userid}`)
        .then((res) => {
          this.total = res.data
          // 얘도 멀쩡
          console.log("생성되었을 때 tmp: ", this.total)
        })
    },
      infiniteHandler($state) {
        // 얘랑
        console.log("스크롤때 때 tmp: ", this.total)
        // 얘는 멀쩡함
        console.log("잘려진 tmp tmp: ", this.total.splice(0, 3))
        setTimeout(() => {
          if (this.total.length) {
            this.cards.push(...this.total.splice(0, 3))
            console.log("추가된 때 cards: ", this.cards)
            $state.loaded();
          } else {
            $state.complete();
          }
        }, 1000)
      }
    },
    created() {
      this.UserCertification();
  }
};
</script>

<style scoped>

.container-size {
    width: 60%;
}

</style>
