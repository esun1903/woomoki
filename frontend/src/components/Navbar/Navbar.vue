<template>
    <div class="navbar-block"> 
      <v-app-bar class="navbar" fixed>
        <router-link to="/" class="router">
          <v-img 
            width="4vw"
            class="mt-3"
            :src="require('@/assets/images/logo.png')"
          ></v-img>
        </router-link>
        
        <SearchBar/>
        <div v-if="getCheckLogin">안녕하세요, <span class="nickname">{{this.user.nickname}}</span>님</div>
        <div class="btn-group">
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
          <v-dialog v-model="notice" width="25%">
            <div class="notification">
              <v-card class="notice-card">
                <v-tabs v-model="currentTab" grow dark>
                  <v-tabs-slider></v-tabs-slider>
                  <v-tab v-for="(type,idx) in types" :key="idx">
                    {{ type}}
                  </v-tab>
                </v-tabs>

                <v-tabs-items v-model="currentTab">
                  <v-tab-item v-for="(type,idx) in types" :key="idx">
                    <v-card v-for="(tab,idx) in tabs" :key="idx" @click="NotificationConfirm(tab.id)">
                      
                      <v-card-text v-if="type=='요청' && tab.type=='reqFollow'">{{tab.get_user}}님이 팔로우 요청을 하였습니다. </v-card-text>
                      <v-card-text v-if="type=='요청' && tab.type=='reqChallenge'">{{tab.get_user}}님이 {{tab.cng_id}} 챌린지 참가 요청을 하였습니다. </v-card-text>
                      <v-card-text v-if="type=='알림' && tab.type=='resFollow'">{{tab.get_user}}님의 팔로워가 되었습니다.</v-card-text>
                      <v-card-text v-if="type=='알림' && tab.type=='resChallenge'">{{tab.cng_id}} 챌린지 참여 완료. </v-card-text>
                  
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
                <v-list-item>
                  <router-link :to="{ name: 'UserPage', params: { userNickname: CheckUserInfo }}">
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
import {mapState} from "vuex";
import axios from 'axios';

export default {
  name: 'Navbar',
  components: { SearchBar },
  directives: {  },
  data: function () {
    return {
      userInfo: [],
      userId: "",
      myNickname: "",
      notice: false,
      currentTab: null,
      tabs: [],
      types:['알림','요청'],	
    };
  },
  created() {
    console.log(this.userInfo)
  },
  computed:  {
    CheckUserInfo () {
      this.userInfo = this.$store.state.UserStore.user 
      return this.userInfo.nickname
    },
    getCheckLogin () {
      return this.$store.getters["UserStore/getCheckLogin"];
    },
    ...mapState('UserStore', ['user']),
  }, 
 watch: {
    tabs : function(){
        if(!this.tabs.length)
         this.NotificationList();
    }
  }, 
  methods: {
    goLogout: function () {
      this.$router.push({ name: 'Main' })
      this.$store.dispatch('UserStore/logOut')
    },
    goLogin: function () {
      this.$router.push({ name: 'Login' }) 
    },
    goSignup: function () {
      this.$router.push({ name: 'Signup' })  
    },
     NotificationConfirm: function(id) {
      const notificationId = id;
       axios.put(`http://127.0.0.1:8080/notificationConfirm/${notificationId}`)
        .then((response) => {
          this.tabs = response.data;
        })
        .catch((error) => {
          console.log(error);
        });  
    },
    NotificationList(){
      const userId =  this.$store.state.UserStore.user.user_id;
       axios.get(`http://127.0.0.1:8080/notificationList/${userId}`)
        .then((response) => {
          this.tabs = response.data;
        })
        .catch((error) => {
          console.log(error);
        });    
    }
  },
  mounted (){
  this.NotificationList();
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

.btn-group {
  padding-left: 20%;
}

.navbar-block {
  margin-bottom: 5%;
}

.router {
  padding-right:30%;;
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
  width: 100%;
}
.v-tabs-slider {
  color: red;
}
.v-dialog__content {
  display: flex;
  align-items: flex-start;
  justify-content: flex-end;
  margin-top: 2%;
}

.nickname {
  font: bold; 
  color: greenyellow;
}
</style>