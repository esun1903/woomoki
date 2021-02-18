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
            <h2>팔로잉</h2>
            <div>{{ followings.length }}</div>
          </div>
        </v-btn>
      </template>
      <v-card>
          <v-card-title class="d-flex justify-space-between">팔로잉
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
          <Followings v-for="(following, idx) in followings" :key="idx" :following="following" :followings="followings" :followers="followers" :isMyPage="isMyPage"></Followings>
        </v-card-text>
        <v-card-actions>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-col>
</template>

<script>
import axios from "axios"
import Followings from "./Followings"

export default {
  name: "FollowingList",
  components: {
    Followings
  },
  data: function() {
    return {
      userId: "",
      followers: [],
      followings: [],
      dialogm: "",
      dialog: false,
    }
  },
  props:{
    isMyPage: Boolean, 
  },
  methods: {
    async FollowingList () {
      const UserNickname = this.$route.params.userNickname
      await axios.get(`http://127.0.0.1:8080/userPage/${UserNickname}`)
        .then((res) => {
          this.UserInfo = res.data
        })
      const userId = this.UserInfo.id
      await axios.get(`http://127.0.0.1:8080/followerList/${userId}`)
        .then((res) => {
          this.followers = res.data
        })
      await axios.get(`http://127.0.0.1:8080/followingList/${userId}`)
        .then((res) => {
          this.followings = res.data
        })
    },
    reload: function () {
      this.$router.go(this.$router.currentRoute)
    }
  },
  created() {
    this.FollowingList();
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