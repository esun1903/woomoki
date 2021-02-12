<template>
  <span>
    <v-btn icon @click.stop="dialog = true">
      <v-icon>
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
            color="green darken-1"
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
    }
  }
}
</script>

<style lang="scss" scoped>

</style>