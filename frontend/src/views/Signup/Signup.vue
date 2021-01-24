<template>
  <div>
    <section class="section-container">
      <v-row class="signin">
        <v-col cols="8" class="left">
          <h1>GRITREE, 당신의 새로운 원동력</h1>
        </v-col>
        <v-col cols="4" class="right">
          <h2>로그인</h2>
          <h4>이메일 로그인하기</h4>
          <validation-observer ref="observer">
            <v-form @submit.prevent="submit">
              <validation-provider
                v-slot="{ errors }"
                name="닉네임"
                rules="required"
              >
                <v-text-field
                  v-model="nickName"
                  :error-messages="errors"
                  label="사용할 닉네임을 입력해주세요"
                  required
                  outlined
                  dark
                  filled
                  dense
                ></v-text-field>
              </validation-provider>
              <validation-provider
                v-slot="{ errors }"
                name="이메일"
                rules="required|email"
              >
                <v-text-field
                  v-model="email"
                  :error-messages="errors"
                  label="이메일을 입력해주세요"
                  required
                  outlined
                  dark
                  filled
                  dense
                ></v-text-field>
              </validation-provider>
              <validation-observer>
                <validation-provider
                  v-slot="{ errors }"
                  name="비밀번호"
                  rules="required|password"
                >
                  <v-text-field
                    v-model="password"
                    :error-messages="errors"
                    label="비밀번호를 입력해주세요"
                    :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
                    @click:append="showPass = !showPass"
                    required
                    outlined
                    dense
                    dark
                    filled
                    :type="showPass ? 'text' : 'password'"
                  ></v-text-field>
                </validation-provider>
                <validation-provider
                  v-slot="{ errors }"
                  name="비밀번호 한 번 더"
                  rules="required|passwordConfirm:@비밀번호"
                  vid="password"
                >
                  <v-text-field
                    v-model="passwordConfirmation"
                    :error-messages="errors"
                    label="비밀번호 재확인 부탁드려요"
                    :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
                    @click:append="showPass = !showPass"
                    required
                    outlined
                    dense
                    dark
                    filled
                    :type="showPass ? 'text' : 'password'"
                  ></v-text-field>
                </validation-provider>
              </validation-observer>
              <div class="text-center">
                <v-btn class="signup-btn" type="submit" rounded color="white">
                  회원가입
                </v-btn>
              </div>

              <v-col class="py-2">
                <div id="join-find">
                  <ul>
                    <li><a href="">아이디 찾기</a></li>
                    <li><a href="">비밀번호 재설정</a></li>
                    <li><a href="#">회원가입</a></li>
                  </ul>
                </div>
              </v-col>
            </v-form>
          </validation-observer>
          <v-divider></v-divider>
          <p class="login-box-hd">또는 다른 서비스 계정으로 로그인</p>
          <span class="or-bar or-bar-right"></span>
          <v-col class="py-2">
            <div id="socialBtn">
              <Kakao />
              <Naver />
              <Google/>
            </div>
          </v-col>
        </v-col>
      </v-row>
    </section>
  </div>
</template>

<script>
import Kakao from "@/components/BaseSocial/Kakao.vue";
import Naver from "@/components/BaseSocial/Naver.vue";
import Google from "@/components/BaseSocial/Google.vue";
import { required, email } from "vee-validate/dist/rules";
import {
  extend,
  ValidationProvider,
  setInteractionMode,
  ValidationObserver
} from "vee-validate";


setInteractionMode("eager");

extend("required", {
  ...required,
  message: "{_field_} ㅠㅠ"
});

extend("email", {
  ...email,
  message: "이메일 형식에 맞추어 입력해주세요."
});

extend( "password", {
  message: "문자, 숫자, 특수 문자 포함 8자리 이상을 입력해주세요",
  validate: value => {
    return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value)
  }
});

extend("passwordConfirm", {
  params: ['target'],
  validate(value, { target }) {
    return value === target;
  },
  message: '패스워드가 일치하지 않습니다.'
});


export default {
  name: "Signup",
  components: {
    ValidationProvider,
    ValidationObserver,
    Kakao,
    Naver,
    Google
  },
  data: function() {
    return {
      nickName: "",
      email: "",
      password: "",
      passwordConfirmation: "",
      showPass: false,

      isSubmit: false,
    }
  },
  computed: {
    params: function () {
      return {
        email: this.email,
        password: this.password
      };
    },  
  },
  methods: {

    async submit() {
      const valid = await this.$refs.observer.validate();
      if (valid) {
        this.isSubmit = true;
        this.login(this.params); 
      }
      else this.isSubmit = false;
    },
    clear() {
      this.email = "";
      this.password = null;
      this.$refs.observer.reset();
    },

  }
};
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
    color: #ffffff;
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

    .signin {
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
      }
    }
  }
</style>
