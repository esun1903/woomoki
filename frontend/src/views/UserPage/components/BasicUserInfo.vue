<template>
  <v-container>

    <v-row class="d-flex justify-space-around align-center">
      
      <v-col class="d-flex justify-start">
        <v-avatar 
          color="grey lighten-3"
          width="250"
          height="250"
          >
          <span class="black--text headline">
            
            <v-img
              :src="this.UserInfo.img">
            </v-img>
          </span>
        </v-avatar>
      </v-col>
 
      <v-col>
        <v-row 
          class="d-flex align-center">
          <v-col>
            <h2>
              {{ this.UserInfo.nickname }}
            </h2>
          </v-col>

          <v-col class="d-flex justify-center">
            <v-btn-toggle
              active-class="toggle-btn">
              <v-btn
                v-if="isMyPage === false"
                :ripple="false"
                color="primary"
                width="60"
                height="30"
                @click="BasicUserInfo"
                >
                팔로우
              </v-btn>
            </v-btn-toggle>
          </v-col>

          <v-col>
            <router-link to="/comparepwd">
              <v-icon v-if="isMyPage === true">fas fa-user-cog</v-icon>
            </router-link>    
          </v-col>
        </v-row>

        <v-row>
          <v-col>
            <div>👩‍💼 Lv. {{ this.UserInfo.levelnum }} {{ this.UserInfo.leveltitle }}</div>
          </v-col>
        </v-row>

        <v-row class="d-flex justify-space-around">
          <v-col>
            <v-btn
              plain
              text
              :ripple="false"
            >
              <div v-if="isMyPage === true">
                <h2>나의 캐시</h2>
                <div>{{ this.UserInfo.deposit }}원</div>
              </div>
            </v-btn>
          </v-col>
          <FollowerList></FollowerList>
          <FollowingList></FollowingList>
          
        </v-row>
        
        <v-row>
          <v-col>
            <div>{{ this.UserInfo.introduce }}</div>
          </v-col>
        </v-row>

      </v-col>
    </v-row>
    <v-row class="mt-0">
      <ChallengeResults></ChallengeResults>
    </v-row>
  </v-container>
</template>

<script>
import ChallengeResults from "./ChallengeResults"
import FollowerList from "./FollowerList"
import FollowingList from "./FollowingList"
import axios from "axios";
// import { mapState } from "vuex"

export default {
  name: "BasicUserInfo",
  components: {
    ChallengeResults,
    FollowerList,
    FollowingList
    // mapState,
  },
  data: function () {
    return {
      isMyPage: false,
      UserInfo: [],
    }
  },
  methods: {
    // jwt 토큰 활용
    // setToken: function () {
    //   const token = localStorage.getItem("jwt");
    //   const config = {
    //     headers: {
    //       Authorization: `JWT ${token}`,
    //     },
    //   };
    //   return config;
    // },
    // getUserId: function () {
    //   const config = this.setToken();
    //   axios
    //     .get("http://127.0.0.1:8000/userPage/", config)
    //     .then((res) => {
    //       console.log(res.data);
    //       this.myId = res.data.user_id;
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // },

    // 마이페이지 아이콘 or 다른사람의 닉네임을 누르면 
    // router로 페이지 이동과 함께 params로 vuex state에 저장되어 있는 userId를 보낸다.
    // 그리고 UserPage.vue가 렌더링 될때 vuex state에 있는 로그인한 사람의 myId와 params로 받은 userId를 비교하여 같으면
    // 마이페이지를 렌더링 하고 다르면 유저페이지를 렌더링한다.
    BasicUserInfo: function () {
      const MyNickname = this.$store.state.UserStore.user.nickname
      const user_id = this.$store.state.UserStore.user.user_id
      // query사용
      // const UserNickname = this.$route.query.userNickname
      // nickname을 보내서 응답받도록 바꿔야 할듯..?
      const UserNickname = this.$route.params.userNickname

      // 얘가 한번만 실행되야하는데...
      this.$store.dispatch("UserStore/compareId", user_id);

      console.log(MyNickname, UserNickname)
      axios.get(`http://127.0.0.1:8080/userPage/${user_id}`)
        .then((res) => {
          this.UserInfo = res.data
          if (MyNickname === UserNickname) {
            
            // 내 페이지
            // myState에 따라 태그에 v-if 렌더링
            this.isMyPage = true;
          } else {
            
            // 다른 유저 페이지
            this.isMyPage = false;
          }
          console.log(this.isMyPage)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    // UserFollowerBtn: function () {
      
    // },

  },
  created() {
    // this.setToken()
    this.BasicUserInfo()
    // this.getUserId()
  },
  computed: {
      // ...mapState('UserStore', ['user_id'])
  },
  // watch: {
  //   UserInfo: function() {
  //     this.BasicUserInfo();
  //   }
  // }
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
