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
          <router-link to="/userPage/userPageEdit/password">프로필 편집</router-link>    
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

export default {
  name: "BasicUserInfo",
  data: function () {
    return {
      test: "",
      userState: "", 
      dialog: {
        dialogm1: "",
        dialog: false,
        dialogm2: "",
        dialog2: false,
      },
      userId: "",
      userInfo: [],
      followers: [ "김용민", "김용민", "김용민", "김용민", "김용민", "김용민", "김용민", "김용민", "김용민", "김용민"],
      followings: [ "홍지희", "표기동", "최은선", "김효진"],
    }
  },
  methods: {
    setToken: function () {
      const token = localStorage.getItem("jwt");
      const config = {
        headers: {
          Authorization: `JWT ${token}`,
        },
      };
      return config;
    },
    // 마이페이지아이콘을 눌러 이 페이지로 이동할 때
    // jwt 토큰 정보를 통해 config 생성 후에 요청으로 보내 로그인한 사람의 정보를 응답받는다.
    // 로그인한 사람의 userid와 
    // setToken: function () {
    //   const config = this.setToken();
    //   axios.get("??", config)
    //     .then((res) => {
    //       this.userInfo = res.data
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //     })
    // },

    // 다른 유저 페이지를 따로만들어야 할듯?..
    // 다른 유저 페이지
    // 누른 아이디(닉네임) 정보를 요청으로 보내 userid(pk)를 응답받는다.
    // GetUserId: function () {
    //   const nickName = this.nickName
    //   axios.post("??", nickName)
    //     .then((res) => {
    //       this.UserId = res.data
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //     })
    // }

    // BasicUserInfo: function () {
    //   axios.get("??/userId")
    //     .then((res) => {
    //       this.userInfo = res.data
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //     })
    // },
    UserFollowerBtn: function () {
      
    },
    created() {
      // this.GetUserId()
      // this.BasicUserInfo()
    }
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