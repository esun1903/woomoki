<template>
  <v-container class="container-size">

    <v-row class="d-flex justify-center mb-7">
      <h2 class="message">프로필 변경을 위해 비밀번호를 한번더 입력해주세요!</h2>
    </v-row>
    
    <v-row>
      <v-text-field
        color="#AED864"
        v-model="originPassword"
        label="비밀번호"
        :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
        @click:append="showPass = !showPass"
        @keydown.enter="comparePassword"
        :type="showPass ? 'text' : 'password'"
        outlined
      ></v-text-field>
      
    </v-row>

    <v-row class="d-flex justify-end">
      <v-btn
        color="#AED864"
        @click="comparePassword" 
        :disabled="this.originPassword.length < 4"
        class="confirm-btn-right">
        <router-link to="/userPageEdit" class="confirm-btn-textcolor">확인</router-link>
      </v-btn>
    </v-row>

  </v-container>
</template>

<script>
import axios from "axios"

export default {
  name: "CompareOriginPassword",
  data: function () {
    return {
      originPassword: "",
      savedPassword: "",
      isSubmit: false,
      showPass: false,
    }
  },
  methods:  {
    // db에서 로그인 정보와 일치하는 패스워드 불러오기
    getPassword: function () {
      const myId = this.$store.state.UserStore.user.user_id
      axios.get(`http://i4a303.p.ssafy.io/api/userPage/Id/${myId}`)
        .then((res) => {
          // console.log(res.data)
          this.savedPassword = res.data.password
        })
        .catch((err) => {
          console.log(err)
        })
    },
    comparePassword: function () {
      if (this.originPassword === this.savedPassword) {
        console.log("패스워드 일치!")
        this.$router.push({ name: "UserPageEdit" })
      } else {
        alert("패스워드 불일치!")
      }
      console.log(this.originPassword, this.savedPassword)
    },
    // 입력된 패스워드 정규식 테스트
    // formPassword: function (password) {
    //   const re = /$/
    //   return re.test(password)
    // },
    validatePassword: function (password) {
      if (password.length >= 0) {
        console.log("true")
        this.isSubmit = true;
      } else {
        console.log("false")
        this.isSubmit = false;
      }
      return this.isSubmit;
    }
  },
  created() {
    this.getPassword();
  },
}
</script>

<style scoped>

a { text-decoration:none } 

.container-size {
  width: 35%;
  margin-top: 16%;
  margin-bottom: 15%;
}

.confirm-btn-textcolor {
  color: white;
}

.message {
  font-size: 22px;
}

</style>
