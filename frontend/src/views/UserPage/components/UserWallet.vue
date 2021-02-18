<template>
  <v-col class="d-flex justify-center">
    <v-dialog class="cash-dialog" width="35vh">
      <template v-slot:activator="{ on, attrs }">
        <v-btn plain text :ripple="false"  v-bind="attrs" v-on="on" class="btn-cursor">
          <div>
            <h2>나의 캐시</h2>
            <div>{{ UserInfo.deposit }}</div>
          </div>
        </v-btn>
      </template>
      <v-card>
        <v-card-text>
          <p class="oh-icon">🤦‍♀️</p>
          <p class="oh-text">앗! 아직 개발 중ㅎ</p>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-col>
</template>

<script>
export default {
  name: "UserWallet",
  props: {
    UserInfo: [Object, Array],
  },
  data: function () {
    return {
      dialog: {
        dialogm1: "",
        dialog: false,
      },
    }
  }
}
</script>

<style lang="scss" scoped>
.btn-cursor {
  cursor: default;
}

.v-dialog__content{
  margin-left: 5vw;
  margin-top: 5vh;
  .v-dialog{
    .v-card{

      .v-card__text{
        height: 20vh;
        padding: 5vh;
        .oh-icon{
          display: flex;
          justify-content: center;
          font-size: 2.5rem;
          margin-bottom: 5vh;
        }
        .oh-text{
          display: flex;
          justify-content: center;
          font-size: 1.2rem;
          font-weight: bold;
        }
      } 
    }
  }
}
</style>