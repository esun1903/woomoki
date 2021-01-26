<template>
    <div>
    <section class="section-container">
      <v-row class="find-password">
        <v-col cols="8" class="left">
          <h1>로고로고로고로고로고로고로고</h1>
        </v-col>
        <v-col cols="4" class="right">
          <h2>Oops!!!!!!!비밀번호를 잊었다니!</h2>
          <h4>임시 비밀번호 발급받기</h4>
            <validation-observer v-slot="{ invalid }">
            <v-form @submit.prevent="submit">
              <validation-provider v-slot="{ errors }" name="Name" rules="required|email">
                <v-text-field v-model="email" :error-messages="errors" label="Email" required outlined dark filled dense></v-text-field>
              </validation-provider>

              <div class="text-center">
                <v-btn class="send-email" type="submit" rounded color="white" :disabled="invalid">
                  임시 비밀번호 발송
                </v-btn>
                 <router-link :to="'login'">
                 <v-btn class="back-btn" rounded color="white">
                  이전 페이지로 돌아가기
                </v-btn>
                </router-link>
              </div>
            </v-form>
          </validation-observer>
        </v-col>
      </v-row>
    </section>
  </div>
</template>
 
<script>
  import { required, email } from 'vee-validate/dist/rules'
  import { extend, ValidationProvider, setInteractionMode, ValidationObserver } from 'vee-validate'

setInteractionMode('eager')

  extend('required', {
    ...required,
    message: '{_field_} can not be empty'
  })

  extend('email', {
    ...email,
    message: 'Email must be valid'
  })

  export default {
    name: 'FindPassword',
    components: {
      ValidationProvider,
      ValidationObserver,
     
    },
    data: () => ({
      email: '',
      isSubmit: false,
    }),
    computed: {
      params() {
        return {
          email: this.email,
        }
      }
    },
    methods: {
       
      async submit() {
        this.isSubmit = false;
        const valid = await this.$refs.observer.validate()
        if (valid) {
          this.isSubmit = true;
          console.log(this.isSubmit);
          alert('임시 비밀번호를 발송하였습니다!\n이메일을 확인해주세요');
          location.href='login';
        //   this.login(this.params) // action to login
        } else {
          this.isSubmit = false;
          console.log(this.isSubmit);
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

        h2, h4 {
          text-align: center;
          margin: 30px 0;
        }

        .send-email {
          width: 100%;
          color: #be5656;
        }
        .disabled,.disabled:hover {
            background-color: rgb(136, 154, 152, 0.25);
            color: #f8f8f8;
            cursor: inherit;
            }
        .back-btn {
          width: 100%;
          color: #be5656;
          margin-top:20px;
          text-decoration:none;
        }
      }
    }
  }
</style>