<template>
  <div>
    <v-btn @click="kakao" color="transparent" depressed icon>
      <v-icon color="white" size="2vw">fab fa-twitter</v-icon>
      <!-- <v-img :src="require('@/assets/images/kakao.png')" max-height="40" max-width="40" /> -->
    </v-btn>
  </div>
</template>

<script>
import firebase from 'firebase'
// import KakaoAuth from '@/shared/KakaoAuth'

export default {
  name: 'Kakao',
  data: () => ({
      email : "",
      isSignin: false,
  }),
  created () {
    var user = firebase.auth().currentUser;
    console.log('user: '+user);
    // firebase.auth().onAuthStateChanged(function(user) {
    //   if (user) {
    //     // User is signed in.
    //     console.log(user);
    //   } else {
    //     // No user is signed in.
    //   }
    // });
    if(user) {
      this.isSignin = true;
    }
  },
  methods: {
    signout() {
      const _this = this;
      firebase.auth().signOut().then(function() {
        // Sign-out successful.
        _this.isSignin = false;
        //_this.$router.push("/signin");
      }).catch(function(error) {
        // An error happened.
        console.log('error: '+ error);
      });
      // if (!window.Kakao.Auth.getAccessToken()) {
      //   console.log('Not logged in.');
      //   return;
      // }
      window.Kakao.Auth.logout(function() {
        console.log('token: ' + window.Kakao.Auth.getAccessToken());
      });
    },
   
    kakao() {
      
      const protocol = location.protocol;
      const hostName = location.hostname;
      const port = location.port;
      let url = protocol + '//' + hostName + (port ? ':' + port : '');
      // url += '/#/signupfinish';
      url += '/callback/kakaotalk';
      console.log('url: '+url);
      // const auth = new KakaoAuth();
      // auth.auth();
      window.Kakao.Auth.authorize({
        redirectUri: url
        // , state:""
        // , scope:""
        , throughTalk: true
      });
    },
  }
}
</script>