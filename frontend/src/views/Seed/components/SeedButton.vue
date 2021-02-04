<template>

  <!-- bottomsheets -->
  <div class="text-center">
    <v-bottom-sheet
      v-model="sheet"
      inset
    >
      <template v-slot:activator="{ on, attrs }">
      
        <v-btn
          color="success lighten-2"
          dark
          v-bind="attrs"
          v-on="on"
        >생성
        </v-btn>
      </template>
      <v-sheet
        class="text-center"
        height="200px"
      >
        <v-btn
          class="mt-6"
          text
          color="error"
          @click="sheet = !sheet"
        >
          닫기
        </v-btn>
        <div class="my-3">
          참여자가 있으면 씨앗을 없앨 수 없어요
        </div>
      </v-sheet>
    </v-bottom-sheet>
    <v-btn class="ma-2">취소</v-btn>
  </div>
  

  <!-- <v-col>
      <v-col class="d-flex justify-end">
        <v-btn
          color="success" 
          class="ma-2"
        >
          생성
        </v-btn>
        <v-btn class="ma-2">취소</v-btn>
      </v-col>
  </v-col> -->
</template>

<script>
export default {
  name: "SeedButton",
  data: function() {
    return {
      sheet: false,
    }
  }
}
</script>

<style scoped>

</style>