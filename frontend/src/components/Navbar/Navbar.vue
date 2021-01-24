<template>
  <v-app>
    <div>
      <v-app-bar dark id="app-bar">
        <v-btn :ripple="false" id="service-name" @click="goMainPage">
          GRITREE
        </v-btn>

        <!-- 간격 띄우기 용 -->
        <!-- <v-spacer></v-spacer> -->

        <!-- 누르면 스크랩 페이지로 이동 -->
        <div id="right">
          <v-btn id="btns" :ripple="false" @click="goChallengeCreate">
            챌린지 생성하기
          </v-btn>

          <v-btn icon id="btns">
            <v-icon>mdi-cart</v-icon>
          </v-btn>

          <v-btn id="btns" @click="notice = true">
            <v-icon>mdi-bell-ring</v-icon>
          </v-btn>
          <v-dialog v-model="notice">
            <v-card id="notice-card">
              <v-tabs v-model="currentTab" grow dark>
                <v-tabs-slider></v-tabs-slider>
                <v-tab v-for="tab in tabs" :key="tab.title">
                  {{ tab.title }}
                </v-tab>
              </v-tabs>

              <v-tabs-items v-model="currentTab">
                <v-tab-item v-for="tab in tabs" :key="tab.title">
                  <v-card flat>
                    <v-card-text>{{ tab.content }}</v-card-text>
                  </v-card>
                </v-tab-item>
              </v-tabs-items>
              <v-card-actions>
                <v-btn color="primary" text @click="notice = false">
                  Close
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          
          <v-menu offset-y open-on-hover bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon v-bind="attrs" v-on="on" id="btns">
                <v-icon>mdi-account-circle</v-icon>
              </v-btn>
            </template>

            <v-list>
              <div v-if="login">
                <v-list-item @click="goMyPage">
                  <v-list-item-title>마이페이지</v-list-item-title>
                </v-list-item>
                <v-list-item @click="goLogout">
                  <v-list-item-title>로그아웃</v-list-item-title>
                </v-list-item>
              </div>
              <div v-else>
                <v-list-item  @click="goLogin">
                  <v-list-item-title>로그인</v-list-item-title>
                </v-list-item>
                <v-list-item @click="goJoin">
                  <v-list-item-title>회원가입</v-list-item-title>
                </v-list-item>
              </div>

            </v-list>
          </v-menu>
        </div>
      </v-app-bar>
    </div>
  </v-app>
</template>

<script>
export default {
  name: 'Navbar',
  components: {  },
  directives: {  },
  data: function () {
    return {
      notice: false,
      currentTab: null,
      login: false,
      tabs: [
        {
          title: '알림',
          content: '매일 조깅 챌린지에 참가 요청을 완료했습니다.'
        },
        {
          title: '요청',
          content: '용밍밍님이 매일 조깅 챌린지 참가를 요청했습니다.'
        }
      ],
    };
  },
  mounted() {
    
  },
  created: function () {
    // const token = localStorage.getItem('jwt')

    // if (token) {
    //   this.login = true
    // }
  },  
  methods: {
    goMainpage: function () {
      // this.$router.push({ name: 'Mainpage' }) 
    },
    goMyPage: function () {
      // this.$router.push({ name: 'UserPage' }) 
    },
    goLogout: function () {
      // localStorage.removeItem('jwt')
      // this.$emit('logout')
      // this.$router.push({ name: 'Login' })
    },
    goLogin: function () {
      // this.$router.push({ name: 'Login' }) 
    },
    goJoin: function () {
      this.$router.push({ name: 'Join' })  
    },
    
  },
};
</script>

<style lang="scss">
#service-name {
  font-size: 30px;
}
.v-toolbar__content {
  display: flex;
  justify-content: space-between;
  padding: 4px 16px 4px 40px;
}
#btns {
  margin: 0px 10px 0px 5px;
}
.v-tabs-slider {
  color: red;
}
.v-dialog {
  width: 20% !important;
  box-shadow: none;
}
.v-dialog__content {
  top: -36% !important;
  left: 39% !important;
}
.notice-card {
  width: 50px;
}
</style>