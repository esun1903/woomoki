<template>
  <div class="all">
    <section class="section-container">
      <v-row class="login">
        <v-col cols="8" class="left">
          <h1>로고로고로고로고로고로고로고</h1>
        </v-col>
        <v-col cols="4" class="right">
          <h2>로그인</h2>
          <h4>이메일 로그인하기</h4>
          <!-- <validation-observer ref="observer"> -->
          <validation-observer v-slot="{ invalid }" ref="observer">
            <v-form @submit.prevent="submit">
              <validation-provider v-slot="{ errors }" name="email" rules="required|email">
                <v-text-field v-model="user.email" :error-messages="errors" label="Email" required outlined dark filled
                  dense></v-text-field>
              </validation-provider>
              <validation-provider v-slot="{ errors }" name="password" rules="required|password">
                <v-text-field v-model="user.password" :error-messages="errors" label="Password"
                  :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'" @click:append="showPass = !showPass" required
                  outlined dense dark filled :type="showPass ? 'text' : 'password'"></v-text-field>
              </validation-provider>

              <div class="text-center">
                <v-btn class="login-btn" type="submit" rounded color="white" :disabled="invalid">
                  로그인
                </v-btn>
              </div>

              <v-col class="py-2">
                <div id="join-find">
                  <ul>
                    <li>
                      <router-link :to="'/login/findId'">아이디 찾기</router-link>
                    </li>
                    <li>
                      <router-link :to="'/login/findPassword'">비밀번호 찾기</router-link>
                    </li>
                    <li>
                      <router-link :to="'signup'">회원가입</router-link>
                    </li>
                  </ul>
                </div>
              </v-col>
            </v-form>
          </validation-observer>
          <p class="login-box-hd">또는 다른 서비스 계정으로 로그인</p>
          <span class="or-bar or-bar-right"></span>
          <v-col class="py-2">
            <!-- <v-btn-toggle class="socialBtnToggle"> -->
            <div id="socialBtn">
              <Kakao />
              <Naver />
              <Google />
            </div>
            <!-- </v-btn-toggle> -->
          </v-col>
        </v-col>
      </v-row>
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
  /* ./assets/styles.scss */

  // .socialBtnToggle {
  //         background-color: rgba(255,255,255,0) !important;
  //     }


  #join-find {
    display: flex;
    justify-content: space-around;
  }

  #socialBtn {
    display: flex;
    justify-content: space-around;
    margin-right: 50px;
    margin-left: 50px;
    padding-left: 20px;
    padding-right: 20px;

  }

  a {
    text-decoration: none;
    color: #ffffff !important;
  }

  ul {
    list-style: none;
    font-size: 13px;
  }

  li {
    float: left;
    font-size: 13px;
    margin-right: 20px;

  }

  .login-box-hd {
    text-align: center;
    font-weight: 400;
    font-size: 11px;
    line-height: 28px;
    color: #ffffff;
  }

  .section-container {
    //   padding: 20px;
    //   margin: 20px;
    background: #fff;
    width: 100%;
    box-shadow: 0 0 1px 1px rgba($color: #000000, $alpha: 0.1);
    box-sizing: border-box;

    .login {
      padding: 0;
      margin: 0 auto;
      min-height: 750px;
      box-shadow: 0 0 1px 1px rgba($color: #000000, $alpha: 0.1);

      .left {
        //   padding: 30px;
        justify-content: center;
        align-items: center;
        box-sizing: border-box;
        display: flex;
        color: #30ac7c;
        background-color: #f9f9f9;
      }

      .right {
        //   padding: 30px;
        display: flex;
        flex-direction: column;
        align-items: center;
        box-sizing: border-box;
        background: #30ac7c;
        color: #fff;
        padding-left: 50px;
        padding-right: 50px;

        h2,
        h4 {
          text-align: center;
          margin: 30px 0;
        }

        .login-btn {
          width: 100%;
          color: #30ac7c;
        }

        .disabled,
        .disabled:hover {
          background-color: rgb(136, 154, 152, 0.25);
          color: #f8f8f8;
          cursor: inherit;
        }

        .all {
          .application--wrap {
            min-height: 80vh !important;
          }
        }

      }
    }
  }
</style>