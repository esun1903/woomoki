<template>

  <v-list-item class="border-list">
        <router-link :to="{ name: 'UserPage', params: { userNickname: user.nickname }}">
          <v-list-item-avatar size="40">
            <v-img :src="img"></v-img>
          </v-list-item-avatar>
        </router-link>

        <v-list-item-content>
          <v-list-item-title>
            <router-link class="username" :to="{ name: 'UserPage', params: { userNickname: user.nickname }}">
              {{ user.nickname }}
            </router-link>
          </v-list-item-title>
        </v-list-item-content>
        </v-list-item>
</template>

<script>
import axios from "axios"

export default {
  name: "JoinUser",
  data: function () {
    return {
      isFollow: false,
      followingid: "",
      img: "",
    }
  },
  props: {
    user: Object
  },
  methods: {
    getUserImg: function() {
      const nickname = this.user.nickname
      axios.get(`http://i4a303.p.ssafy.io/api/userPage/${nickname}`)
        .then((res) => {
          this.img = res.data.img
        })
      
    }
  },
  created() {
    this.getUserImg()
  }
}
</script>

<style lang="scss" scoped>

a {text-decoration: none;}

.username {
  font-size: 25px;
  margin: 5px;
  font-weight: bold;
  color: black
}

</style>