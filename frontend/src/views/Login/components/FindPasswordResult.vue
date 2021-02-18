<template>
  <div>
    <section class="section-container">
      <v-row class="find-password-result">
        <v-col cols="8" class="left">
          <h1>로고로고로고로고로고로고로고</h1>
        </v-col>
        <v-col cols="4" class="right">
          <h2>Oops!!!!!!!비밀번호를 잊었다니!</h2>
          <h4>비밀번호 찾기</h4>
          <div class="text-center">
            <span class="showResult">
              회원님의 비밀번호는 
              <br>
            <b>ssafy1111!</b> 입니다. 
            </span>
            <router-link :to="'/login'">
              <GoLoginBtn />
            </router-link>
          </div>
        </v-col>
      </v-row>
    </section>
  </div>
</template>

<script>
  import GoLoginBtn from "@/views/Login/components/GoLoginBtn.vue"

  export default {
    name: 'FindPasswordResult',
    components: {
      GoLoginBtn,
    },
    data: () => ({
    }),
    computed: {
      params() {
        return {
        }
      }
    },
    methods: {
    }
  }
</script>

<style lang="scss" scoped>


  a {
    text-decoration: none;
    color: #ffffff;
  }

  .section-container {
    background: #fff;
    width: 100%;
    box-shadow: 0 0 1px 1px rgba($color: #000000, $alpha: 0.1);
    box-sizing: border-box;

    .find-password-result {
      padding: 0;
      margin: 0 auto;
      min-height: 690px;
      box-shadow: 0 0 1px 1px rgba($color: #000000, $alpha: 0.1);

      .left {
        justify-content: center;
        align-items: center;
        box-sizing: border-box;
        display: flex;
        color: #be5656;
        background-color: #f9f9f9;
      }

      .right {
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

        .disabled,
        .disabled:hover {
          background-color: rgb(136, 154, 152, 0.25);
          color: #f8f8f8;
          cursor: inherit;
        }
      .showResult {
          margin-top: 20px;
        }
      }
    }
  }
</style>