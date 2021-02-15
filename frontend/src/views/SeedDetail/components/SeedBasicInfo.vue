<template>
  <v-container>
    <v-row class="mb-5">
      <v-col class="d-flex justify-start align-center">
        <router-link :to="{ name: 'UserPage', params: { userNickname: UserInfo.nickname}}">
          <v-avatar
            size="64"
            color="grey lighten-3"
            class="cursor-img"
            >
              <v-img 
              :src="UserInfo.img"
            ></v-img>
          </v-avatar>
        </router-link>
        <div class="ml-7">
          <v-row>
            <router-link :to="{ name: 'UserPage', params: { userNickname: UserInfo.nickname}}">
              <h1 class="d-inline-flex ninckname">{{ UserInfo.nickname }}</h1>
            </router-link>
          </v-row>
          <v-row v-if="UserInfo.levelnum <= 10">
            <div class="d-inline-flex">🥉  Lv.{{ UserInfo.levelnum }}</div>
          </v-row>
          <v-row v-if="UserInfo.levelnum > 10 && UserInfo.levelnum <= 20">
            <div class="d-inline-flex">🥈  Lv.{{ UserInfo.levelnum }}</div>
          </v-row>
          <v-row v-if="UserInfo.levelnum > 30">
            <div class="d-inline-flex">🥇  Lv.{{ UserInfo.levelnum }}</div>
          </v-row>
        </div>
      </v-col>
      <v-col>
      </v-col>
      <v-col class="d-flex align-center">
        <v-row class="d-flex justify-end">
          <router-link v-if="isMySeed === true" :to="{ name: 'SeedUpdate', params: { seedId: this.seedId }}">
            <v-btn color="white--text light-green lighten-1">
              수정
            </v-btn>
          </router-link>
          <v-btn v-if="isMySeed === true" color="white--text light-green lighten-1" @click="deleteSeed">삭제</v-btn>
          <SeedShare></SeedShare>
          <SeedViewMore></SeedViewMore>
        </v-row>
      </v-col>
    </v-row>

    <v-row ustify="center" class="mb-5">
      <v-expansion-panels multiple popout hover disabled readonly v-model="panel">
        <v-expansion-panel
          v-for="(result, idx) in results"
          :key="idx"
        >
          <v-expansion-panel-header :color="color" :ripple="false">
            <h3 class="white--text">
              {{ result.key }}  
            </h3>
          </v-expansion-panel-header>
          <v-expansion-panel-content v-if="result.key === '예시 이미지'" class="mt-4">
            <v-img :src="result.value"></v-img>
          </v-expansion-panel-content>
          <v-expansion-panel-content v-else class="mt-4">
            <span class="black--text content-color">
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
      <v-row class="ma-10">
        <div>- 중간에 나가게되면 참여자들의 사기가 떨어질 수 있습니다</div>
      </v-row>
      <v-row class="mt-10, ml-10">
        <div>- 욕설 및 타인을 비방하거나 음란물 등 불법촬영물 등을 공유시에 씨앗관리 퇴출사유에 해당되며, 법적조치를 받을 수 있습니다</div>
      </v-row>
    </div>

  </v-container>


</template>

<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v9.0" nonce="NCYaLITf"></script>
<script>

// import $ from 'jquery'
import axios from 'axios'
import SeedShare from "./SeedShare"
import SeedViewMore from "./SeedViewMore"

export default {
  name: "SeedBasicInfo",
  components: {
    SeedShare,
    SeedViewMore
  },
  data: function () {
    return {
      seedId: this.$route.params.seedId,
      userId: "",
      SeedInfo: [],
      UserInfo: [],
      results: [],
      isMySeed: false,
      panel: [0, 1, 2, 3, 4]
    }
  },
  methods: {
    async SeedDetailInfo () {
      // 씨앗 정보 가져오기
      const SeedInfo = await axios.get(`http://127.0.0.1:8080/detailChallenge/${this.seedId}`)
      this.SeedInfo = SeedInfo.data
      this.results.push({key: "내용", value: this.SeedInfo.content})
      this.results.push({key: "참여 인원", value: `${this.SeedInfo.max_people}명`})
      this.results.push({key: "참여 기간", value: `${this.SeedInfo.start_date} ~ ${this.SeedInfo.end_date}`})
      this.results.push({key: "참여 금액", value: `${this.SeedInfo.join_deposit}원`})
      this.results.push({key: "예시 이미지", value: this.SeedInfo.example_img})
      
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
          this.$router.push({ name: "Main" })
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
  created() {
    this.SeedDetailInfo();
  },
  // jquery
  mounted() {
  },
  computed: {
    color: function () {
      if (this.SeedInfo.category_id === 1) {
        return 'light-blue lighten-1'
      } else if (this.SeedInfo.category_id === 2) {
        return 'orange lighten-1'
      } else if (this.SeedInfo.category_id === 3) {
        return 'teal lighten-1'
      } else if (this.SeedInfo.category_id === 4) {
        return 'indigo lighten-1'
      } else if (this.SeedInfo.category_id === 5) {
        return 'purple lighten-1'
      } else {
        return 'pink lighten-1'
      }
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
</style>