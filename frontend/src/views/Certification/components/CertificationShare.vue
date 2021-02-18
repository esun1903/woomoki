<template>
  <v-dialog
      v-model="dialog"
      max-width="290"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="mr-3 ml-3"
          color="black"
          icon
          v-bind="attrs"
          v-on="on"
          v-if="isLogin"
        >
          <v-icon>fas fa-share-alt</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="d-flex justify-center">
          씨앗을 공유해보세요
        </v-card-title>
          <div class="d-flex justify-center">
            <v-btn icon plain class="mt-2">
              <v-icon size="40" :ripple="false" gradient color="#AED864">fab fa-instagram</v-icon>
            </v-btn>
          </div>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            text
            @click="dialog = false"
          >
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
export default {
  name: "CertificationShare",
  data: function () {
    return {
      dialog: false,
      isLogin : this.$store.state.UserStore.isLogin
    }
  }
}
</script>

<style lang="scss" scoped>

</style>