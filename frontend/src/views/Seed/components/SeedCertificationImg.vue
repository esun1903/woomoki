<template>
  <v-col>
  <v-row>
      <v-avatar
        tile
        :class="`rounded-lg`"
        width="100%"
        height="250px"
        color="grey"
        class="cursor-img"
        @click="onClickImageUpload"
        >{{ this.text }}
        <v-img
            v-if="imageUrl" :src="imageUrl"
        ></v-img>
      </v-avatar>

      <input ref="imageInput" type="file" hidden @change="onChangeImages">
        
    </v-row>
    <v-row class="d-flex justify-end align-end">
      <v-btn
        @click="onDeleteImage"
        >이미지 제거
      </v-btn>
    </v-row>
  </v-col>
</template>

<script>
export default {
  name: "SeedCertificationImg",
  data: function () {
    return {
      text: "인증 예시 사진",
      exampleImg: null,
    }
  },
  methods: {
    onClickImageUpload() {
    this.$refs.imageInput.click();
    },
    onChangeImages(e) {
        console.log(e.target.files)
        const file = e.target.files[0]; // Get first index in files
        this.imageUrl = URL.createObjectURL(file); // Create File URL
        this.text = ""
    },
    onDeleteImage() {
      this.text = "썸네일 사진 변경"
      this.imageUrl = ""
    }
  }
}
</script>

<style scoped>

.cursor-img {
  cursor: pointer;
  }

</style>
