<template>
  <div class="d-flex justify-center bgimg">
    <section class="size">
        <v-container class="title-box">
          <h2 class="text">아이디 찾기</h2>
        </v-container>
        
        <v-container>
          <v-row>
            <p class="question">아이디를 잊으셨나요?</p>
            <p class="information">등록된 핸드폰 번호로 아이디 찾기</p>
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
                등록하신 아이디는 <br><b>{{ this.userInfo.email }}</b><br>입니다.
              </div>
              <router-link :to="'/login'">
                <BackBtn />
              </router-link>
            </div>
          </v-row>

        </v-container>
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
    async submit() {
      const valid = await this.$refs.observer.validate()
      if (valid) {
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
  #find-password-btn {
    display: flex;
    justify-content: space-around;
    font-size: 15px;
  }

a { text-decoration: none; }

body, html { 
  margin: 0;
  padding: 0;
  height: 100%;
}

.email-btn{
  margin-bottom: 0;
}

.back-btn{
  margin-top: 0 !important;
}


.bgimg {
  border: 0;
  padding: 0;
  height: 91vh;
  background-image: url("https://demos.creative-tim.com/vue-material-kit/img/profile_city.435c148e.jpg");
  min-height: 100%;
  background-position: center;
  background-size: cover;
  .size{
    .title-box{
      .login-text{
        font-size: 1.6vw;
        color: white;
        margin-top: 1.5vh;
        margin-bottom: 1vh;
      }
    }
    .container{
      .row{
        margin-top: 0.5vh;
        display: flex;
        justify-content: center;
        .question{
          font-size: 0.9rem;
        }
        .information{
          margin-top: 2vh;
        }
      }
    }

  }
}

.size {
  margin-top: 20vh;
  width: 23%;
  height: 55vh;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0.5px 0.5px 5px rgb(172, 172, 172);
  .title-box {
    width: 90%;
    height: 20%;
    background: linear-gradient(70deg, #7CB342, #AED864,);
    // background-color: #AED864;
    box-shadow: 5px 5px 10px grey;
    position: relative;
    top: -5vh;
    border-radius: 10px;
    margin-bottom: -2.5vh;
    display: flex;
    justify-content: center;
    align-items: center;
    .text{
      font-size: 1.6vw;
      color: white;
      margin-top: 1.5vh;
      margin-bottom: 1vh;
    }
  }
}

.input-size{
  width: 20vw;
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
  font-size: 0.9vw;
  color: black;
}

.message {
  margin-bottom: 5.5vh;
}

.seed-icon{
  font-size: 1rem;
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
</style>