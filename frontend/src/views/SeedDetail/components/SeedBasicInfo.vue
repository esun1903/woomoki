<template>
  <v-container>
    <v-row>
      <v-col class="d-flex justify-start align-center">
        <v-avatar
          size="64"
          color="grey lighten-3"
          class="cursor-img"
          >
            <v-img 
            :src="UserInfo.img"
          ></v-img>
        </v-avatar>
        <div>
          <h1 class="d-inline-flex">{{ UserInfo.nickname }}</h1>
          <div class="d-inline-flex">👩‍💼 Lv.{{ UserInfo.levelnum }} {{ UserInfo.title }}</div>
        </div>
      </v-col>
      <v-col class="d-flex align-center">
        <v-row class="d-flex justify-end">
          <v-btn>참여하기</v-btn>
          <router-link v-if="isMySeed === true" :to="{ name: 'SeedUpdate', params: { seedId: this.seedId }}">
            <v-btn>수정하기</v-btn>
          </router-link>
          <SeedShare></SeedShare>
        </v-row>
      </v-col>
    </v-row>
    <v-row justify="center" class="mb-5">
      <v-expansion-panels multiple focusable>
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
            {{ result.value }}
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-row>
    
    <v-row>
      <h2>씨앗 관리 규칙 안내</h2>
    </v-row>
    <v-row>
      <div>- 중간에 나가게되면 참여자들의 사기가 떨어질 수 있습니다</div>
    </v-row>
    <v-row>
      <div>- 욕설 및 타인을 비방하거나 음란물 등 불법촬영물 등을 공유시에 씨앗관리 퇴출사유에 해당되며, 법적조치를 받을 수 있습니다</div>
    </v-row>
  </v-container>
</template>

<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v9.0" nonce="NCYaLITf"></script>
<script>
import axios from 'axios'
import SeedShare from "./SeedShare"

export default {
  name: "SeedBasicInfo",
  components: {
    SeedShare
  },
  data: function () {
    return {
      seedId: this.$route.params.seedId,
      SeedInfo: [],
      UserInfo: [],
      results: [],
      isMySeed: false,
    }
  },
  methods: {
    async SeedDetailInfo () {
      const SeedInfo = await axios.get(`http://127.0.0.1:8080/detailChallenge/${this.seedId}`)
      this.SeedInfo = SeedInfo.data
      this.results.push({key: "내용", value: this.SeedInfo.content})
      this.results.push({key: "참여 인원", value: `${this.SeedInfo.max_people}명`})
      this.results.push({key: "참여 기간", value: `${this.SeedInfo.start_date} ~ ${this.SeedInfo.end_date}`})
      this.results.push({key: "참여 금액", value: `${this.SeedInfo.join_deposit}원`})
      this.results.push({key: "예시 이미지", value: this.SeedInfo.example_img})
      console.log("seed 데이터 응답")
      console.log(this.SeedInfo)

      const user_id = this.SeedInfo.user_id
      const UserInfo = await axios.get(`http://127.0.0.1:8080/userPage/${user_id}`)
      this.UserInfo = UserInfo.data
      console.log(UserInfo)
    },
    isMySeedCheck: function () {
      const SeedUserId = this.$store.state.UserStore.user.user_id 
      const UserId = this.SeedInfo.user_id
      if (SeedUserId === UserId) {
        this.isMySeed = ture;
      }
    }
  },
  created() {
    this.SeedDetailInfo();
    this.isMySeedCheck();
  },
  computed: {
    color: function () {
      if (this.SeedInfo.category_id === 1) {
        return 'light-green lighten-1'
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
  }
}
</script>

<style lang="scss" scoped>


</style>