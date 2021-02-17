<template>
  <v-container>
    <v-row class="mb-5">
      <v-col cols="4" class="d-flex justify-start align-center">
        <router-link :to="{ name: 'UserPage', params: { userNickname: UserInfo.nickname}}">
          <v-avatar size="64" color="grey lighten-3" class="cursor-img">
            <v-img :src="UserInfo.img"></v-img>
          </v-avatar>
        </router-link>
        <div class="ml-8">
          <v-row>
            <router-link :to="{ name: 'UserPage', params: { userNickname: UserInfo.nickname}}">
              <h1 class="d-inline-flex ninckname">{{ UserInfo.nickname }}</h1>
            </router-link>
          </v-row>
          <v-row v-if="UserInfo.levelnum <= 10">
            <div class="d-inline-flex">🥉 Lv.{{ UserInfo.levelnum }}</div>
          </v-row>
          <v-row v-if="UserInfo.levelnum > 10 && UserInfo.levelnum <= 20">
            <div class="d-inline-flex">🥈 Lv.{{ UserInfo.levelnum }}</div>
          </v-row>
          <v-row v-if="UserInfo.levelnum > 30">
            <div class="d-inline-flex">🥇 Lv.{{ UserInfo.levelnum }}</div>
          </v-row>
        </div>
      </v-col>
      <!-- <v-col>
      </v-col> -->
      <v-col cols="8" class="d-flex align-center">
        <v-row class="d-flex justify-end align-center">
          <!-- isJoin에 따라 보이거나 안보이거나 -->
          <!-- <router-link v-if="checkAcception" :to="{ name: 'CertificationInsert', params: { cngId: this.$route.params.seedId }}"> -->
          <v-btn :ripple="false" color="#AED864" class="btn-position white--text mr-2" @click="certInsertorLogin">
            인증 작성
          </v-btn>

          <!-- <router-link> -->
          <v-btn v-if="checkAcception" @click="goStampCard" :ripple="false" color="#AED864"
            class="btn-position white--text mr-2">
            나의 인증 현황
          </v-btn>
          <!-- </router-link> -->
          <WaitList v-if="isMySeed === true" :waitUser="waitUser"></WaitList>

          <router-link v-if="isMySeed === true" :to="{ name: 'SeedUpdate', params: { seedId: this.seedId }}">
            <!-- <v-btn class="mr-5 btn-color" color="#AED864">
              수정
            </v-btn> -->
            <v-btn icon class="mr-5 btn-color" color="#AED864">
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
          </router-link>
          <v-btn icon v-if="isMySeed === true" class="mr-5 btn-color" color="#EF5350"
            @click.stop="confirmCngDeleteDialog = true">
            <v-icon>mdi-trash-can</v-icon>
          </v-btn>
          <v-dialog v-model="confirmCngDeleteDialog" max-width="350">
            <v-card>
              <v-card-title class="headline">
                씨앗을 삭제하시겠습니까?
              </v-card-title>

              <v-card-text>
                한번 삭제된 씨앗은 다시 살릴 수 없어요!
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn color="grey" text @click="confirmCngDeleteDialog = false">
                  취소
                </v-btn>

                <v-btn color="red" text @click="confirmCngDeleteDialog = false; deleteSeed()">
                  삭제
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <!-- <v-btn v-if="isMySeed === true" class="mr-5 btn-color" color="#AED864" @click="deleteSeed">삭제</v-btn> -->
          <SeedShare></SeedShare>
          <!-- <SeedViewMore></SeedViewMore> -->
        </v-row>
      </v-col>
    </v-row>

    <v-row justify="center" class="mb-5">
      <v-expansion-panels multiple popout hover disabled readonly v-model="panel">
        <v-expansion-panel v-for="(result, idx) in results" :key="idx">
          <v-expansion-panel-header :color="color" :ripple="false">
            <h3 class="black--text">
              {{ result.key }}
            </h3>
          </v-expansion-panel-header>
          <v-expansion-panel-content v-if="result.key === '예시 이미지'" class="mt-4">
            <v-img :src="result.value"></v-img>
          </v-expansion-panel-content>
          <v-expansion-panel-content v-else class="mt-4">
            <span class="black--text content-size">
              {{ result.value }}
            </span>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-row>
    <v-divider></v-divider>
    <div>
      <v-row class="ma-10">
        <h1>씨앗 관리 규칙 안내</h1>
      </v-row>
      <v-row class="ma-5">
        <div>• 중간에 나가게되면 참여자들의 사기가 떨어질 수 있습니다</div>
      </v-row>
      <v-row class="ma-5">
        <div>• 씨앗 소개 내용은 생성자 개인이 작성한 것으로 우목이팀이 보증하는 내용이 아닙니다.</div>
      </v-row>
      <v-row class="mt-5, ml-5">
        <div>• 욕설 및 타인을 비방하거나 음란물 등 불법촬영물 등을 공유시에 씨앗관리 퇴출사유에 해당되며, 법적조치를 받을 수 있습니다</div>
      </v-row>
    </div>

  </v-container>


</template>

<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v9.0"
  nonce="NCYaLITf"></script>
<script>
  // import $ from 'jquery'
  import axios from 'axios'
  import SeedShare from "./SeedShare"
  import SeedViewMore from "./SeedViewMore"
  import WaitList from "./WaitList"
  import {
    mapState
  } from "vuex"

  export default {
    name: "SeedBasicInfo",
    components: {
      SeedShare,
      SeedViewMore,
      WaitList
    },
    data: function () {
      return {
        seedId: this.$route.params.seedId,
        userId: "",
        SeedInfo: [],
        UserInfo: [],
        results: [],
        allUser: [],
        isMySeed: false,
        panel: [0, 1, 2, 3, 4, 5],
        joinUser: [],
        waitUser: [],
        isAccepted: false,
        confirmCngDeleteDialog: false,
      }
    },
    props: {
      isJoin: Boolean
    },
    methods: {
      async SeedDetailInfo() {
        // 씨앗 정보 가져오기
        const SeedInfo = await axios.get(`http://127.0.0.1:8080/detailChallenge/${this.seedId}`)
        this.SeedInfo = SeedInfo.data
        this.results.push({
          key: "예시 이미지",
          value: this.SeedInfo.example_img
        })
        this.results.push({
          key: "내용",
          value: this.SeedInfo.content
        })
        this.results.push({
          key: "참여 인원",
          value: `${this.SeedInfo.max_people}명`
        })
        this.results.push({
          key: "참여 기간",
          value: `${this.SeedInfo.start_date} ~ ${this.SeedInfo.end_date}`
        })
        this.results.push({
          key: "보살핌 횟수",
          value: `${this.SeedInfo.week}동안 주 ${this.SeedInfo.day}회`
        })
        this.results.push({
          key: "참여 금액",
          value: `${this.SeedInfo.join_deposit}원`
        })

        console.log(this.results)
        // 유저 정보 가져오기
        const user_id = this.SeedInfo.user_id
        // 유저 닉네임 -> 아이디 -> 유저정보...?
        const UserInfo = await axios.get(`http://127.0.0.1:8080/userPage/Id/${user_id}`)
        this.UserInfo = UserInfo.data

        // 내가 만든 씨앗인지 구분
        const SeedUserId = this.$store.state.UserStore.user.user_id
        const UserId = this.SeedInfo.user_id
        if (SeedUserId === UserId) {
          this.isMySeed = true;
        }

      },
      // 씨앗 제거
      deleteSeed: function () {
        const seedId = this.seedId
        console.log(seedId)
        axios.delete(`http://127.0.0.1:8080/deleteChallenge/${seedId}`)
          .then((res) => {
            console.log(res)
            this.$router.push({
              name: "Main"
            })
          })
          .catch((err) => {
            console.log(err)
          })
      },
      goStampCard: function () {
        this.$router.push({
          name: 'StampCard',
          params: {
            seedId: this.seedId,
            userId: this.user.user_id
          }
        })
      },
      certInsertorLogin() {
        if (this.$store.state.UserStore.user.accessToken === null) {
          this.$router.push({
            name: "Login"
          })
        } else {
          this.$router.push({
            name: 'CertificationInsert',
            params: {
              cngId: this.$route.params.seedId
            }
          })
        }
      }

    },
    created() {
      this.SeedDetailInfo();

    },
    // jquery
    mounted() {},
    computed: {
      ...mapState('UserStore', ['user']),
      color: function () {
        if (this.SeedInfo.category_id === 1) {
          return '#F3ECE2'
        } else if (this.SeedInfo.category_id === 2) {
          return '#F3ECE2'
        } else if (this.SeedInfo.category_id === 3) {
          return '#F3ECE2'
        } else if (this.SeedInfo.category_id === 4) {
          return '#F3ECE2'
        } else if (this.SeedInfo.category_id === 5) {
          return '#F3ECE2'
        } else {
          return '#F3ECE2'
        }
      },
      checkAcception: function () {
        const seedId = this.seedId
        const userId = this.$store.state.UserStore.user.user_id
        axios.get(`http://127.0.0.1:8080/joinChallengeUserList/${seedId}`)
          .then((res) => {
            const userList = res.data
            for (var i = 0; userList.length; i++) {
              if (userList[i].id === userId) {
                this.isAccepted = true
              }
            }
          })
        return this.isAccepted
      }

    },
  }
</script>

<style lang="scss" scoped>
  a {
    text-decoration: none;
  }

  .ninckname {
    color: black;
  }

  .content-size {
    font-size: 20px;
  }

  .btn-color {
    color: white;
  }
</style>