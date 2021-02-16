<template>

  <v-col class="d-flex justify-center">
    <v-dialog
      v-model="dialog.dialog"
      scrollable
      max-width="300px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          plain
          text
          v-bind="attrs"
          v-on="on"
          :ripple="false"
        >
          <div>
            <h2>나의 캐시</h2>
            <div>{{ UserInfo.deposit }}</div>
          </div>
        </v-btn>
      </template>

      <v-card>
          <v-card-title class="d-flex justify-space-between">충전하기
          <v-btn
            color="#AED864"
            icon
            @click="dialog.dialog = false"
            class="d-flex justify-center"
          >
            <v-icon>fas fa-times</v-icon>
          </v-btn>
          </v-card-title>
        <v-divider></v-divider>
        
        <v-card-text class="d-flex justify-center mt-5 dialog-height">
          <v-btn color="amber lighten-2 white--text">
            카카오페이
          </v-btn>
        </v-card-text>
        <v-card-actions>
        </v-card-actions>
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

</style>