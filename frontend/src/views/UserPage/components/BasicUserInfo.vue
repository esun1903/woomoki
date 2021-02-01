<template>
  <v-container>

    <v-row class="d-flex justify-space-around align-center">
      
      <v-col class="d-flex justify-center">
        <v-avatar 
          color="success"
          width="300"
          height="300"
          >
          <span class="white--text headline">
            사진
            <v-img
              :src="require('@/assets/images/profile_img.jpg')">
            </v-img>
          </span>
        </v-avatar>
      </v-col>

        
      <v-col>
        <v-row 
          class="d-flex align-center">
          <v-col>
            <h2>
              rladydals123
            </h2>
          </v-col>

          <v-col class="d-flex justify-center">
            <v-btn-toggle
              active-class="toggle-btn">
              <v-btn
                :ripple="false"
                color="primary"
                width="60"
                height="30"
                >
                팔로우
              </v-btn>
            </v-btn-toggle>
          </v-col>

          <v-col>
            <router-link to="/userPage/password">
              <v-icon>fas fa-user-cog</v-icon>
            </router-link>    
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            <div>👩‍💼 Lv. 17</div>
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            <div>씨앗에 매일매일 물주기!</div>
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            <div>나의 캐시 : 3000원</div>
          </v-col>
        </v-row>

        <v-row class="d-flex justify-space-around">
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
                    v-for="follower in followers"
                    :key="follower"
                    class="d-flex justify-space-between ma-1"
                    >
                    <span class="d-flex align-center">
                      <a href="">{{ follower }}</a>
                    </span>
                    <v-btn
                      color="success"
                      small
                      >
                    팔로우
                    </v-btn>
                  </div>
                </v-card-text>
                <v-card-actions>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
       
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
                    <div>122</div>
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
                    v-for="following in followings"
                    :key="following"
                    class="d-flex justify-space-between ma-1"
                    >
                    <span class="d-flex align-center">
                      <a href="">{{ following }}</a>
                    </span>
                    <v-btn
                      color="success"
                      small
                      >
                    팔로우
                    </v-btn>
                  </div>
                </v-card-text>
                <v-card-actions>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
        </v-row>
        
      </v-col>
    </v-row>
  </v-container>
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
      followers: [ "배상연", "김상훈", "최인교", "엄윤상", "김영재", "강병훈", "조민형", "김대인", "이선규", "박상민", "강은빈", "이재현", "김동광", "손인호"],
      followings: [ "홍지희", "표기동", "최은선", "김효진", "최정휴", "현성섭", "손준희", "권기현"],
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

<style lang="scss" scoped>

a {text-decoration: none;}

@mixin between {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.title-close-inline {
  @include between;
}

.name-follow {
  @include between;
}

.container-size {
  width: 60%;
}

.user-id {
  font-size: 30px;
}

.align-Id-follow-edit {
  display: flex;
  align-items: center;
}

.user-level {
  font-size: 15px;
}

.user-introduce {
  font-size: 20px;
}


.follow-info {
  font-size: 30px;
  display: flex;
  justify-content: space-around;
}

.btn {
  opacity: 1;
}

.toggle-btn {
  color: skyblue;
}

.dialog-height {
  height: 300px;
}

</style>
