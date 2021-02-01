<template>
<v-row class="cursor_img d-flex justify-center">
  <v-avatar
    width="300"
    height="300"
    color="success"
    @click="onClickImageUpload"
    >{{ this.text }}
    <v-img
        v-if="imageUrl" :src="imageUrl"
    ></v-img>
  </v-avatar>

  <input ref="imageInput" type="file" hidden @change="onChangeImages">
</v-row>
</template>

<script>
export default {
  name: "ImgEdit",
  data: function () {
      return {
        imageUrl: null,
        text: "프로필 변경"
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
    }
  }
};
</script>

<style scoped>

.cursor_img {cursor: pointer;}

</style>

