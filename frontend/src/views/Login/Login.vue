<template>
  <div class="d-flex justify-center bgimg">
    
    <section class="size">
        <v-container class="title-box">
          <v-row class="d-flex justify-center">
            <h2 class="login-text">로그인</h2>
          </v-row>
          <v-row>
            <v-col class="d-flex justify-center">
              <Kakao />
            </v-col>
            <v-col class="d-flex justify-center">
              <Naver />
            </v-col>
            <v-col class="d-flex justify-center">
              <Google />
            </v-col>
          </v-row>
            <!-- <v-row class="d-flex justify-center">
              <p class="login-box-hd">또는 다른 서비스 계정으로 로그인</p>
            </v-row> -->
        </v-container>
        
        <v-container>
          <v-col>
            <v-row class="d-flex justify-center message">
              <h4>이메일 로그인하기</h4>
            </v-row>

            <validation-observer v-slot="{ invalid }" ref="observer">
              <v-form @submit.prevent="submit">
                <v-row class="d-flex justify-center">
                  <validation-provider v-slot="{ errors }" name="email" rules="required|email">
                    <v-text-field class="input-size" color="#AED864" v-model="user.email" :error-messages="errors" label="Email" required outlined filled
                      dense></v-text-field>
                  </validation-provider>
                </v-row>
                <v-row class="d-flex justify-center">
                  <validation-provider v-slot="{ errors }" name="password" rules="required|password">
                    <v-text-field class="input-size" v-model="user.password" :error-messages="errors" label="Password"
                      :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'" @click:append="showPass = !showPass" required
                      outlined color="#AED864" dense filled :type="showPass ? 'text' : 'password'"></v-text-field>
                  </validation-provider>
                </v-row>
                <v-row class="d-flex justify-center">
                  
                  <div class="text-center">
                    <v-btn class="loginbtn" plain :ripple="false" type="submit" rounded color="#AED864" :disabled="invalid">
                      로그인
                    </v-btn>
                  </div>
                </v-row>
                <v-row> 
                  <v-col class="d-flex justify-center">
                    <router-link class="link" :to="'/login/findId'">아이디 찾기</router-link>
                  </v-col>

                  <v-col class="d-flex justify-center">
                    <router-link class="link" :to="'/login/findPassword'">비밀번호 찾기</router-link>
                  </v-col>

                  <v-col class="d-flex justify-center">
                    <router-link class="link" :to="'signup'">회원가입</router-link>
                  </v-col>
                </v-row>
       
              </v-form>
            </validation-observer>
            

            <span class="or-bar or-bar-right"></span>
            
          </v-col>
        </v-container>
    </section>
  </div>
  
</template>


<script>
  import Kakao from "@/components/BaseSocial/Kakao.vue"
  import Naver from "@/components/BaseSocial/Naver.vue"
  import Google from "@/components/BaseSocial/Google.vue"
  import {
    required,
    email
  } from 'vee-validate/dist/rules'
  import {
    extend,
    ValidationProvider,
    setInteractionMode,
    ValidationObserver
  } from 'vee-validate'

  setInteractionMode('eager')

  extend('required', {
    ...required,
    message: '{_field_} can not be empty'
  })

  extend('email', {
    ...email,
    message: '이메일 형식이 올바르지 않습니다.'
  })

  extend("password", {
    message: "문자, 숫자, 특수문자 8자리",
    validate: value => {
      return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value)
    }
  });

  export default {
    name: 'Login',
    components: {
      ValidationProvider,
      ValidationObserver,
      Kakao,
      Naver,
      Google,
    },
    data: function () {
      return {
        user: {
        email: "",
        password: "",
      },
        showPass: false,
      };
    },
  computed: {

    },
    methods: {
      async submit() {
        const valid = await this.$refs.observer.validate()
        if (valid) {
          // console.log(this.user.email);
          // console.log(this.user.password);
          this.$store.dispatch("UserStore/login", this.user);
        }else{
          alert("이메일, 비밀번호를 확인해주세요")
        }
      },
    }
  }
</script>

<style lang="scss" scoped>

a { text-decoration: none; }

body, html { 
  margin: 0;
  padding: 0;
  height: 100%;
}

.bgimg {
    border: 0;
    padding: 0;
    height: 80vh;
    background-image: url("https://demos.creative-tim.com/vue-material-kit/img/profile_city.435c148e.jpg");
    min-height: 100%;
    background-position: center;
    background-size: cover;
}

.size {
  margin-top: 6%;
  width: 23%;
  height: 70%;
  background-color: whitesmoke;
  border-radius: 10px;
  box-shadow: 1px 1px 7px rgb(172, 172, 172);
}

.input-size{
  width: 20vw;
}

.title-box {
  width: 90%;
  height: 30%;
  background: linear-gradient(70deg, #7CB342, #AED864,);
  // background-color: #AED864;
  box-shadow: 5px 5px 10px grey;
  position: relative;
  top: -5vh;
  border-radius: 10px;
  margin-bottom: -2.5vh;
}

.login-text {
  font-size: 1.6vw;
  color: white;
  margin-top: 1.5vh;
  margin-bottom: 1vh;
}

.loginbtn {
  font-size: 1.5vw;
  margin-bottom: 2vh;
  color: #AED864;
}

.link {
  color: black;
}

.message {
  margin-bottom: 5.5vh;
}

  /* ./assets/styles.scss */

  // .socialBtnToggle {
  //         background-color: rgba(255,255,255,0) !important;
  //     }


  // #join-find {
  //   display: flex;
  //   justify-content: space-around;
  // }

  // #socialBtn {
  //   display: flex;
  //   justify-content: space-around;
  //   margin-right: 50px;
  //   margin-left: 50px;
  //   padding-left: 20px;
  //   padding-right: 20px;

  // }

  // a {
  //   text-decoration: none;
  //   color: black !important;
  // }

  // ul {
  //   list-style: none;
  //   font-size: 13px;
  // }

  // li {
  //   float: left;
  //   font-size: 13px;
  //   margin-right: 20px;

  // }

  // .login-box-hd {
  //   text-align: center;
  //   font-weight: 400;
  //   font-size: 11px;
  //   line-height: 28px;
  //   color: black;
  // }

  // .section-container {
  //   //   padding: 20px;
  //   //   margin: 20px;
  //   background: #fff;
  //   width: 100%;
  //   // box-shadow: 0 0 1px 1px rgba($color: #000000, $alpha: 0.1);
  //   box-sizing: border-box;

  //   .login {
  //     padding: 0;
  //     margin: 0 auto;
  //     min-height: 91vh;
  //     // box-shadow: 0 0 1px 1px rgba($color: #000000, $alpha: 0.1);

  //     .left {
  //       //   padding: 30px;
  //       justify-content: center;
  //       align-items: center;
  //       // box-sizing: border-box;
  //       display: flex;
  //       color: #30ac7c;
  //       background-color: #f9f9f9;
  //       // background-size: cover;
  //       background-size: cover;
  //       // background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2z9HX0e8x5yymedl_0bacIHJvoS1WOUafww&usqp=CAU");
  //       // background-image: url("https://demos.creative-tim.com/vue-material-kit/img/vue-mk-header.98fb6ce8.jpg");
  //       background-image: url("https://demos.creative-tim.com/vue-material-kit/img/profile_city.435c148e.jpg");
  //       // background-image: url("./login.jpg");
        
  //     }

  //     .right {
  //       //   padding: 30px;
  //       display: flex;
  //       flex-direction: column;
  //       align-items: center;
  //       box-sizing: border-box;
  //       background: #F3ECE2;
  //       color: black;
  //       padding-left: 50px;
  //       padding-right: 50px;
  //       padding-top: 10vh;

  //       h2,
  //       h4 {
  //         text-align: center;
  //         margin: 30px 0;
  //       }

  //       .login-btn {
  //         width: 100%;
  //         color: #30ac7c;
  //       }

  //       .disabled,
  //       .disabled:hover {
  //         background-color: rgb(136, 154, 152, 0.25);
  //         color: #f8f8f8;
  //         cursor: inherit;
  //       }

  //       .all {
  //         .application--wrap {
  //           min-height: 80vh !important;
  //         }
  //       }

  //     }
  //   }
  // }
</style>