<template>
  <v-container>
    <v-row>
      <v-col class="d-flex justify-center">
        <v-avatar
          size="64"
          color="grey lighten-3"
          class="cursor-img"
          >
            <v-img 
            :src="UserInfo.img"
          ></v-img>
        </v-avatar>
      </v-col>
      <v-col class="d-flex align-center">
        <div>심은이: {{ UserInfo.nickname }}</div>
        <div>레벨: {{ UserInfo.levelnum }} {{ UserInfo.title }}</div>
      </v-col>
      <v-col class="d-flex align-center">
        <v-row>
          <v-btn>참여하기</v-btn>
          <SeedShare></SeedShare>
        </v-row>
      </v-col>
    </v-row>
    <v-row>
      <div>내용: {{ SeedInfo.content }}</div>
    </v-row>
    <v-row>
      <div>최대 인원: {{ SeedInfo.max_people }}</div>
    </v-row>
    <v-row>
      <div>시작 날짜: {{ SeedInfo.start_date }}</div>
    </v-row>
    <v-row>
      <div>종료 날짜: {{ SeedInfo.end_date }}</div>
    </v-row>
    <v-row>
      <div>참여 금액: {{ SeedInfo.join_deposit }}</div>
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
      SeedInfo: [],
      UserInfo: [],
    }
  },
  methods: {
    async SeedDetailInfo () {
    
      const seedId = 5
      const SeedInfo = await axios.get(`http://127.0.0.1:8080/detailChallenge/${seedId}`)
      this.SeedInfo = SeedInfo.data
      console.log("seed 데이터 응답")
      console.log(this.SeedInfo)

      const user_id = this.SeedInfo.user_id
      const UserInfo = await axios.get(`http://127.0.0.1:8080/userPage/${user_id}`)
      this.UserInfo = UserInfo.data
      console.log(UserInfo)
    }
  },
  created() {
    this.SeedDetailInfo();
  }
}
</script>

<style lang="scss" scoped>

</style>