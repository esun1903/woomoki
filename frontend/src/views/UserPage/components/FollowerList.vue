<template>
  <v-col class="d-flex justify-center">
    <v-dialog
      v-model="dialog.dialog"
      scrollable
      max-width="300px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          :ripple="false"
          color="black"
          plain
          v-bind="attrs"
          v-on="on"
          @click="isFollow"
        >
          <div>
            <h2>팔로워</h2>
            <div>116</div>
          </div>
        </v-btn>
      </template>
      <v-card>
          <v-card-title class="d-flex justify-space-between">팔로워
          <v-btn
            color="success darken-1"
            text
            @click="dialog.dialog = false"
            class="d-flex justify-center"
          >
            <v-icon>fas fa-times</v-icon>
          </v-btn>
          </v-card-title>
        <v-divider></v-divider>
        
        <v-card-text class="dialog-height">
          <div 
            v-for="(follower, idx) in followers"
            :key="idx"
            class="d-flex justify-space-between ma-1"
            >
            <span class="d-flex align-center">
              <router-link :to="{ name: 'UserPage', params: { userNickname: follower.nickname }}">
                {{ follower.nickname }}
              </router-link>
            </span>
            <v-btn
              :color="isFollow(follower.nickname) ? 'grey' : 'success' "
              small
              class="white--text"
              >
              <span v-if="isFollow(follower.nickname)">팔로잉</span>
              <span v-else>팔로우</span>
            </v-btn>
          </div>
        </v-card-text>
        <v-card-actions>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-col>
</template>

<script>
import axios from "axios"

export default {
  name: "FollowerList",
  data: function () {
    return {
      userId: this.$store.state.UserStore.user.user_id,
      followers: [],
      followings: [],
      dialog: {
        dialogm1: "",
        dialog: false,
      },
    }
  },
  methods: {
    // 나를 팔로우하는 팔로우 유저 리스트 가져오기
    async FollowerList () {
      const userId = this.userId
      await axios.get(`http://127.0.0.1:8080/followerList/${userId}`)
        .then((res) => {
          this.followers = res.data
          // console.log("팔로우 리스트", res.data)
        })
      await axios.get(`http://127.0.0.1:8080/followingList/${userId}`)
        .then((res) => {
          this.followings = res.data
          // console.log("팔로잉 리스트",res.data)
        })
    },
    UserFollow: function () {
      // this.isFollow = !this.isFollow
      // 닉네임을 통해 유저 정보받아오는 api필요
      // const followInfo = {
      //   userid : this.$store.state.UserStore.user.user_id,
      //   followingid : ??
      // }
      // axios.post(`http://127.0.0.1:8080/followingListInsert/${userId}/${followingid}`)
      //   .then((res) => {
      //     console.log(res)
      //   })
      // this.$router.push({ name: "UserPage", params: { userNickname: follower }})
    },
    UserUnfollow: function () {
      const userId = this.userId
      // 닉네임을 통해 유저 정보받아오는 api필요
      // const deleteid = ??
      // axios.delete(`http://127.0.0.1:8080/followerList/delete/${userId}/${deleteid}`)
      //   .then((res) => {
      //     console.log(res)
      //   })
      //   .catch((err) => {
      //     console.log(err)
      //   })
    },
    // 나를 팔로우 하는 전체 팔로우 리스트에서 해당 유저를 
    // 내가 팔로우하는지 팔로잉 리스트와 비교하여 확인
    isFollow: function (nickname) {
      const tmp = []
      var i;
      for (i=0; i < this.followings.length; i++) {
        tmp.push(this.followings[i].nickname)
      }
      return tmp.includes(nickname)
    }
  },
  created() {
    this.FollowerList();
  },
}
</script>

<style lang="scss" scoped>

a {text-decoration: none;}


.dialog-height {
  height: 300px;
}

</style>