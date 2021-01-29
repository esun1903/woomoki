<template>
  <div>
    <section class="section-container">
      <v-row class="find-password">
        <v-col cols="8" class="left">
          <h1>로고로고로고로고로고로고로고</h1>
        </v-col>
        <v-col cols="4" class="right">
          <h2>Oops!!!!!!!비밀번호를 잊었다니!</h2>
          <h4>휴대폰으로 비밀번호 찾기</h4>
          <div class="text-center">
            <validation-observer v-slot="{ invalid }" ref="observer">
              <v-form @submit.prevent="submit">
                <validation-provider v-slot="{ errors }" name="phone" rules="required|min:12|max:13">
                  <v-text-field v-model="phone" :error-messages="errors" label="Phone" @keyup="getPhoneMask(phone)"
                    required outlined dark filled dense></v-text-field>
                </validation-provider>
                <v-btn class="send-code-btn" type="submit" rounded color="white" :disabled="invalid">
                  인증 코드 보내기
                </v-btn>
                <div class="code" v-if="show">
                  <!-- <v-divider></v-divider> -->
                  <v-text-field label="인증 코드" outlined dark dense></v-text-field>
                  <v-btn class="input-code-btn" @click="checkCode" rounded color="white">
                    입력
                  </v-btn>
                </div>
              </v-form>
            </validation-observer>
            <router-link :to="'/login/findPassword'">
              <BackBtn />
            </router-link>

          </div>
        </v-col>
      </v-row>
    </section>
  </div>
</template>

<script>
  import {
    required,
    min,
    max
  } from 'vee-validate/dist/rules'
  import {
    extend,
    ValidationProvider,
    setInteractionMode,
    ValidationObserver
  } from 'vee-validate'

  import BackBtn from "@/views/Login/components/BackBtn.vue"

  setInteractionMode('eager')

  extend('required', {
    ...required,
    message: '{_field_} can not be empty'

  })

  

  extend('min', {
    ...min,
    message: '휴대폰 번호는 최소 10자의 숫자를 입력해야 합니다'

  })

    extend('max', {
    ...max,
    message: '휴대폰 번호는 최대 11자의 숫자를 입력해야 합니다'

  })

  export default {
    name: 'FindPasswordByPhone',
    components: {
      ValidationProvider,
      ValidationObserver,
      BackBtn,
    },
    data: () => ({
      phone: '',
      show: false
    }),
    computed: {
      params() {
        return {
          phone: this.phone,
        }
      }
    },
    methods: {

      getPhoneMask(val) {
        let res = this.getMask(val)
        this.phone = res
        //서버 전송 값에는 '-' 를 제외하고 숫자만 저장
        this.model.phone = this.phone.replace(/[^0-9]/g, '')
      },

      getMask(phoneNumber) {
        if (!phoneNumber) return phoneNumber
        phoneNumber = phoneNumber.replace(/[^0-9]/g, '')

        let res = ''
        if (phoneNumber.length < 3) {
          res = phoneNumber
        } else {
          if (phoneNumber.substr(0, 2) == '02') {

            if (phoneNumber.length <= 5) { //02-123-5678
              res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3)
            } else if (phoneNumber.length > 5 && phoneNumber.length <= 9) { //02-123-5678
              res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3) + '-' + phoneNumber.substr(5)
            } else if (phoneNumber.length > 9) { //02-1234-5678
              res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 4) + '-' + phoneNumber.substr(6)
            }

          } else {
            if (phoneNumber.length < 8) {
              res = phoneNumber
            } else if (phoneNumber.length == 8) {
              res = phoneNumber.substr(0, 4) + '-' + phoneNumber.substr(4)
            } else if (phoneNumber.length == 9) {
              res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
            } else if (phoneNumber.length == 10) {
              res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
            } else if (phoneNumber.length > 10) { //010-1234-5678
              res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 4) + '-' + phoneNumber.substr(7)
            }
          }
        }

        return res
      },

      async submit() {
        const valid = await this.$refs.observer.validate()
        if (valid) {
          alert('인증 코드를 발송하였습니다.\n 인증 코드를 입력해주세요.');
          this.show = !this.show;
        }
      },
      checkCode() {
        this.$router.push("/login/findPasswordResult");
      }
    }
  }
</script>

<style lang="scss" scoped>
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

        .send-code-btn,
        .input-code-btn {
          width: 100%;
          color: #be5656;
        }

        .disabled,
        .disabled:hover {
          background-color: rgb(136, 154, 152, 0.25);
          color: #f8f8f8;
          cursor: inherit;
        }

        .code {
          margin-top: 20px;
        }
      }
    }
  }
</style>