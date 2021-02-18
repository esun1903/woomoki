<template>
  <div>
    <div class="d-flex justify-space-between ma-1">
      <span class="d-flex align-center">
        <router-link class="username" :to="{ name: 'UserPage', params: { userNickname: following.nickname }}">
          {{ following.nickname }}
        </router-link>
      </span>
    
      <v-btn
        @click="UserUnfollow"
        v-if="isMyPage === true"
        :color="isFollow ? 'grey' : '#AED864' "
        small
        class="white--text"
        >
        <span v-if="isFollow">팔로잉</span>
        <span v-else>팔로우</span>
      </v-btn>
    </div>
  </div>
</template>

<script>
import axios from "axios"

export default {
  name: "Followers",
  data: function () {
    return {
      isFollow: false,
      followingid: "",
    }
  },
  props: {
    followers: [Array, Object],
    followings: Array,
    following: [Array, Object],
    isMyPage: Boolean
  },
  methods: {
    async UserUnfollow () {
      const UserNickname = this.following.nickname
      await axios.get(`http://i4a303.p.ssafy.io/api/userPage/${UserNickname}`)
        .then((res) => {
          this.followingid = res.data.id
        })
      const followInfo = {
        userId : this.$store.state.UserStore.user.user_id,
        followingid : this.followingid
      }
      if (!this.isFollow) {
        await axios.post(`http://i4a303.p.ssafy.io/api/followingList/Insert/${followInfo.userId}/${followInfo.followingid}`, followInfo)
        .then(() => {
          this.isFollow = true
          this.requestFollow()
        })
      }
      else {
        await axios.delete(`http://i4a303.p.ssafy.io/api/followingList/delete/${followInfo.userId}/${followInfo.followingid}`, followInfo)
        .then(() => {
          this.isFollow = false
        })
      }
    },
    requestFollow: function () {
      const userid = this.$store.state.UserStore.user.user_id
      const followId = this.UserInfo.id
      const type = "reqFollow"
      axios.post(`http://i4a303.p.ssafy.io/api/notificationFollow/${userid}/${followId}/${type}`)
        .then((res) => {
          console.log(res)
        })
    },
    initialState: function () {
      
      const tmp = []
      var i;
      for (i=0; i < this.followings.length; i++) {
        tmp.push(this.followings[i].nickname)
      }
      if (tmp.includes(this.following.nickname)) {
        this.isFollow = true
      } else {
        this.isFollow = false
      } 
    }
  },
  created() {
    this.initialState();
  }
}
</script>

<style lang="scss" scoped>
a {text-decoration: none;}

.username {
  color: black
}
</style>