<template>
  <v-hover>
    <template v-slot:default="{ hover }"> 
      <v-avatar
          tile
          :class="`rounded-lg`"
          width="100%"
          height="450px"
          max-width="100%"
          max-height="100%"
          color="grey lighten-3"
          class="cursor-img"
          @click="onClickImageUpload"
          >
          <v-img
              v-if="imageUrl"
              :src="imageUrl"
              contain
          ></v-img>
        
        <v-fade-transition>
          <v-overlay
            v-if="hover"
            absolute
            color="#036358"
          >
            <v-btn>인증 예시 사진 변경<input ref="imageInput" type="file" hidden @change="onChangeImages"></v-btn>
          </v-overlay>
        </v-fade-transition>
      </v-avatar>
    </template>
  </v-hover>
</template>

<script>
export default {
  name: "SeedCertificationImg",
  data: function () {
    return {
      text: "인증 예시 사진",
      imageUrl : "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/1P88/image/MK3vcYmgiNjgHG0lyRfRPRxtW7M.jpg",
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
      this.text = "인증 사진 변경"
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
