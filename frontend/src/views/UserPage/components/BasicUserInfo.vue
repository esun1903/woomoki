<template>
  <v-container>

    <v-row class="d-flex justify-space-around align-center">
      
      <v-col class="d-flex justify-start">
        <v-avatar
          color="grey lighten-3"
          width="250"
          height="250"
          >
          <span class="black--text headline">
            
            <v-img
              :src="this.UserInfo.img">
            </v-img>
          </span>
        </v-avatar>
      </v-col>
 
      <v-col>
        <v-row 
          class="d-flex align-center">
          <v-col>
            <h2>
              {{ this.UserInfo.nickname }}
            </h2>
          </v-col>

          <v-col v-if="isMyPage === false">
            <v-btn
              :ripple="false"
              :color="isFollow ? 'grey' : 'light-green lighten-2' "
              width="60"
              height="30"
              @click="UserFollow"
              >
              <span v-if="isFollow" class="white--text">팔로잉</span>
              <span v-else class="white--text">팔로우</span>

            </v-btn>
          </v-col>

          <v-col class="d-flex justify-center" v-if="isMyPage === true">
            <router-link to="/comparepwd">
              <v-icon>fas fa-user-cog</v-icon>
            </router-link>    
          </v-col>
        </v-row>

        <v-row>
          <v-col v-if="UserInfo.levelnum <= 10">
            <div>🥉  Lv. {{ this.UserInfo.levelnum }} {{ this.UserInfo.leveltitle }}</div>
          </v-col>
          <v-col v-if="UserInfo.levelnum > 10 && UserInfo.levelnum <= 20">
            <div>🥈  Lv. {{ this.UserInfo.levelnum }} {{ this.UserInfo.leveltitle }}</div>
          </v-col>
          <v-col  v-if="UserInfo.levelnum > 30">
            <div>🥇  Lv. {{ this.UserInfo.levelnum }} {{ this.UserInfo.leveltitle }}</div>
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            <div>{{ this.UserInfo.introduce }}</div>
          </v-col>
        </v-row>

        <v-row class="d-flex justify-space-around">
          <UserWallet v-if="isMyPage === true" :UserInfo="UserInfo"></UserWallet>
          <FollowerList :isMyPage="isMyPage"></FollowerList>
          <FollowingList :isMyPage="isMyPage"></FollowingList>
        </v-row>
      

      </v-col>
    </v-row>
    
    <v-row class="mt-0">
      <ChallengeResults></ChallengeResults>
    </v-row>
  </v-container>
</template>

<script>
import ChallengeResults from "./ChallengeResults"
import UserWallet from "./UserWallet"
import FollowerList from "./FollowerList"
import FollowingList from "./FollowingList"
import axios from "axios";
import { mapState } from "vuex"

export default {
  name: "BasicUserInfo",
  components: {
    ChallengeResults,
    UserWallet,
    FollowerList,
    FollowingList
    // mapState,
  },
  data: function () {
    return {
      isMyPage: false,
      UserInfo: [],
      followId: "",
      followings: [],
      isFollow: false,
      isFollowing: false,
    }
  },
  methods: {
    // 마이페이지 아이콘 or 다른사람의 닉네임을 누르면 
    // router로 페이지 이동과 함께 params로 vuex state에 저장되어 있는 userId를 보낸다.
    // 그리고 UserPage.vue가 렌더링 될때 vuex state에 있는 로그인한 사람의 myId와 params로 받은 userId를 비교하여 같으면
    // 마이페이지를 렌더링 하고 다르면 유저페이지를 렌더링한다.
    async BasicUserInfo () {
      const MyNickname = this.$store.state.UserStore.user.nickname
      const user_id = this.user.user_id
      const UserNickname = this.$route.params.userNickname

      console.log(MyNickname, UserNickname)
      await axios.get(`http://127.0.0.1:8080/userPage/${UserNickname}`)
        .then((res) => {
          this.UserInfo = res.data
          if (MyNickname === UserNickname) {
            
            // 내 페이지
            // myState에 따라 태그에 v-if 렌더링
            this.isMyPage = true;
          } else {
            
            // 다른 유저 페이지
            this.isMyPage = false;
          }
          // console.log("나의 페이지인가", this.isMyPage)
        })
      // 로그인 한 유저의 팔로잉 리스트
      // 컴포넌트에서 emit으로 받아서 간소화
      await axios.get(`http://127.0.0.1:8080/followingList/${user_id}`)
        .then((res) => {
          this.followings = res.data
          // console.log("팔로잉 리스트",res.data)
        })
      
      // const UserNickname = this.$route.params.userNickname
      const tmp = []
      for (var i=0; i < this.followings.length; i++) {
        tmp.push(this.followings[i].nickname)
      }
      // console.log("팔로잉 test",this.tmp)
      if (tmp.includes(UserNickname)) {
        this.isFollow = true
        // console.log("팔로우 되어 있어요",this.isFollow)
      } else {
        this.isFollow = false
        // console.log("팔로우 안되어 있네요",this.isFollow)
      }
    },
    // 유저를 팔로우하거나 언팔로우
    async UserFollow () {

      const followInfo = {
        userId : this.$store.state.UserStore.user.user_id,
        followingid : this.UserInfo.id
      }
      
      // 팔로우
      if (!this.isFollow) {
        await axios.post(`http://127.0.0.1:8080/followingList/Insert/${followInfo.userId}/${followInfo.followingid}`, followInfo)
        .then(() => {
          this.isFollow = true
          console.log(this.isFollow)
        })
      }
      // 언팔로우
      else {
        await axios.delete(`http://127.0.0.1:8080/followingList/delete/${followInfo.userId}/${followInfo.followingid}`, followInfo)
        .then(() => {
          this.isFollow = false
          console.log(this.isFollow)
        })
      }
    // console.log(this.isFollow)
    },
    // 로그인 한 유저의 팔로잉 리스트 가져오기
    async FollowerList () {
      // userId 가져오기
      // await axios.get(`http://127.0.0.1:8080/userPage/${UserNickname}`)
      //   .then((res) => {
        //     this.userId = res.data.id
      //   })
      // const userId = this.userId
      // 팔로잉 리스트
      // const userId = this.$store.state.UserStore.user.user_id
      // await axios.get(`http://127.0.0.1:8080/followingList/${userId}`)
      //   .then((res) => {
      //     this.followings = res.data
      //     // console.log("팔로잉 리스트",res.data)
      //   })
    },
    // 페이지에 접속했을 때 로그인한 유저의 팔로잉 리스트에 해당 유저가 있는지 확인
    // CheckisFollow: function (nickname) {
      
    // }
  },
  created() {
    // this.setToken()
    this.BasicUserInfo()
    // this.CheckisFollow();
    // this.FollowerList()
    // this.getUserId()
  },
  computed: {
    ...mapState('UserStore', ['user'])
  },
  watch: {
    // isFollow: function() {
    //   this.CheckisFollow();
    // }
  }
}
</script>

<style lang="scss" scoped>

a {text-decoration: none;}

@mixin between {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.title-close-inline {
  @include between;
}

.name-follow {
  @include between;
}

.container-size {
  width: 60%;
}

.user-id {
  font-size: 30px;
}

.align-Id-follow-edit {
  display: flex;
  align-items: center;
}

.user-level {
  font-size: 15px;
}

.user-introduce {
  font-size: 20px;
}


.follow-info {
  font-size: 30px;
  display: flex;
  justify-content: space-around;
}

.btn {
  opacity: 1;
}

.dialog-height {
  height: 300px;
}

</style>
