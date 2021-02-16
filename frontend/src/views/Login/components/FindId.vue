<template>
  <div>
    <section class="section-container">
      <v-row class="find-id">
        <v-col cols="8" class="left">
          <h1>오랜만에 오신건 아니죠? 우리 우목이 열심히 사용해주세요ㅎㅎ</h1>
        </v-col>
        <v-col cols="4" class="right">
          <h2>아이디를 잊으셨나요?</h2>
          <h4>등록된 핸드폰 번호로 아이디 찾기</h4>
          <div class="text-center">
            <div v-if="!show">
              <validation-observer v-slot="{ invalid }" ref="observer">
                <v-form @submit.prevent="submit">
                  <validation-provider v-slot="{ errors }" name="핸드폰 번호" :rules="{
                  required: true,
                  digits: 11,
                  regex: /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/}">
                    <v-text-field v-model="phone" :counter="11" :error-messages="errors" label="핸드폰 번호(숫자만)" required
                      outlined color="#be5656" dense></v-text-field>
                  </validation-provider>
                  <v-btn class="send-code-btn" type="submit" rounded color="white" :disabled="invalid">
                    입력
                  </v-btn>
                </v-form>
              </validation-observer>
            </div>
            <div class="code" v-else>
              <!-- <v-divider></v-divider> -->
              등록하신 아이디는 <br><b>{{ this.userInfo.email }}</b><br>입니다.
              <!-- <v-text-field label="인증 코드" outlined dark dense></v-text-field> -->
            </div>
            <router-link :to="'/login'">
              <BackBtn />
            </router-link>
          </div>
        </v-col>
      </v-row>
    </section>
  </div>
</template>

<script>

import axios from "axios"
  import {
    required,
    length
  } from 'vee-validate/dist/rules'
  import {
    extend,
    ValidationProvider,
    setInteractionMode,
    ValidationObserver
  } from 'vee-validate'

  import BackBtn from "@/views/Login/components/BackBtn.vue"

  setInteractionMode('eager')

  extend("required", {
    ...required,
    message: "{_field_}는 필수 항목입니다"
  });



  export default {
    name: 'FindId',
    components: {
      ValidationProvider,
      ValidationObserver,
      BackBtn,
    },
    data: () => ({
      phone: '',
      show: false,
      userInfo:"",
    }),
    computed: {
      params() {
        return {
          phone: this.phone,
        }
      }
    },
    methods: {

      // getPhoneMask(val) {
      //   let res = this.getMask(val)
      //   this.phone = res
      //   //서버 전송 값에는 '-' 를 제외하고 숫자만 저장
      //   this.model.phone = this.phone.replace(/[^0-9]/g, '')
      // },

      // getMask(phoneNumber) {
      //   if (!phoneNumber) return phoneNumber
      //   phoneNumber = phoneNumber.replace(/[^0-9]/g, '')

      //   let res = ''
      //   if (phoneNumber.length < 3) {
      //     res = phoneNumber
      //   } else {
      //     if (phoneNumber.substr(0, 2) == '02') {

      //       if (phoneNumber.length <= 5) { //02-123-5678
      //         res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3)
      //       } else if (phoneNumber.length > 5 && phoneNumber.length <= 9) { //02-123-5678
      //         res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3) + '-' + phoneNumber.substr(5)
      //       } else if (phoneNumber.length > 9) { //02-1234-5678
      //         res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 4) + '-' + phoneNumber.substr(6)
      //       }

      //     } else {
      //       if (phoneNumber.length < 8) {
      //         res = phoneNumber
      //       } else if (phoneNumber.length == 8) {
      //         res = phoneNumber.substr(0, 4) + '-' + phoneNumber.substr(4)
      //       } else if (phoneNumber.length == 9) {
      //         res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
      //       } else if (phoneNumber.length == 10) {
      //         res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
      //       } else if (phoneNumber.length > 10) { //010-1234-5678
      //         res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 4) + '-' + phoneNumber.substr(7)
      //       }
      //     }
      //   }

      //   return res
      // },

      async submit() {
        const valid = await this.$refs.observer.validate()
        if (valid) {
          // 핸드폰 번호값 보내주고 유저 이메일 얻어오기
          const phone = this.phone;
                axios.get(`http://localhost:8080/userInfo/${phone}`)
                    .then((response) => {
                        this.userInfo = response.data;
                    })
                    .catch((err) => {
                        console.log(err)
                    })
          console.log(this.phone);
          this.show = !this.show;
        }
      },
    }
  }
</script>

<style lang="scss" scoped>
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

  .section-container {
    //   padding: 20px;
    //   margin: 20px;
    background: #fff;
    width: 100%;
    box-shadow: 0 0 1px 1px rgba($color: #000000, $alpha: 0.1);
    box-sizing: border-box;

    .find-id {
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
        color: #be5656;
        background-color: #f9f9f9;
      }

      .right {
        //   padding: 30px;
        display: flex;
        flex-direction: column;
        align-items: center;
        box-sizing: border-box;
        background: #F3ECE2;
        color: black;
        padding-left: 50px;
        padding-right: 50px;

        h2,
        h4 {
          text-align: center;
          margin: 30px 0;
        }

        .send-code-btn,
        .input-code-btn {
          width: 100%;
          color: #black;
        }

        .disabled,
        .disabled:hover {
          background-color: rgb(136, 154, 152, 0.25);
          color: #be5656;
          cursor: inherit;
        }

        .code {
          margin-top: 20px;
        }
      }
    }
  }
</style>