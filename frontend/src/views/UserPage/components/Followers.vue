<template>
  <div>
    <div class="d-flex justify-space-between ma-1">
      <span class="d-flex align-center">
        <router-link class="username" :to="{ name: 'UserPage', params: { userNickname: follower.nickname }}">
          {{ follower.nickname }}
        </router-link>
      </span>
    
      <v-btn
        @click="UserFollow"
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
    followings: Array,
    follower: [Array, Object],
    isMyPage: Boolean
  },
  methods: {
    async UserFollow () {
      const UserNickname = this.follower.nickname
      await axios.get(`http://127.0.0.1:8080/userPage/${UserNickname}`)
        .then((res) => {
          this.followingid = res.data.id
        })
      const followInfo = {
        userId : this.$store.state.UserStore.user.user_id,
        followingid : this.followingid
      }
      if (!this.isFollow) {
        await axios.post(`http://127.0.0.1:8080/followingList/Insert/${followInfo.userId}/${followInfo.followingid}`, followInfo)
        .then(() => {
          console.log(this.followingid)
          this.isFollow = true
        })
      }
      else {
        await axios.delete(`http://127.0.0.1:8080/followingList/delete/${followInfo.userId}/${followInfo.followingid}`, followInfo)
        .then(() => {
          this.isFollow = false
        })
      }
    },
    initialState: function () {
      const tmp = []
      var i;
      for (i=0; i < this.followings.length; i++) {
        tmp.push(this.followings[i].nickname)
      }
      if (tmp.includes(this.follower.nickname)) {
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