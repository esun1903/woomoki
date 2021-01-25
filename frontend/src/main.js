import Vue from "vue"
import Vuex from "vuex"
import App from "./App.vue"
import router from "./router"
import store from "./store"
import vuetify from "./plugins/vuetify"
import axios from 'axios'
import firebase from 'firebase'

Vue.config.productionTip = false;

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
  vuetify,
  axios,
  firebase,
  render: h => h(App)
}).$mount("#app");
