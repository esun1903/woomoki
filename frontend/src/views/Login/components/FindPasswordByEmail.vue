<template>
  <div>
    <validation-observer v-slot="{ invalid }" ref="observer">
      <v-form @submit.prevent="submit">
        <validation-provider v-slot="{ errors }" name="이메일" rules="required|email">
          <v-text-field class="input-size" color="black" v-model="email" :error-messages="errors" label="이메일" required
            outlined dense></v-text-field>
        </validation-provider>
        <div class="text-center">
          <v-btn class="send-email" type="submit" rounded color="white" :disabled="invalid">
            임시 비밀번호 메일 발송
          </v-btn>
        </div>
      </v-form>
    </validation-observer>

  </div>
</template>

<script>
  import axios from "axios";
  import emailjs from 'emailjs-com';
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
    message: '{_field_}은 필수 항목입니다'
  })

  extend('email', {
    ...email,
    message: '이메일 형식이 올바르지 않습니다.'
  })


  export default {
    name: 'FindPasswordByEmail',
    components: {
      ValidationProvider,
      ValidationObserver,

    },
    data: () => ({
      email: ''
    }),
    computed: {
      params() {
        return {
          email: this.email,
          temp_pw:"",
        }
      }
    },
    created() {
      // this.makeRandomPW();
    },
    methods: {

      // 랜덤비번 생성(특수문자 전범위 ver)
      // makeRandomPW() {
      //   var arr =
      //     "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,!,@,#,$,%,^,&,*,(,),-,+,|,_,=,\,[,],{,},<,>,?,/,."
      //     .split(",");

      //   var temp_pw = this.createCode(arr, 8);
      //   console.log(temp_pw);
      // },
      //  createCode(objArr) {
      //   var arr = objArr;
      //   var randomStr = "";

      //   for (var j = 0; j < 8; j++) {
      //     randomStr += arr[Math.floor(Math.random() * arr.length)];
      //   }

      //   return randomStr
      // },
      async submit(temp_pw) {
        const valid = await this.$refs.observer.validate();
        if (valid) {
          this.temp_pw = Math.random().toString(36).substr(2, 11);
          this.temp_pw = this.temp_pw + "!";
          emailjs.send('service_y8xub6u', 'template_fyxfdgh', {
            from_name: "우목이",
            to_name: "김싸피",
            user_email: this.email,
            admin_email: "admin@a303.com",
            temp_pw: this.temp_pw,
          }, 'user_jsT9VLscfRQIahhEQbuiv').then((
            result) => {
            const cngPasswordInfo = {
              email: this.email,
              password: this.temp_pw,
            }
            console.log(cngPasswordInfo);
            axios.post("http://localhost:8080/userPage/changePassword", cngPasswordInfo)
              .then((response) => {
                console.log(response.data);
              })
            console.log('SUCCESS!', result.status, result.text);
          }, (error) => {
            console.log('FAILED...', error);
          });
          alert('임시 비밀번호를 발송하였습니다!\n이메일을 확인해주세요');
          location.href = '/login';
        }
      },
    }
  }
</script>

<style lang="scss" scoped>
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

    .find-password {
      padding: 0;
      margin: 0 auto;
      min-height: 690px;
      box-shadow: 0 0 1px 1px rgba($color: #000000, $alpha: 0.1);

      .left {
        //   padding: 30px;
        justify-content: center;
        align-items: center;
        box-sizing: border-box;
        display: flex;
        color: #be5656;
        background-color: #f9f9f9;
      }

      .right {
        //   padding: 30px;
        display: flex;
        flex-direction: column;
        align-items: center;
        box-sizing: border-box;
        background: #be5656;
        color: #fff;
        padding-left: 50px;
        padding-right: 50px;

        h2,
        h4 {
          text-align: center;
          margin: 30px 0;
        }

        .send-email {
          width: 100%;
          color: black;
        }

        .disabled,
        .disabled:hover {
          background-color: rgb(136, 154, 152, 0.25);
          color: #be5656;
          cursor: inherit;
        }

        .back-btn {
          width: 100%;
          color: #be5656;
          margin-top: 20px;
          text-decoration: none;
        }
      }
    }
  }
</style>