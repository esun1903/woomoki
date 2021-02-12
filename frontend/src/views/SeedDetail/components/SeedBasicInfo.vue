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
          <div class="d-inline-flex">👩‍💼 Lv.{{ UserInfo.levelnum }}</div>
        </div>
      </v-col>
      <v-col>
      </v-col>
      <v-col class="d-flex align-center">
        <v-row class="d-flex justify-end">
          <router-link v-if="isMySeed === true" :to="{ name: 'SeedUpdate', params: { seedId: this.seedId }}">
            <v-btn color="success">
              수정하기
            </v-btn>
          </router-link>
          <v-btn v-if="isMySeed === true" color="success" @click="deleteSeed">삭제</v-btn>
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
            <span class="content-color">
              {{ result.value }}
            </span>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-row>
    
    <div id="rules">
      <v-row>
        <h2>씨앗 관리 규칙 안내</h2>
      </v-row>
      <v-row>
        <div>- 중간에 나가게되면 참여자들의 사기가 떨어질 수 있습니다</div>
      </v-row>
      <v-row>
        <div>- 욕설 및 타인을 비방하거나 음란물 등 불법촬영물 등을 공유시에 씨앗관리 퇴출사유에 해당되며, 법적조치를 받을 수 있습니다</div>
      </v-row>
    </div>
    
    <div id="content"></div>
    <footer></footer>
    <v-btn id="banner" width="20vw" height="5vw" class="position-fixed" v-if="isMySeed === false" color="light-green lighten-2">
      <h1>
        함께하기
      </h1>
    </v-btn>

  </v-container>


</template>

<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v9.0" nonce="NCYaLITf"></script>
<script>

import $ from 'jquery'
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
      const UserInfo = await axios.get(`http://127.0.0.1:8080/userPage/${user_id}`)
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
    $(function() {
      var $w = $(window),
        footerHei = $('footer').outerHeight(),
        $banner = $('#banner');

        $w.on('scroll', function() {

        var sT = $w.scrollTop();
        var val = $(document).height() - $w.height() - footerHei;
    
        if (sT + 80 >= val) {
          $banner.addClass('on')
        }
        else
          $banner.removeClass('on')   
      });
    });
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
  },
}


</script>

<style lang="scss" scoped>

.position-fixed {
  z-index: 2;
  position: fixed;
  bottom: 0;
  right: 37.5%;
  color: #fff;
  background-position: center center;
  background-repeat: no-repeat;
  box-shadow: 12px 15px 20px 0 rgba(46, 61, 73, 0.15);
  cursor: pointer;
  margin: 2vw;
}

* {
  margin:0;
  padding:0;
}

main {
  position:relative;
}

footer {
  height: 100px;
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
  right: 37.7%;
  width: 50px;
  height: 100px;
  background: salmon;
  box-shadow: 0 0 10px rgba(0, 0, 0, .6);
}

#banner.on {
  position: absolute;
  right: 33.8%;
  bottom: 1vw;
}

.content-color {
  color: black;
}

</style>