<template>
  <div>
    <section class="section-container">
      <v-row class="find-id">
        <v-col cols="8" class="left">
          <h1>로고로고로고로고로고로고로고</h1>
        </v-col>
        <v-col cols="4" class="right">
          <h2>Oops!!!!!!!아이디를 잊었다니!</h2>
          <h4>아이디 찾기</h4>
          <div class="text-center">
            <validation-observer v-slot="{ invalid }" ref="observer">
              <v-form @submit.prevent="submit">
                <validation-provider v-slot="{ errors }" name="phone" rules="required|integer|length:11">
                  <v-text-field v-model="phone" :error-messages="errors" label="Phone" required outlined dark filled
                    dense></v-text-field>
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
  import {
    required,
    integer,
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

  extend('required', {
    ...required,
    message: '{_field_} can not be empty'

  })

  extend('integer', {
    ...integer,
    message: '휴대폰 번호를 확인해주세요'

  })

  extend('length', {
    ...length,
    message: '휴대폰 번호는 10 또는 11자의 숫자로 이루어져야 합니다'

  })


  export default {
    name: 'FindId',
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

      async submit() {
        const valid = await this.$refs.observer.validate()
        if (valid) {
          alert('인증 코드를 발송하였습니다.\n 인증 코드를 입력해주세요.');
          this.show = !this.show;
        }
      },
      checkCode(){
         this.$router.push("/login/findIdResult");
      }
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