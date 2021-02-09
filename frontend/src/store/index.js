import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import UserStore from "@/store/modules/UserStore.js";
import CertStore from "@/store/modules/CertStore.js";
import ChallengeStore from "@/store/modules/CertStore.js";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    UserStore: UserStore,
    CertStore : CertStore,
    ChallengeStore: ChallengeStore,
  },
  plugins: [
    createPersistedState({
      // 여기다 놓은 모듈들의 store값들은 localstorage에 저장됨 == 새로고침해도 날라가지 않음
      paths: ['UserStore'],
    })
  ]
})


