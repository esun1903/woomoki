<template>
  <div>
    <section class="section-container">
      <v-row class="find-password">
        <v-col cols="8" class="left">
          <h1>로고로고로고로고로고로고로고</h1>
        </v-col>
        <v-col cols="4" class="right">
          <h2>Oops!!!!!!!비밀번호를 잊었다니!</h2>
          <h4>임시 비밀번호 발급 수단 선택</h4>
          <div class="find-password-btn">
            <FindPasswordByPhoneBtn />
            <v-divider></v-divider>
            <FindPasswordByEmailBtn />
          </div>
          <div>
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
  import FindPasswordByPhoneBtn from "@/views/Login/components/FindPasswordByPhoneBtn.vue"
  import FindPasswordByEmailBtn from "@/views/Login/components/FindPasswordByEmailBtn.vue"
  import BackBtn from "@/views/Login/components/BackBtn.vue"

  export default {
    name: 'FindPassword',
    components: {
      FindPasswordByPhoneBtn,
      FindPasswordByEmailBtn,
      BackBtn,
    },
    computed: {

    },
    methods: {

    }
  }
</script>

<style lang="scss" scoped>
  // @import "Login.scss";

  #join-find-password-btn {
    display: flex;
    justify-content: space-around;
    font-size: 15px;
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
      }
    }
  }
</style>