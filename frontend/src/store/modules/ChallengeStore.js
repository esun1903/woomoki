import axios from "axios";
import router from "@/router";

// 나중에 env 파일로 바꾸기
// const SERVER_URL = "http://i4a303.p.ssafy.io/";
const SERVER_URL = "http://localhost:8080";

const UserStore = {
  namespaced: true,
  state: {
    // user: {
    //   accessToken: null,
    //   user_id: "",
    //   nickname: "",
    //   email: "",   
    // },
    // isLogin: false,
  },
  mutations: {
    // LOGIN(state, payload){
    //   console.log("payload[auth-token]: "+payload["auth-token"]);
    //   state.user.accessToken = payload["auth-token"];
    //   state.user.user_id = payload["user-id"];
    //   state.user.nickname = payload["user-name"];
    //   state.user.email = payload["user-email"];
    //   state.isLogin = true;
    // },
  },
  getters: {

    // getCheckLogin(state){
    //   return state.isLogin;
    // }
  }, 
  actions: {
    // signUp({commit}, user) {
    //   return axios
    //   .post(`${SERVER_URL}/signup`, user)
    //   .then((response) => {
    //     console.log(response);
    //     console.log('회원가입 성공');
    //     commit('LOGIN', response.data)
    //     axios.defaults.headers.common[
    //       "auth-token"
    //     ] = `${response.data["auth-token"]}`;
    //     router.push({ name: 'FavoriteCategory' });
    //   })
    //   .catch((err) => {
    //     alert("회원가입에 실패했습니다.")
    //     console.log(err)
    //   })
    // },
  }
}

export default ChallengeStore;