<template>
  <span>
    <v-btn v-if="isLogin" icon @click.stop="dialog = true">
      <v-icon color="light-green lighten-2">
        fas fa-ellipsis-h
      </v-icon>
    </v-btn>
    <v-dialog
      v-model="dialog"
      max-width="300"
    >
      <v-card>
        <div class="d-flex justify-space-between">

        <v-card-title>
          더보기
        </v-card-title>

        <div class="d-flex align-center">
          <v-btn
            color="light-green lighten-2"
            icon
            @click="dialog = false"
          >
            <v-icon class="mr-3" size="32">fas fa-times</v-icon>
          </v-btn>
        </div>
        </div>
        <v-divider></v-divider>

        <v-card-text class="d-flex justify-center mt-5 dialog-height">
          <v-btn color="light-green lighten-2 white--text">
            신고하기
          </v-btn>
          <!-- <v-btn text class="m-5">
            <h3>
            신고하기
            </h3>
          </v-btn> -->
        </v-card-text>

        <v-card-actions>
          <!-- <v-spacer></v-spacer>

          <v-btn
            color="light-green lighten-2"
            text
            @click="dialog = false"
          >
            취소
          </v-btn> -->
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