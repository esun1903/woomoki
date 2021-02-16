<template>
  <!-- <div> -->
    <!-- <section class="section-container">
      <v-row class="signup">
        <v-col cols="8" class="left">
          <h1>우목이, 당신의 새로운 원동력</h1>
        </v-col>
        <v-col cols="4" class="right">
          <h2>회원가입</h2>
          <h4>이메일 회원가입하기</h4> -->
          <!-- <validation-observer v-slot="{ invalid }" ref="observer">
            <v-form @submit.prevent="signUp">
              <validation-provider
                v-slot="{ errors }"
                name="닉네임"
                rules="required"
              >
                <v-text-field
                  v-model="credentials.nickname"
                  :error-messages="errors"
                  label="닉네임"
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
                  v-model="credentials.email"
                  :error-messages="errors"
                  label="이메일"
                  required
                  outlined
                  dark
                  filled
                  dense
                ></v-text-field>
              </validation-provider>
              <validation-provider
                v-slot="{ errors }"
                name="핸드폰 번호"
                :rules="{
                  required: true,
                  digits: 11,
                  regex: /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/
                }"
              >
                <v-text-field
                  v-model="credentials.phone"
                  :counter="11"
                  :error-messages="errors"
                  label="핸드폰 번호(숫자만)"
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
                    v-model="credentials.password"
                    :error-messages="errors"
                    label="비밀번호"
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
                    label="비밀번호 재확인"
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
                <v-btn class="signup-btn" type="submit" rounded color="white" :disabled="invalid" 
                >
                  회원가입
                </v-btn>
              </div>
            </v-form>
          </validation-observer> -->
          <!-- <v-divider></v-divider>
          <p class="signup-box-hd">또는 다른 서비스 계정으로 회원가입</p>
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
  </div> -->

    <div class="d-flex justify-center bgimg">
    
    <section class="size">
        <v-container class="title-box">
          <v-row class="d-flex justify-center">
            <h2 class="signup-text">회원가입</h2>
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
              <h4>이메일 회원가입하기</h4>
            </v-row>

            <validation-observer v-slot="{ invalid }" ref="observer">
            <v-form @submit.prevent="signUp">
              <validation-provider
                v-slot="{ errors }"
                name="닉네임"
                rules="required"
              >
                <v-text-field
                  v-model="credentials.nickname"
                  :error-messages="errors"
                  label="닉네임"
                  required
                  outlined
                  color="#AED864"
                  dense
                ></v-text-field>
              </validation-provider>
              <validation-provider
                v-slot="{ errors }"
                name="이메일"
                rules="required|email"
              >
                <v-text-field
                  v-model="credentials.email"
                  :error-messages="errors"
                  label="이메일"
                  required
                  outlined
                  color="#AED864"
                  dense
                ></v-text-field>
              </validation-provider>
              <validation-provider
                v-slot="{ errors }"
                name="핸드폰 번호"
                :rules="{
                  required: true,
                  digits: 11,
                  regex: /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/
                }"
              >
                <v-text-field
                  v-model="credentials.phone"
                  :counter="11"
                  :error-messages="errors"
                  label="핸드폰 번호(숫자만)"
                  required
                  outlined
                  color="#AED864"
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
                    v-model="credentials.password"
                    :error-messages="errors"
                    label="비밀번호"
                    :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
                    @click:append="showPass = !showPass"
                    required
                    outlined
                    dense
                    color="#AED864"
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
                    label="비밀번호 재확인"
                    :append-icon="showPass ? 'mdi-eye' : 'mdi-eye-off'"
                    @click:append="showPass = !showPass"
                    required
                    outlined
                    dense
                    color="#AED864"
                    :type="showPass ? 'text' : 'password'"
                  ></v-text-field>
                </validation-provider>
              </validation-observer>
              <div class="text-center">
                <v-btn class="signUpbtn" color="#AED864" :ripple="false" plain type="submit" rounded :disabled="invalid" 
                >
                  회원가입
                </v-btn>
              </div>
            </v-form>
          </validation-observer>
            

            <span class="or-bar or-bar-right"></span>
            
          </v-col>
        </v-container>
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
  message: "{_field_}는 필수 항목입니다"
});

extend("email", {
  ...email,
  message: "이메일 형식이 아닙니다"
});


extend( "password", {
  message: "문자, 숫자, 특수문자 8자리",
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
      credentials: {
        nickname: "",
        email: "",
        phone: "",
        password: "",
      },
      passwordConfirmation: "",
      showPass: false,
    }
  },
  computed: {
  },
  methods: {
    async signUp () {
      const valid = await this.$refs.observer.validate();
      if (valid) {
        this.$store.dispatch('UserStore/signUp', this.credentials)
      } else {
        alert("내용을 확인해주세요.")
      }
    }
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
    height: 100vh;
    background-image: url("https://demos.creative-tim.com/vue-material-kit/img/vue-mk-header.98fb6ce8.jpg");
    min-height: 100%;
    background-position: center;
    background-size: cover;
}

.size {
  margin-top: 6%;
  width: 23%;
  height: 80%;
  background-color: whitesmoke;
  border-radius: 10px;
  box-shadow: 0.5px 0.5px 5px rgb(172, 172, 172);
}

.input-size{
  width: 20vw;
}

.title-box {
  width: 90%;
  height: 20%;
  background: linear-gradient(70deg, #7CB342, #AED864,);
  // background-color: #AED864;
  box-shadow: 5px 5px 10px grey;
  position: relative;
  top: -5vh;
  border-radius: 10px;
  margin-bottom: -2vh;
}

.signup-text {
  font-size: 1.6vw;
  color: white;
  margin-top: 1.5vh;
  margin-bottom: 1vh;
}

.signUpbtn {
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


  // .v-text-field {
  //   width: 100%;
  // }
  // #socialBtn {
  //   display: flex;
  //   justify-content: space-around;
  //   margin-right: 50px;
  //   margin-left: 50px;
  //   padding-left: 20px;
  //   padding-right: 20px;

  // }
  // .signup-box-hd {
  //   margin-top: 20px;
  //   text-align: center;
  //   font-weight: 400;
  //   font-size: 11px;
  //   line-height: 28px;
  //   color: #ffffff;
  // }

  // .section-container {
  //   background: #fff;
  //   width: 100%;
  //   box-shadow: 0 0 1px 1px rgba($color: #000000, $alpha: 0.1);
  //   box-sizing: border-box;

  //   .signup {
  //     padding: 0;
  //     margin: 0 auto;
  //     min-height: 750px;
  //     box-shadow: 0 0 1px 1px rgba($color: #000000, $alpha: 0.1);

  //     .left {
  //       justify-content: center;
  //       align-items: center;
  //       box-sizing: border-box;
  //       display: flex;
  //       color: #30ac7c;
  //       background-color: #f9f9f9;
  //     }

  //     .right {
  //       padding: 30px;
  //       display: flex;
  //       flex-direction: column;
  //       align-items: center;
  //       box-sizing: border-box;
  //       background: #30ac7c;
  //       color: #fff;
  //       padding-left: 50px;
  //       padding-right: 50px;

  //       h2,
  //       h4 {
  //         text-align: center;
  //         margin: 20px 0;
  //       }

  //       .signup-btn {
  //         width: 100%;
  //         color: #30ac7c;  
  //         margin-top: 10px; 
  //       }
  //     }
  //   }
  // }
</style>