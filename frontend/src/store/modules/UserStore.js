import axios from "axios";
import router from "@/router";

// 나중에 env 파일로 바꾸기
// const SERVER_URL = "http://i4a303.p.ssafy.io/";
const SERVER_URL = "http://localhost:8080";

const UserStore = {
  namespaced: true,
  state: {
    user: {
      accessToken: null,
      user_id: "",
      nickname: "",
      email: "",
      phone: "",
      password: "",    
    },
    isLogin: false,
  },
  getters: {
    // getAccessToken(state){
    //   if(localStorage.accessToken && typeof localStorage.accessToken != "undefined")
    //     return localStorage.accessToken;
    //   console.log("getAccessToken: " + localStorage.accessToken + " " + state.user.accessToken)
    //   if(state.user.accessToken=null)
    //     return false;
    //   return state.user.accessToken;
    // },
    // getUserId(state){
    //   return state.user.user_id;
    // },
    // getNickname(state){
    //   return state.user.nickname;
    // }
  }, 
  mutations: {
    LOGIN(state, payload){
      console.log("payload[auth-token]: "+payload["auth-token"]);
      state.user.accessToken = payload["auth-token"];
      state.user.user_id = payload["user_id"];
      state.user.nickname = payload["nickname"];
      state.user.email = payload["email"];
      localStorage.accessToken = state.accessToken;
      localStorage.user_id = state.user_id;
    },
    SIGN_UP(state, payload) {
      axios.post("http://localhost:8080/signup", payload)
        .then((res) => {
          console.log(res);
          console.log('회원가입 성공');
          localStorage.setItem('jwt', res.data.token);
          localStorage.setItem('user_id',res.data.user_id);
          localStorage.setItem('isLogin', true);
          state.isLogin=true;
          router.push({ name: 'FavoriteCategory' });
        })
        .catch((err) => {
          alert("회원가입에 실패했습니다.")
          console.log(err)
        })
    },
    LOG_OUT() {
      localStorage.removeItem('jwt');
      localStorage.setItem('isLogin', false);
      localStorage.setItem('user_id', null);
      router.push({ name: 'Login' });
    }
  },
  actions: {
       LOGIN(context, user){
      console.log(SERVER_URL);
      return axios
        .post(`${SERVER_URL}/login`, user)
        .then((response) => {
          context.commit("LOGIN", response.data);
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["auth-token"]}`;
          router.push({ name: 'Main' });
        }).catch((error) => {
          console.log(error);
          alert("이메일, 비밀번호 확인");
        })
    },
    signUp({commit}, payload) {
      commit('SIGN_UP', payload)
    },
    logOut({commit}) {
      commit('LOG_OUT')
    }
  }
}

export default UserStore;