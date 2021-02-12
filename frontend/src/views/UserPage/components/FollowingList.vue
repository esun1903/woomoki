<template>
  <v-col class="d-flex justify-center">
    <v-dialog
      v-model="dialog.dialog2"
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
              color="success darken-1"
              text
              @click="dialog.dialog2 = false"
              class="d-flex justify-center"
            >
              <v-icon>fas fa-times</v-icon>
            </v-btn>
          </v-card-title>
        <v-divider></v-divider>
        
        <v-card-text class="dialog-height">
          <div 
            v-for="(following, idx) in followings"
            :key="idx"
            class="d-flex justify-space-between ma-1"
            >
            <span class="d-flex align-center">
              <router-link :to="{ name: 'UserPage', params: { userNickname: following.nickname }}">
                {{ following.nickname }}
              </router-link>
            </span>
            <v-btn
              color="grey"
              small
              class="white--text"
              >
              <span>팔로잉</span>
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
  name: "FollowingList",
  data: function() {
    return {
      userId: this.$store.state.UserStore.user.user_id,
      followings: [],
      dialog: {
        dialogm2: "",
        dialog2: false,
      },
    }
  },
  methods: {
    // 내가 팔로우 하는 유저들 팔로잉 리스트 가져오기
    FollowingList: function () {
      const userId = this.userId
      axios.get(`http://127.0.0.1:8080/followingList/${userId}`)
        .then((res) => {
          this.followings = res.data
          // console.log(res.data)
        })
        .catch((err) => {
          // console.log(err)
        })
    },
    // 내가 다른 유저 팔로우 취소
    UserUnfollow: function () {
      const userId = this.userId
      //닉네임을 통해 유저 정보받아오는 api필요
      // const deleteid = ??
      axios.delete(`http://127.0.0.1:8080/followingList/delete/${userId}/${deleteid}`)
        .then((res) => {
          // console.log(res)
        })
        .catch((err) => {
          // console.log(err)
        })
    },
    // 내가 팔로우 하는 전체 팔로잉 리스트에서 해당 유저를 
    // 내가 팔로우하고 있는지 팔로우 리스트와 비교하여 확인???이상한데 뭔가
  },
  created() {
    this.FollowingList();
  }
}
</script>

<style lang="scss" scoped>

a {text-decoration: none;}


.dialog-height {
  height: 300px;
}

</style>