<template>
<div>
    <v-avatar 
      color="success"
      width="300"
      height="300"
      class="profile-img"
      >
      <span class="white--text headline">사진</span>
    </v-avatar>
    <div class="inline-Info">
      <div class="align-Id-follow-edit">
        <span class="user-id">rladydals123</span>
        <v-btn-toggle
         active-class="toggle-btn">
          <v-btn
            :ripple="false"
            class="btn-margin"
            color="primary"
            width="60"
            height="30"
            >
            팔로우
          </v-btn>
        </v-btn-toggle>
        <v-btn
          :ripple="false"
          class="btn-margin"
          plain
          width="100"
          >
          <router-link to="/userPage/password">프로필 편집</router-link>    
        </v-btn>
      </div>
      <div class="user-level">👩‍💼 Lv. 17</div>
      <div class="user-introduce">안녕하세용~~ㅎㅎ</div>
      <div class="user-wallet">나의 캐시 : 3000원</div>
      <div class="follow-info">
        <div>
          <v-row justify="center">
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
                >
                  <div>팔로워</div>
                  <div>116</div>
                </v-btn>
              </template>
              <v-card>
                  <v-card-title class="title-close-inline">팔로워
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="dialog.dialog = false"
                    >
                      Close
                    </v-btn>
                  </v-card-title>
                <v-divider></v-divider>
                
                <v-card-text style="height: 300px;">
                  <div 
                    v-for="follower in followers"
                    :key="follower"
                    class="name-follow">
                    <span><a href="">{{ follower }}</a></span>
                    <v-btn
                      color="primary"
                      small
                      class="follow-margin"
                      >
                    팔로우
                    </v-btn>
                  </div>
                </v-card-text>
                <v-card-actions>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-row>
        </div>
        <div>
          <v-row justify="center">
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
                  <div>팔로잉</div>
                  <div>122</div>
                </v-btn>
              </template>
              <v-card>
                  <v-card-title class="title-close-inline">팔로잉
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="dialog.dialog2 = false"
                    >
                      Close
                    </v-btn>
                  </v-card-title>
                <v-divider></v-divider>
                
                <v-card-text style="height: 300px;">
                  <div 
                    v-for="following in followings"
                    :key="following"
                    class="name-follow">
                    <a href="">{{ following }}</a>
                    <v-btn
                      color="primary"
                      small
                      class="follow-margin"
                      >
                    팔로우
                    </v-btn>
                  </div>
                </v-card-text>
                <v-card-actions>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-row>
        </div>
      </div>
    </div>
</div>
</template>

<script>
// import axios from "axios";
// import { mapState } from "vuex"

export default {
  name: "BasicUserInfo",
  components: {
    // mapState,
  },
  data: function () {
    return {
      myId: "",
      myState: "",
      userState: "", 
      dialog: {
        dialogm1: "",
        dialog: false,
        dialogm2: "",
        dialog2: false,
      },
      followers: [ "김용민", "김용민", "김용민", "김용민", "김용민", "김용민", "김용민", "김용민", "김용민", "김용민"],
      followings: [ "홍지희", "표기동", "최은선", "김효진"],
    }
  },
  methods: {
    // setToken: function () {
    //   const token = localStorage.getItem("jwt");
    //   const config = {
    //     headers: {
    //       Authorization: `JWT ${token}`,
    //     },
    //   };
    //   return config;
    // },

    // jwt 토큰
    // setToken: function () {
    //   const config = this.setToken();
    //   axios.get("??", config)
    //     .then((res) => {
    //       this.myId = res.data
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //     })
    // },

    // 마이페이지 아이콘 or 다른사람의 닉네임을 누르면 
    // router로 페이지 이동과 함께 params or query로 유저 아이디를 vuex state에 저장한다.
    // 그리고 UserPage.vue가 렌더링 될때 vuex state에 있는 아이디를 post에 보내서 유저 정보를 렌더링
    // jwt토큰을 가져와서 그 유저정보와 지금 렌더링 되는 유저 정보가 같으면 마이페이지 렌더링, 아니면 유저페이지 렌더링
    // BasicUserInfo: function () {
    //   axios.post("??/userId", userid)
    //     .then((res) => {
    //       this.userInfo = res.data
    //       if (this.myId === userid) {
    //         // 내 페이지
    //         // myState에 따라 태그에 v-if 렌더링
    //         this.myState = true;
    //       } else {
    //         // 다른 유저 페이지
    //         this.myState = false;
    //       }
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //     })
    // },
    // UserFollowerBtn: function () {
      
    // },
    // created() {
    //   // this.setToken()
    //   // this.BasicUserInfo()
    // },
    // computed: {
    //   ...mapState({
    //     userid: 'state에서 유저닉네임이 저장되어있는 변수'
    //   })
    // }
  }
}
</script>

<style lang="scss">
/* 안됨..
@import "./BasicUserInfo.scss"; */
a {text-decoration: none;}

@mixin between {
  display: flex;
  justify-content: space-between;
}

@mixin margin {
  margin: 2px;
}

.title-close-inline {
  @include between;
}

.name-follow {
  @include between;
}

.follow-margin {
  @include margin;
}

.inline-Info {
  display: inline-block;
  margin-top: 100px;
}

.profile-img {
  margin-right: 150px;
  margin-bottom: 200px;
}

.user-id {
  font-size: 30px;
  margin-right: 30px;
}

.align-Id-follow-edit {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}

.user-level {
  font-size: 15px;
  margin-bottom: 20px;
}

.user-introduce {
  font-size: 20px;
  margin-top: 10px;
  margin-bottom: 20px;
}

.user-wallet {
  margin-bottom: 30px;
}

.follow-info {
  font-size: 30px;
  margin-right: 30px;
  margin-top: 10px;
  display: flex;
  justify-content: space-around;
}

.btn-profile .btn-margin {
  margin-right: 10px;
}

.positon-center {
  justify-content: center;
}

.btn {
  opacity: 1;
}

.toggle-btn {
  color: skyblue;
}

</style>