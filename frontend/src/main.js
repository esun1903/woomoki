import Vue from "vue";
import Vuex from "vuex";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import firebase from 'firebase';
import VueApexCharts from 'vue-apexcharts';
import "./common/filters";
import InfiniteLoading from "vue-infinite-loading";
import { UserStore } from "./store/modules/UserStore"
import VueSplide from '@splidejs/vue-splide';

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

window.Kakao.init("dc7cf69baf1f6d8d13dbb622f40e287f");

var firebaseConfig = {
  apiKey: "AIzaSyClsx40HL7u95k61ZxSaufmPmsi-ffpsz4",
  authDomain: "ssafy-project-a303.firebaseapp.com",
  projectId: "ssafy-project-a303",
  storageBucket: "ssafy-project-a303.appspot.com",
  messagingSenderId: "1022778219692",
  appId: "1:1022778219692:web:83ed67c1451fd548219309",
  measurementId: "G-QXQV6E2PQ5"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();

if(window.location.hostname === 'localhost') {
  firebase.firestore().settings({ host: 'localhost:8080', ssl: false });
  firebase.functions().useFunctionsEmulator('http://localhost:5001');
}

new Vue({
  router,
  store,
  UserStore,
  vuetify,
  axios,
  firebase,
  render: h => h(App)
}).$mount("#app");

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
// var firebaseConfig = {
//   apiKey: "AIzaSyAIMaUEUqwZ7MMV-M9Jk0WlR_bEQvR0PrA",
//   authDomain: "st-project-302507.firebaseapp.com",
//   projectId: "st-project-302507",
//   storageBucket: "st-project-302507.appspot.com",
//   messagingSenderId: "885895929921",
//   appId: "1:885895929921:web:f27a75b05ea15eee5e26c0",
//   measurementId: "G-JN404KP8WX"
// };

// // Initialize Firebase
// firebase.analytics();
// firebase.initializeApp(firebaseConfig);


// if(window.location.hostname === 'localhost') {
//   firebase.firestore().settings({ host: 'localhost:8080', ssl: false });
//   firebase.functions().useFunctionsEmulator('http://localhost:5001');
// }

