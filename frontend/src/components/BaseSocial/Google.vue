<template>
      <div>
      <v-btn
              @click="google"
              color="transparent"
              depressed
              icon
            >
              <v-img :src="require('@/assets/images/google.png')"
               max-height="40"
  max-width="40" />
            </v-btn>

     
    </div>
</template>

<script>
import firebase from 'firebase'
export default {
  name: 'Google',
  components: {  },
  directives: {  },
  data() {
    return {
      
    };
  },
  mounted() {
    
  },
   methods:{
      google() {
      const _this = this;

      var provider = new firebase.auth.GoogleAuthProvider();

      // 추가적인 권한이 있을 경우에는 아래와 같이 추가합니다.
      // provider.addScope('https://www.googleapis.com/auth/contacts.readonly');

      // 로그인시 보여줄 언어를 지정합니다.
      // firebase.auth().languageCode = 'pt';
      // To apply the default browser preference instead of explicitly setting it.
      // firebase.auth().useDeviceLanguage();

      // 로그인 아이디의 기본값을 지정합니다. 지정하지 않아도 됩니다.
      provider.setCustomParameters({
        'login_hint': 'user@example.com'
      });

      // 로그인 팝업창을 띄웁니다.
      firebase.auth().signInWithPopup(provider).then(function(result) {
        // This gives you a Google Access Token. You can use it to access the Google API.
        var token = result.credential.accessToken;
        console.log(token);
        // The signed-in user info.
        var user = result.user;
        // ...
        console.log(user);
          _this.$router.push("/");
      }).catch(function(error) {
        // Handle Errors here.
        var errorCode = error.code;
        console.log(errorCode);
        var errorMessage = error.message;
        console.log(errorMessage);
        // The email of the user's account used.
        var email = error.email;
        console.log(email);
        // The firebase.auth.AuthCredential type that was used.
        var credential = error.credential;
        console.log(credential);
        // ...
      });
    },
   },
};
</script>

<style lang="scss" scoped>

</style>