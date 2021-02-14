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
      // compareId: "",
      nickname: "",
      email: "",   
    },
    isLogin: false,
  },
  mutations: {
    LOGIN(state, payload){
      console.log("payload[auth-token]: "+payload["auth-token"]);
      state.user.accessToken = payload["auth-token"];
      state.user.user_id = payload["user-id"];
      state.user.nickname = payload["user-nickname"];
      state.user.email = payload["user-email"];
      state.isLogin = true;
 
    },
    LOGOUT(state) {
      state.user.accessToken = null;
      state.user.user_id = "";
      state.user.compareId = "";
      state.user.nickname = "";
      state.user.email = "";  
      state.isLogin = false;
    },
    // COMPARE(state, compareId) {
    //   state.user.compareId = compareId
    // }
  },
  getters: {

    getCheckLogin(state){
      return state.isLogin;
    }
  }, 
  actions: {
      login(context, user){
      console.log(SERVER_URL);
      return axios
        .post(`${SERVER_URL}/login`, user)
        .then((response) => {
          context.commit("LOGIN", response.data);
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["auth-token"]}`;
          router.push('/');
          // router.push({name: 'Main', params: { userInfo : response.data }});
        }).catch((error) => {
          console.log(error);
          alert("이메일 또는 비밀번호를 확인해주세요!");
        })
    },
    signUp({commit}, user) {
      return axios
      .post(`${SERVER_URL}/signup`, user)
      .then((response) => {
        console.log(response);
        console.log('회원가입 성공');
        commit('LOGIN', response.data)
        axios.defaults.headers.common[
          "auth-token"
        ] = `${response.data["auth-token"]}`;
        // localStorage.setItem('jwt', res.data.token);
        // localStorage.setItem('user_id',res.data.user_id);
        // localStorage.setItem('isLogin', true);
        router.push({ name: 'FavoriteCategory' });
      })
      .catch((err) => {
        alert("회원가입에 실패했습니다.")
        console.log(err)
      })
      
    },
    logOut({commit}) {
      axios.defaults.headers.common[
        "auth-token"
      ] = undefined;
      commit('LOGOUT')
    },
    // compareId(context, compareId) {
    //   context.commit("COMPARE", compareId);
    // }
  }
}

export default UserStore;