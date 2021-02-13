<template>
  <span>
    <v-btn v-if="isLogin" icon @click.stop="dialog = true">
      <v-icon color="light-green lighten-2">
        fas fa-ellipsis-h
      </v-icon>
    </v-btn>
    <v-dialog
      v-model="dialog"
      max-width="290"
    >
      <v-card>
        <v-card-title>
        </v-card-title>

        <v-btn text>
          <h3>
          신고하기
          </h3>
        </v-btn>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="light-green lighten-2"
            text
            @click="dialog = false"
          >
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </span>

  
</template>

<script>
export default {
  name: "SeedViewMore",
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