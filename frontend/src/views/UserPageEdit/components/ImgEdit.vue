<template>
  <v-avatar
    class="cursor_img profile-img-margin"
    width="250"
    height="250"
    color="light-green lighten-2"
    @click="onClickImageUpload"
    >
      <span class="white--text">
        {{ this.text }}
      </span>
    <v-img
        v-if="imageUrl" :src="imageUrl"
    ></v-img>
    <input ref="imageInput" type="file" hidden @change="onChangeImages">
  </v-avatar>
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

.profile-img-margin {
  margin-bottom: 10%;
}

</style>

