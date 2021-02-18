<template>
  <v-container>

    <v-row class="d-flex justify-space-around align-center">
      
      <v-col class="d-flex justify-center">
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
            <h1>
              {{ this.UserInfo.nickname }}
            </h1>
          </v-col>

          <v-col v-if="isMyPage === false">
            <v-btn
              :ripple="false"
              :color="isFollow ? 'grey' : '#AED864' "
              width="60"
              height="30"
              @click="UserFollow"
              >
              <span v-if="isFollow" class="white--text">팔로잉</span>
              <span v-else class="white--text">팔로우</span>

            </v-btn>
          </v-col>

          <v-col class="d-flex justify-center ml-4" v-if="isMyPage === true">
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
    
    <v-row class="mt-1">
      <FavoriteCategory></FavoriteCategory>
    </v-row>
  </v-container>
</template>

<script>
import FavoriteCategory from "./FavoriteCategory"
import UserWallet from "./UserWallet"
import FollowerList from "./FollowerList"
import FollowingList from "./FollowingList"
import axios from "axios";
import { mapState } from "vuex"

export default {
  name: "BasicUserInfo",
  components: {
    FavoriteCategory,
    UserWallet,
    FollowerList,
    FollowingList
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
    async BasicUserInfo () {
      const MyNickname = this.$store.state.UserStore.user.nickname
      const user_id = this.user.user_id
      const UserNickname = this.$route.params.userNickname

      console.log(MyNickname, UserNickname)
      await axios.get(`http://i4a303.p.ssafy.io/api/userPage/${UserNickname}`)
        .then((res) => {
          this.UserInfo = res.data
          if (MyNickname === UserNickname) {
            this.isMyPage = true;
          } else {
            this.isMyPage = false;
          }
        })
      await axios.get(`http://i4a303.p.ssafy.io/api/followingList/${user_id}`)
        .then((res) => {
          this.followings = res.data
        })
      
      const tmp = []
      for (var i=0; i < this.followings.length; i++) {
        tmp.push(this.followings[i].nickname)
      }
      if (tmp.includes(UserNickname)) {
        this.isFollow = true
      } else {
        this.isFollow = false
      }
    },
    async UserFollow () {

      const followInfo = {
        userId : this.$store.state.UserStore.user.user_id,
        followingid : this.UserInfo.id
      }
      if (!this.isFollow) {
        await axios.post(`http://i4a303.p.ssafy.io/api/followingList/Insert/${followInfo.userId}/${followInfo.followingid}`, followInfo)
        .then(() => {
          this.isFollow = true
          console.log(this.isFollow)
          this.requestFollow()
        })
      }
      else {
        await axios.delete(`http://i4a303.p.ssafy.io/api/followingList/delete/${followInfo.userId}/${followInfo.followingid}`, followInfo)
        .then(() => {
          this.isFollow = false
          console.log(this.isFollow)
        })
      }
    },
    requestFollow: function () {
      const followInfo = {
        userid : this.$store.state.UserStore.user.user_id,
        followId : this.UserInfo.id,
        type : reqFollow
      }
      axios.post(`http://i4a303.p.ssafy.io/api/notificationFollow/${followInfo.userId}/${followInfo.followingid}/reqFollow`, followInfo)
        .then((res) => {
          console.log(res)
        })
    },
  },
  created() {
    this.BasicUserInfo()
  },
  computed: {
    ...mapState('UserStore', ['user'])
  },
  watch: {
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
