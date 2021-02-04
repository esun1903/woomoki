import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLogin: false,
  },
  getters: {

  },
  mutations: {
    LOGIN_SUCCESS (state) {
      state.isLogin = true
    },
    LOGOUT_SUCCESS (state) {
      state.isLogin = false
      localStorage.removeItem('jwt')
      location.reload()
    }
  },
  actions: {
    loginSuccess({commit}) {
      commit('LOGIN_SUCCESS')
    },
    logoutSuccess({commit}) {
      commit('LOGOUT_SUCCESS')
    },
  },
  modules: {}
});
