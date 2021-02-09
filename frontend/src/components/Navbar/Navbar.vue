<template>
    <div class="navbar-block"> 
      <v-app-bar class="navbar" fixed>
        <router-link to="/">
          <v-img 
            width="4vw"
            class="mt-3"
            :src="require('@/assets/images/logo.png')"
          ></v-img>
        </router-link>
        <v-spacer></v-spacer>
        <SearchBar/>
        <v-spacer></v-spacer>

        <div class="btn_group">
          <v-menu offset-y open-on-hover bottom left>
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon v-bind="attrs" v-on="on" class="btn">
                <v-icon>mdi-lead-pencil</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item>
                <v-list-item-title>
                  <router-link to="/createSeed">
                    씨앗 만들기
                  </router-link>
                </v-list-item-title>
              </v-list-item>
              <v-list-item>
                <v-list-item-title>씨앗 물 주기</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <v-btn icon class="btn">
            <v-icon>mdi-cart</v-icon>
          </v-btn>

          <v-btn icon class="btn" @click="notice = true">
            <v-icon>mdi-bell-ring</v-icon>
          </v-btn>
          <v-dialog v-model="notice">
          <div class="notification">
            <v-card class="notice-card">
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
          </div>
          </v-dialog>
          
          <v-menu offset-y open-on-hover bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon v-bind="attrs" v-on="on" class="btn">
                <v-icon color="grey darken-1">mdi-account-circle</v-icon>
              </v-btn>
            </template>
            <v-list>
              <div v-if="getCheckLogin">
                <v-list-item @click="goMyPage">
                  <router-link :to="{ name: 'UserPage', params: { userId: userId, userNickname: userNickname }}">
                    <v-list-item-title>마이페이지</v-list-item-title>
                   </router-link>
                </v-list-item>
                <v-list-item @click="goLogout">
                  <v-list-item-title>로그아웃</v-list-item-title>
                </v-list-item>
              </div>
              <div v-else>
                <v-list-item @click="goLogin">
                  <v-list-item-title>로그인</v-list-item-title>
                </v-list-item>
                <v-list-item @click="goSignup">
                  <v-list-item-title>회원가입</v-list-item-title>
                </v-list-item>
              </div>

            </v-list>
          </v-menu>
        </div>
      </v-app-bar>
    </div>
</template>

<script>
import SearchBar from "@/components/Navbar/SearchBar.vue";

export default {
  name: 'Navbar',
  components: { SearchBar },
  directives: {  },
  data: function () {
    return {
      userId: this.$store.state.UserStore.user.user_id,
      userNickname: this.$store.state.UserStore.user.nickname,
      notice: false,
      currentTab: null,
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
  computed:  {
    getCheckLogin () {
      return this.$store.getters["UserStore/getCheckLogin"];
    }
  },  
  methods: {
    
    goMyPage: function () {
      this.$store.dispatch("UserStore/compareId", this.$store.state.UserStore.user.user_id);
    },
    goLogout: function () {
      this.$store.dispatch('UserStore/logOut')
    },
    goLogin: function () {
      this.$router.push({ name: 'Login' }) 
    },
    goSignup: function () {
      this.$router.push({ name: 'Signup' })  
    },

  },
};
</script>

<style lang="scss" scoped>
// 개발자 도구로 찍고 나서야 바꿀 수 있는 부분

.notification{
 float:right;
}
.v-sheet.v-app-bar.v-toolbar:not(.v-sheet--outlined) {
  box-shadow: none;
}
.theme--light.v-app-bar.v-toolbar.v-sheet{
  background: transparent;
}
a:-webkit-any-link {
    color: black;
    cursor: pointer;
    text-decoration: none;
}


.navbar-block {
  margin-bottom: 5%;
}

.navbar {
  a {
    :-webkit-any-link {
    color: black;
    text-decoration: none;
    }
    p {
      font-size: 1.5rem;
      color: black;
    }
  }
}
.v-dialog > * {
  width: 25%;
}
.v-tabs-slider {
  color: red;
}
.v-dialog__content {
  display: flex;
  align-items: flex-start;
}
</style>