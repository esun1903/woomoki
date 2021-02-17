<template>
  <v-container class="container-size">
    <v-row>
      <SeedThumbnail :joinUser="joinUser"></SeedThumbnail>
    </v-row>

    <v-row>
      <v-col>

        <v-tabs v-model="tab" slider-size="4" color="#AED864" right>
          <v-tab v-for="item in items" :key="item" @click="CheckisBasicInfo(item)">
            {{ item }}
          </v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab">
          <v-tab-item v-for="item in items" :key="item">
            <v-card width="100vw">
              <v-card-text class="d-flex justify-center" v-if="item === '씨앗 정보'">
                <SeedBasicInfo :SeedInfo="SeedInfo" :waitUser="waitUser" :isJoin="isJoin"></SeedBasicInfo>
              </v-card-text>
              <v-card-text class="d-flex justify-center" v-if="item === '보살핌 후기'">
                <!-- <SeedCertification></SeedCertification> -->
                <!-- <SeedCertification v-for="(certification, index) in total" :key="index" :certification="certification" /> -->
                <!-- <v-row class="stamp-card-section">
                  <v-btn @click="goStampCard">나의 스탬프카드</v-btn>
                </v-row> -->
                <v-row>
                  <v-col v-for="(card, $idx) in cards" :key="$idx" class="d-flex child-flex" cols="4">
                    <v-img :src="card.img" :lazy-src="card.img" aspect-ratio="1" class="grey lighten-2 cursor_test"
                      v-on:click="detailCertification(card.id)">
                      <template v-slot:placeholder>
                        <v-row class="fill-height ma-0" align="center" justify="center">
                          <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                        </v-row>
                      </template>
                    </v-img>
                  </v-col>

                </v-row>

              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-col>
    </v-row>

    <infinite-loading v-if="isBasicInfo === false" @infinite="infiniteHandler" spinner="waveDots">
      <div slot="no-more">
        보살핌 후기의 끝입니다
      </div>
      <div slot="no-results">
        아직 보살핌 후기가 없어요! 가장 처음으로 후기를 작성해보세요
      </div>
    </infinite-loading>

    <div v-if="isMySeed === false">
      <div id="rules"></div>
      <div id="content"></div>
      <footer></footer>
      <v-btn v-if="!isJoin && !checkAcception" @click="JoinSeed" depressed tile id="banner" width="65.55vw" height="5vw" class="position-fixed"
        color="#AED864">
        <h1 class="join-font">
          함께하기
        </h1>
      </v-btn>
      <v-btn v-if="isJoin && checkAcception" disabled depressed tile id="banner" width="65.55vw" height="5vw" class="position-fixed"
        color="#AED864">
        <h1 class="join-font">
          참여 수락을 기다리고 있습니다
        </h1>
      </v-btn>
    </div>

  </v-container>
</template>

<script>
import $ from 'jquery'
import axios from "axios"
import {mapState} from "vuex"
import SeedThumbnail from "./components/SeedThumbnail"
import SeedBasicInfo from "./components/SeedBasicInfo"
import SeedCertification from "./components/SeedCertification"

export default {
  name: "SeedDetail",
  components: {
    SeedThumbnail,
    SeedBasicInfo,
    SeedCertification
  },
  data: function () {
    return {
      tab: null,
      items: ["씨앗 정보", "보살핌 후기"],
      SeedInfo: {},
      seedId: this.$route.params.seedId,
      total: [],
      cards: [],
      isBasicInfo: true,
      isMySeed: false,
      isLogin: this.$store.state.UserStore.isLogin,
      isJoin: false,
      isAccepted: false,
      joinUser: [],
      waitUser: [],
    }
  },
  computed: {
    ...mapState('UserStore', ['user']),
  },
  methods: {
    async getSeedCertification() {
      const seedId = this.seedId
      console.log(this.seedId)
      await axios.get(`http://127.0.0.1:8080/sameChallengeCertification/${seedId}`)
        .then((res) => {
          this.total = res.data
          // console.log("인증:",res.data)
        })

      // 내가 만든 씨앗인지 구분
      const SeedInfo = await axios.get(`http://127.0.0.1:8080/detailChallenge/${this.seedId}`)
      this.SeedInfo = SeedInfo.data
      console.log("보내기 테스트", this.SeedInfo)
      const SeedUserId = this.$store.state.UserStore.user.user_id
      const UserId = this.SeedInfo.user_id
      if (SeedUserId === UserId) {
        this.isMySeed = true;
      }
    },
    infiniteHandler($state) {
      setTimeout(() => {
        if (this.total.length) {
          // console.log("옮겨지기 전 cards: ", this.cards)
          // console.log("옮겨지기 전 total: ", this.total)
          this.cards.push(...this.total.splice(0, 3))
          console.log("옮겨진 후 cards: ", this.cards)
          // console.log("옮겨진 후 total: ", this.total)
          $state.loaded();
        } else {
          $state.complete();
        }
      }, 1000)
    },
    CheckisBasicInfo: function (item) {
      if (item === "씨앗 정보") {
        this.isBasicInfo = true
      } else {
        this.isBasicInfo = false
      }
      console.log(this.isBasicInfo)
    },
    JoinSeed: function () {
      const notification = {
        userId: this.$store.state.UserStore.user.user_id,
        cngUserId: this.SeedInfo.user_id,
        cngId: this.seedId
      }
      axios.post(
          `http://127.0.0.1:8080/notificationRequestChallenge/${notification.userId}/${notification.cngUserId}/${notification.cngId}/reqChallenge`,
          notification)
        .then((res) => {
          console.log(res)
          this.isJoin = true
        })
        .catch((err) => {
          console.log("join err", err)
        })
      
      const joinData = {
        cng_id: this.seedId,
        user_id: this.$store.state.UserStore.user.user_id
      }
      console.log(joinData)
      axios.post(`http://127.0.0.1:8080/joinChallengeInsert`, joinData)
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log("insert err", err)
        })
    },
    async allJoinUser () {
    const seedId = this.seedId
    await axios.get(`http://127.0.0.1:8080/joinChallengeUserList/${seedId}`)
      .then((res) => {
        const allUser = res.data

        console.log("전체 유저 리스트",res.data)

        for (var i=0; i < allUser.length; i++) {
          console.log("for문", allUser[i].result)
          if (allUser[i].result == 1) {
            console.log("if문", allUser[i])
            this.joinUser.push(allUser[i])
          } else if (allUser[i].result == 0) {
            console.log("if문", allUser[i])
            this.waitUser.push(allUser[i])
          }
        }
      console.log("참여중인, 참여대기중인",this.joinUser, this.waitUser) 
      })     
    },
    detailCertification: function (certid) {
      this.$router.push({
        name: 'CertificationDetail',
        params: {
          cngId: this.seedId,
          certId: certid,
          cngUserId: this.SeedInfo.user_id,
        }
      });
    },
  },
  created() {
    this.getSeedCertification();
    this.allJoinUser();
  },
  computed: {
    checkAcception: function () {
      const seedId = this.seedId
      const userId = this.$store.state.UserStore.user.user_id
      axios.get(`http://127.0.0.1:8080/joinChallengeUserList/${seedId}`)
        .then((res) => {
          const userList = res.data
          for (var i=0; userList.length; i++) {
            if (userList[i].id === userId) {
              this.isAccepted = true
              console.log("accpeted",this.isAccepted)
            }
          }
        })
      return this.isAccepted
    }
  },
  mounted() {
    $(function () {
      var $w = $(window),
        footerHei = $('footer').outerHeight(),
        $banner = $('#banner');

        $w.on('scroll', function () {

        var sT = $w.scrollTop();
        var val = $(document).height() - $w.height() - footerHei;

        if (sT + 190 >= val) {
          $banner.addClass('on')
        } else
          $banner.removeClass('on')
      });
    });
  }
}
</script>

<style lang="scss" scoped>
.position-fixed {
  z-index: 2;
  position: fixed;
  bottom: 0;
  // right: 37.5%;
  color: #fff;
  background-position: center center;
  background-repeat: no-repeat;
  box-shadow: 12px 15px 20px 0 rgba(46, 61, 73, 0.15);
  cursor: pointer;
  margin: 2vw;
}

  // * {
  //   margin:0;
  //   padding:0;
  // }

  // main {
  //   position:relative;
  // }

footer {
  height: 0px;
}

#rules {
  background: white;
  height: 150px;
  // font-size: 30px;
  color: black;
}


#banner {
  z-index: 3;
  position: fixed;
  // right: 37.7%;
  right: 14.85%;
  bottom: -3.9%;
  width: 50px;
  height: 100px;
  background: salmon;
  box-shadow: 0 0 10px rgba(0, 0, 0, .6);
}

#banner.on {
  position: absolute;
  // right: 37.7%;
  right: 14.85%;
  bottom: 1%;
}

.content-color {
  color: black;
}

.container-size {
  width: 70%;
}

.join-font {
  font-size: 35px;
}

// .stamp-card-section{
//   margin-bottom: 1vh;
//   .v-btn{
//     display: flex;
//     justify-content: flex-end;
//   }
// }
</style>