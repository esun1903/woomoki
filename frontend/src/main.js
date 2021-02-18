import Vue from "vue";
import Vuex from "vuex";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import VueApexCharts from 'vue-apexcharts';
import "./common/filters";
import InfiniteLoading from "vue-infinite-loading";
import { UserStore } from "./store/modules/UserStore"
import VueSplide from '@splidejs/vue-splide';
import VueToast from 'vue-toast-notification';
import 'vue-toast-notification/dist/theme-sugar.css';

Vue.use(VueToast);
Vue.use( VueSplide );

Vue.config.productionTip = false;


// apexcharts 사용
Vue.use(VueApexCharts)
Vue.component('apexchart', VueApexCharts)

// infinit-loading 사용
Vue.use(InfiniteLoading)

// 쓰면 Object 키값에 .으로 접근이 안되고 ['key'] 이렇게 써야함..
// Vue.prototype.$axios = axios

Vue.use(Vuex);


new Vue({
  router,
  store,
  UserStore,
  vuetify,
  axios,
  render: h => h(App)
}).$mount("#app");
