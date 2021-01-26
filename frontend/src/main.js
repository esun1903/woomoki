import Vue from "vue";
import Vuex from "vuex";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import firebase from 'firebase'


Vue.config.productionTip = false;

Vue.use(Vuex);

new Vue({
  router,
  store,
  vuetify,
  axios,
  firebase,
  render: h => h(App)
}).$mount("#app");

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
var firebaseConfig = {
  apiKey: "AIzaSyAIMaUEUqwZ7MMV-M9Jk0WlR_bEQvR0PrA",
  authDomain: "st-project-302507.firebaseapp.com",
  projectId: "st-project-302507",
  storageBucket: "st-project-302507.appspot.com",
  messagingSenderId: "885895929921",
  appId: "1:885895929921:web:f27a75b05ea15eee5e26c0",
  measurementId: "G-JN404KP8WX"
};

// Initialize Firebase
firebase.analytics();
firebase.initializeApp(firebaseConfig);


if(window.location.hostname === 'localhost') {
  firebase.firestore().settings({ host: 'localhost:8080', ssl: false });
  firebase.functions().useFunctionsEmulator('http://localhost:5001');
}
