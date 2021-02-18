<template>
  <div class="navbar-block">
    <v-app-bar class="navbar" fixed>
      <router-link to="/" class="router">
        <v-img width="4vw" :src="require('@/assets/images/logo.png')"></v-img>
      </router-link>

      <SearchBar />
      <div class="btn-group">
        <span v-if="getCheckLogin">
          <v-menu offset-y open-on-hover bottom left>
            <template v-slot:activator="{ on, attrs }">
              <!-- <v-btn v-if="getCheckLogin" icon v-bind="attrs" v-on="on" class="btn nav-cursur"> -->
              <v-btn icon v-bind="attrs" v-on="on" class="btn nav-cursur">
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
                <v-list-item-title>
                  <router-link to="/seedJoin">
                    씨앗 물 주기
                  </router-link>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </span>
        <span v-if="getCheckLogin">
          <v-menu offset-y open-on-hover bottom left>
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon class="btn nav-cursur" v-bind="attrs" v-on="on">
                <v-icon>fas fa-user-friends</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item>
                <v-list-item-title>
                  <router-link to="/feedAll">
                    모든 유저 피드
                  </router-link>
                </v-list-item-title>
              </v-list-item>
              <v-list-item v-if="getCheckLogin">
                <v-list-item-title>
                  <router-link to="/feedFollow">
                    팔로우 유저 피드
                  </router-link>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </span>


        <span v-if="getCheckLogin">
          <v-dialog class="scrap-dialog" scrollable width="25%">
            <template v-slot:activator="{ on, attrs }">
              <!-- <v-btn v-if="getCheckLogin" @click="getSeeds" icon v-bind="attrs" v-on="on" class="btn"> -->
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
                        <div @click="goSeedDetail(seed.id)" class="title">
                          <p>{{ seed.title }}</p>
                        </div>
                      </div>
                    </v-list-item-content>
                    <v-list-item-action>
                      <v-btn color="#9CCC65" class="white--text" @click="goDeleteScrap(seed.id)">삭제하기</v-btn>
                    </v-list-item-action>
                  </v-list-item>
                  <v-divider></v-divider>
                </v-list>
              </v-card-text>
            </v-card>
          </v-dialog>
        </span>

        <span v-if="getCheckLogin">
          <!-- <v-btn v-if="getCheckLogin" icon class="btn" @click="notice = true"> -->
          <v-btn icon class="btn" @click="notice = true">
             <v-icon :color="bell ? 'red' : '' ">mdi-bell-ring</v-icon>
          </v-btn>
          <v-dialog v-model="notice" width="25%">
            <div class="notification">
              <v-card class="notice-card">
                <v-tabs background-color="#AED864" v-model="currentTab" grow dark>
                  <v-tabs-slider color="white"></v-tabs-slider>
                  <v-tab v-for="(type,idx) in types" :key="idx">
                    {{ type}}
                  </v-tab>
                </v-tabs>

                <v-tabs-items v-model="currentTab">
                  <v-tab-item v-for="(type,idx) in types" :key="idx">
                    <v-card v-for="(tab,idx) in tabs" :key="idx" @click="NotificationConfirm(tab.id)" height="100">
                      <v-card color="#B2DFDB" v-if="tab.confirm_date === null" >  
                        <div class="diCard">
                          <v-card-text v-if="tab.type=='reqFollow'">
                            <router-link :to="{ name: 'UserPage', params: { userNickname: tab.reqUserName }}">
                              {{tab.reqUserName}}
                            </router-link>
                            님이 팔로우 하였습니다.
                            <div class="notiBtn">
                              <v-btn color="#AED864" class="white--text" @click="notificationDelete(tab.id)">알림삭제
                              </v-btn>
                            </div>
                          </v-card-text>
                          <v-card-text v-if="tab.type=='reqChallenge'">
                            <router-link :to="{ name: 'UserPage', params: { userNickname: tab.reqUserName }}">
                              {{tab.reqUserName}}
                            </router-link>
                            님이
                            <router-link :to="{ name: 'SeedDetail', params: { seedId: tab.cng_id }}">
                              {{tab.cngTitle}}
                            </router-link>
                            챌린지 참가 요청을 하였습니다.
                            <div class="notirqBtn">
                              <v-btn color="#AED864" class="white--text"
                                @click="challengeOKay(tab.request_user,tab.cng_id),notificationDelete(tab.id)">수락
                              </v-btn>
                              <v-btn color="#AED864" class="white--text"
                                @click="challengeReject(tab.request_user,tab.cng_id),notificationDelete(tab.id)">거절
                              </v-btn>
                            </div>
                          </v-card-text>
                          <v-card-text v-if="tab.type=='resChallenge'">
                            <router-link :to="{ name: 'SeedDetail', params: { seedId: tab.cng_id }}">
                              {{tab.cngTitle}}
                            </router-link>
                            챌린지 참여 완료.
                            <div class="notiBtn">
                              <v-btn color="#AED864" class="white--text" @click="notificationDelete(tab.id)">
                                알림삭제
                              </v-btn>
                            </div>
                          </v-card-text>
                        </div>
                      </v-card>

                      <v-card v-else color="">
                        <div class="diCard">
                          <v-card-text v-if="tab.type=='reqFollow'">
                            <v-row class="d-flex align-center">
                              <v-col class="d-flex justify-start">
                                <router-link :to="{ name: 'UserPage', params: { userNickname: tab.reqUserName }}">
                                  {{tab.reqUserName}}</router-link>
                                님이 팔로우 하였습니다.
                              </v-col>
                              <v-col class="d-flex justify-end">
                                <div class="notiBtn">
                                  <v-btn color="#AED864" class="white--text" @click="notificationDelete(tab.id)">알림삭제
                                  </v-btn>
                                </div>
                              </v-col>
                            </v-row>
                          </v-card-text>
                          <v-card-text v-if="tab.type=='reqChallenge'">
                            <router-link :to="{ name: 'UserPage', params: { userNickname: tab.reqUserName }}">
                              {{tab.reqUserName}}</router-link>
                            님이 <router-link :to="{ name: 'SeedDetail', params: { seedId: tab.cng_id }}">{{tab.cngTitle}}
                            </router-link> 챌린지 참가 요청을 하였습니다.
                            <div class="notirqBtn">
                              <v-btn color="#AED864" class="white--text"
                                @click="challengeOKay(tab.request_user,tab.cng_id),notificationDelete(tab.id)">수락
                              </v-btn>
                              <v-btn color="#AED864" class="white--text"
                                @click="challengeReject(tab.request_user,tab.cng_id),notificationDelete(tab.id)">거절
                              </v-btn>
                            </div>
                          </v-card-text>

                          <v-card-text v-if="tab.type=='resChallenge'">
                            <router-link :to="{ name: 'SeedDetail', params: { seedId: tab.cng_id }}">{{tab.cngTitle}}
                            </router-link>챌린지 참여 완료.
                            <div class="notiBtn">
                              <v-btn color="#AED864" class="white--text" @click="notificationDelete(tab.id)">알림삭제
                              </v-btn>
                            </div>
                          </v-card-text>
                        </div>
                      </v-card>
                    </v-card>
                  </v-tab-item>
                </v-tabs-items>
                <v-card-actions class="d-flex justify-end">
                  <v-btn color="#AED864" text @click="notice = false">
                    Close
                  </v-btn>
                </v-card-actions>
              </v-card>
            </div>
          </v-dialog>
          </span>

          <v-menu offset-y open-on-hover bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon v-bind="attrs" v-on="on" class="btn nav-cursur">
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
  import {
    mapState
  } from "vuex";
  import axios from 'axios';

  export default {
    name: 'Navbar',
    components: {
      SearchBar
    },
    directives: {},
    data: function () {
      return {
        bell:false,
        userInfo: [],
        userId: "",
        myNickname: "",
        seeds: [],
        notice: false,
        currentTab: null,
        tabs: [],
        types: ['알림'],
      };
    },
    computed: {
      ...mapState('UserStore', ['user']),
      CheckUserInfo() {
        this.userInfo = this.user
        return this.userInfo.nickname
      },
      getCheckLogin() {
        return this.$store.getters["UserStore/getCheckLogin"];
      },
    },
    watch: {
      tabs: function () {
        if (!this.tabs.length)
          this.NotificationList();

      },
      seeds(newVal, oldVal) {
        if (newVal !== oldVal) {
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
            seeds.sort(function (a, b) {
              return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
            })
            this.seeds = seeds
          })
          .catch((err) => {
            console.log(err)
          })
      },
      goSeedDetail: function (val) {
        this.$router.push({
          name: 'SeedDetail',
          params: {
            seedId: val
          }
        })
      },
      goFeed: function () {
        this.$router.push({
          name: 'Feed'
        })
      },
      goLogout: function () {
        this.$store.dispatch('UserStore/logOut')
        this.$router.go(this.$router.currentRoute)
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
      NotificationConfirm: function (id) {
        const notificationId = id;
        axios.put(`http://127.0.0.1:8080/notificationConfirm/${notificationId}`)
          .then((response) => {
            this.tabs = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
      },
      NotificationCallCheck: function (id) {
        const notificationId = id;
        axios.put(`http://127.0.0.1:8080/notificationCallCheck/${notificationId}`)
          .then((response) => {
            this.tabs = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
      },
      NotificationList() {
        const userId = this.user.user_id;

        axios.get(`http://127.0.0.1:8080/notificationList/${userId}`)
          .then((response) => {
            this.tabs = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
      },

      notificationDelete: function (id) {
        const notificationId = id;
        axios.delete(`http://127.0.0.1:8080/notificationDelete/${notificationId}`)
          .then((response) => {
            this.tabs = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
      },
      challengeOKay: function (user_id, cng_id) {
        const userId = user_id;
        const cngId = cng_id;
        axios.put(`http://127.0.0.1:8080/joinResultUpdate/${userId}/${cngId}/0`)
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
      },
      challengeReject: function (user_id, cng_id) {
        const userId = user_id;
        const cngId = cng_id;
        axios.delete(`http://127.0.0.1:8080/joinChallengeDelete/${userId}/${cngId}`)
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
      },
      NotificationToast() {
        
        var flag =false;
        for (var i = 0; i < this.tabs.length; i++) {
          var pushMsg = "";

          if (this.tabs[i].type == 'reqFollow' && this.tabs[i].msg == '1') {
            pushMsg = this.tabs[i].reqUserName + "님이 팔로우 하였습니다.";
          } else if (this.tabs[i].type == 'reqChallenge' && this.tabs[i].msg == '1') {
            pushMsg = this.tabs[i].reqUserName + "님이  '" + this.tabs[i].cngTitle + "'챌린지 참가 신청을 하였습니다.";
          } else if (this.tabs[i].type == 'resChallenge' && this.tabs[i].msg == '1') {
            pushMsg = "'" + this.tabs[i].cngTitle + "' 챌린지 참여 완료."
          }
          
          if(this.tabs[i].confirm_date ===null){
              flag=true;
          }

          if (pushMsg != "") {
            this.$toast.open({
              message: pushMsg,
              type: 'info',
              duration: 3000,
              dismissible: true
            });
            this.NotificationCallCheck(this.tabs[i].id);
          }
        }
          if(flag){
            this.bell=true;
          }else{
            this.bell=false;
          }
      }

    },
    created() {

      setInterval(() => this.NotificationList(), 2000);
      setInterval(() => this.NotificationToast(), 3000);

    },

  };
</script>

<style lang="scss" scoped>
  // 개발자 도구로 찍고 나서야 바꿀 수 있는 부분
  .v-sheet.v-app-bar.v-toolbar:not(.v-sheet--outlined) {
    box-shadow: none;
  }

  .theme--light.v-app-bar.v-toolbar.v-sheet {
    background: white;
  }

  a:-webkit-any-link {
    color: black;
    cursor: pointer;
    text-decoration: none;
  }

  .diCard {
    padding-left: 2%;
  }

  .notirqBtn {
    position: absolute;
    margin-top: 2%;
    padding-left: 62%;
  }

  // .notiBtn{
  //   position: absolute;
  //   margin-top: 2%;
  //   padding-left: 72%;
  // }

  .btn-group {
    padding-left: 17%;
  }

  .navbar-block {
    margin-bottom: 1%;
  }

  .router {
    padding-right: 30%;
    ;
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

  .v-dialog>* {
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
    color: #9CCC65;
  }

  .v-dialog__content {
    .v-dialog {
      .v-card {
        .v-card__title {
          display: flex;
          justify-content: center;
          font-size: 1.1rem;
          font-weight: bold;
        }

        .v-card__text {
          height: 45vh;
          padding: 2% 1% 2% 1%;
        }

        .v-list {
          .v-list-item {
            height: 2vh;
            padding: 0 2%;
            margin-bottom: 1%;

            .v-list-item__content {
              display: flex;
              justify-content: space-around;

              .chips {
                display: flex;
                justify-content: flex-start;

                .v-chip {
                  width: 3.5vw;
                  height: 2vh;
                  margin: 1% !important;
                  display: flex;
                  justify-content: center;
                  font-size: 0.7rem;

                  .v-chip___content {
                    font-size: 0.5rem;
                  }
                }
              }

              .seed-info {
                .title {
                  margin-left: 1%;
                  display: -webkit-box;
                  -webkit-box-orient: vertical;
                  -webkit-line-clamp: 1;
                  overflow: hidden;

                  p {
                    font-family: 'S-CoreDream-4Regular';
                    font-size: 1.1rem;
                  }
                }
              }
            }

            .v-list-item__action {
              .v-btn {
                width: 4vw;
                height: 4vh;
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

  .nav-cursur {
    cursor: default
  }
</style>