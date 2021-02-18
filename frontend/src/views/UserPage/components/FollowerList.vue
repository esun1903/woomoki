<template>
  <v-col class="d-flex justify-center">
    <v-dialog
      v-model="dialog"
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
        >
          <div>
            <h2>팔로워</h2>
            <div>{{ followers.length }}</div>
          </div>
        </v-btn>
      </template>
      <v-card>
          <v-card-title class="d-flex justify-space-between">팔로워
          <v-btn
            color="#AED864"
            text
            icon
            @click="dialog = false"
            class="d-flex justify-center"
          >
            <v-icon>fas fa-times</v-icon>
          </v-btn>
          </v-card-title>
        <v-divider></v-divider>
        
        <v-card-text class="dialog-height">
          <Followers v-for="(follower, idx) in followers" :key="idx" :follower="follower" :followings="followings" :isMyPage="isMyPage"></Followers>
        </v-card-text>
        <v-card-actions>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-col>
</template>

<script>
import axios from "axios"
import Followers from "./Followers"

export default {
  name: "FollowerList",
  components: {
    Followers
  },
  data: function () {
    return {
      UserInfo: [],
      followers: [],
      followings: [],
      dialogm1: "",
      dialog: false,
    }
  },
  props: {
    isMyPage: Boolean,
  },
  methods: {
    async FollowerList () {
      const UserNickname = this.$route.params.userNickname
      await axios.get(`http://i4a303.p.ssafy.io/api/userPage/${UserNickname}`)
        .then((res) => {
          this.UserInfo = res.data
        })
      const userId = this.UserInfo.id
      await axios.get(`http://i4a303.p.ssafy.io/api/followerList/${userId}`)
        .then((res) => {
          this.followers = res.data
        })
      await axios.get(`http://i4a303.p.ssafy.io/api/followingList/${userId}`)
        .then((res) => {
          this.followings = res.data
        })
    },
  },
  created() {
    this.FollowerList();
  },
  watch: {
    dialog: function () {
      if (this.dialog === false) {
        this.$router.go(this.$router.currentRoute)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.dialog-height {
  height: 300px;
}
</style>