<template>
    <div class="navbar-block"> 
      <v-app-bar class="navbar" fixed>
        <router-link to="/" class="router">
          <v-img 
            width="4vw"
            :src="require('@/assets/images/logo.png')"
          ></v-img>
        </router-link>
        
        <SearchBar/>
        <!-- <div v-if="getCheckLogin">
          안녕하세요, 
          <span class="nickname">{{ this.user.nickname }}</span>님
        </div> -->
        <div class="btn-group">
          <v-menu offset-y open-on-hover bottom left>
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon v-bind="attrs" v-on="on" class="btn">
                <v-icon color="light-green lighten-2">mdi-lead-pencil</v-icon>
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

          <v-btn icon class="btn" @click="goFeed">
            <v-icon>fas fa-user-friends</v-icon>
          </v-btn>

          <v-dialog
            class="scrap-dialog"
            scrollable
            width="25%"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn @click="getSeeds" icon v-bind="attrs" v-on="on" class="btn">
                <v-icon>mdi-cart</v-icon>
              </v-btn>
            </template>
            <v-card>
              <v-card-title>장바구니에 담겨있는 씨앗이에요🌱</v-card-title>
              <v-divider></v-divider>
              <v-card-text>
                <v-list v-for="(seed, index) in seeds" :key="index">
                  <v-list-item>
                    <v-list-item-content>
                      <div class="chips">
                        <v-chip class="ma-2">{{ seed.week }}주</v-chip>
                        <v-chip class="ma-2">주 {{ seed.day }}회</v-chip>
                      </div>
                      <div class="seed-info">
                        <div class="title">
                          <p>{{ seed.title }}</p>
                        </div>
                      </div>
                    </v-list-item-content>
                    <v-list-item-action>
                      <v-btn color="#AED581" class="white--text" @click="goDeleteScrap(seed.id)">삭제하기</v-btn>   
                    </v-list-item-action>
                  </v-list-item>
                  <v-divider></v-divider>   
                </v-list>
              </v-card-text>
            </v-card>
          </v-dialog>

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
                <v-list-item>
                  <router-link :to="{ name: 'Login'}">
                    <v-list-item-title>로그인</v-list-item-title>
                  </router-link>
                </v-list-item>
                <v-list-item>
                  <router-link :to="{ name: 'Signup'}">
                    <v-list-item-title>회원가입</v-list-item-title>
                  </router-link>
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
      seeds: [],
      notice: false,
      currentTab: null,
      tabs: [],
      types:['알림','요청'],	
    };
  },
  computed:  {
    ...mapState('UserStore', ['user']),
    CheckUserInfo () {
      this.userInfo = this.user 
      return this.userInfo.nickname
    },
    getCheckLogin () {
      return this.$store.getters["UserStore/getCheckLogin"];
    },
  },  
  watch: {
    tabs : function(){
      if(!this.tabs.length)
        this.NotificationList();
    },
    seeds(newVal, oldVal) {
      if (newVal!== oldVal) {
        return newVal
      }
    },
  }, 
  methods: {
    getSeeds: function () {
      const userId_num = this.user.user_id
      const userId = {};
      userId["userid"] = userId_num
      axios.get(`http://127.0.0.1:8080/userPage/LikeAndfavChallenge/${userId_num}`, userId)
        .then((res) => {
          const seeds = res.data
          console.log(seeds)
          seeds.sort(function(a,b) {
            return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
          })
          this.seeds = seeds
        })
        .catch((err) => {
          console.log(err)
        })
    },
    goFeed: function () {
      this.$router.push({ name: 'Feed' }) 
    },
    goLogout: function () {
      this.$router.push({ name: 'Main' })
      this.$store.dispatch('UserStore/logOut')
    },
    goDeleteScrap: function (val) {
      const userId_num = this.user.user_id;
      const cgId_num = val;
      axios.get(`http://127.0.0.1:8080/userPage/DeletefavChallenge/${userId_num}/${cgId_num}`)
        .then(() => {
          this.getSeeds()
        })
        .catch((err) => {
          console.log(err)
        })
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
      const userId =  this.user.user_id;
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
.v-sheet.v-app-bar.v-toolbar:not(.v-sheet--outlined) {
  box-shadow: none;
}
.theme--light.v-app-bar.v-toolbar.v-sheet{
  background: white;
}
a:-webkit-any-link {
    color: black;
    cursor: pointer;
    text-decoration: none;
}

.btn-group {
  padding-left: 17%;
}

.navbar-block {
  margin-bottom: 1%;
}

.router {
  padding-right:30%;;
}

.navbar {
  height: 9vh !important;
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
  color: #AED581;
}

.v-dialog__content{
  .v-dialog{
    .v-card{
      .v-card__title{
        display: flex;
        justify-content: center;
        font-size: 1.1rem;
        font-weight: bold;
      }
      .v-card__text{
        height: 30vh;
        padding: 2% 1% 2% 1%;
      }
        .v-list{
          .v-list-item{
            height: 2vh;
            padding: 0 2%;
            margin-bottom: 1%;
            .v-list-item__content{
              display: flex;
              justify-content:space-around;
              .chips{
                display: flex;
                justify-content: flex-start;
                .v-chip{
                  width: 3vw;
                  height: 2vh;
                  margin: 1% !important;
                  display: flex;
                  justify-content: center;
                  font-size:0.7rem;
                  .v-chip___content{
                    font-size: 0.5rem;
                  }
                }
              }
              .seed-info{
                .title{
                  margin-left: 1%;
                  p{
                    font-size: 1.1rem;
                  }
                }
              }
            }
            .v-list-item__action{
              .v-btn{
                width: 4vw;
                height: 3vh;
                font-size: 0.8rem;
              }
            }
          }
          .v-divider {
            margin: 1% 0;
          }
        }
    }
  }
}
</style>