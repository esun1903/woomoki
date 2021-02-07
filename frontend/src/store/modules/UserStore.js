import axios from "axios";
import router from "@/router";

const UserStore = {
  namespaced: true,
  state: {
    user: {
      user_id: "",
      nickname: "",
      email: "",
      phone: "",
      password: "",    
    },
    isLogin: false,
  },
  getters: {
    
  }, 
  mutations: {
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
    signUp({commit}, payload) {
      commit('SIGN_UP', payload)
    },
    logOut({commit}) {
      commit('LOG_OUT')
    }
  }
}

export default UserStore;