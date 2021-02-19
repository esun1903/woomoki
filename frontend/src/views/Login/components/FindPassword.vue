<template>
  <div class="d-flex justify-center bgimg">
    <section class="size">
        <v-container class="title-box">
          <h2 class="text">비밀번호 찾기</h2>
        </v-container>
        
        <v-container>
          <v-row>
            <p class="question">비밀번호를 잊으셨나요?</p>
            <p class="information">이메일로 임시 비밀번호 발급 받기</p>
            <FindPasswordByEmail class="email-btn" />
            <router-link :to="'/login'">
              <BackBtn class="back-btn"/>
            </router-link>
          </v-row>

        </v-container>
    </section>
  </div>
  
</template>


<script>
  import FindPasswordByEmail from "@/views/Login/components/FindPasswordByEmail.vue"
  import BackBtn from "@/views/Login/components/BackBtn.vue"

  export default {
    name: 'FindPassword',
    components: {
      FindPasswordByEmail,
      BackBtn,
    },
    data: function () {

    },
    computed: {
    },
    methods: {
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