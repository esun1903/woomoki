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
    // 클릭했을 때 토글되면서 팔로우, 언팔로우
    async UserFollow () {

      // 유저 닉네임에 해당하는 userid값 가져오기
      const UserNickname = this.follower.nickname
      await axios.get(`http://127.0.0.1:8080/userPage/${UserNickname}`)
        .then((res) => {
          this.followingid = res.data.id
        })
        
      // 오브젝트 생성
      const followInfo = {
        userId : this.$store.state.UserStore.user.user_id,
        followingid : this.followingid
      }

      // isFollow가 false(언팔로우 상태면) 팔로우 한다.
      if (!this.isFollow) {
        await axios.post(`http://127.0.0.1:8080/followingList/Insert/${followInfo.userId}/${followInfo.followingid}`, followInfo)
        .then(() => {
          console.log(this.followingid)
          this.isFollow = true
        })
      }
      // isFollow가 true(팔로우 상태면) 언팔로우 한다.
      else {
        await axios.delete(`http://127.0.0.1:8080/followingList/delete/${followInfo.userId}/${followInfo.followingid}`, followInfo)
        .then(() => {
          this.isFollow = false
        })
      }
    },
    initialState: function () {
      // 현재 팔로우 상태 체크
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