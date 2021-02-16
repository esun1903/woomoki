<template>
  <v-hover>
  <template v-slot:default="{ hover }"> 
    <v-avatar
        tile
        :class="`rounded-lg`"
        width="100%"
        height="100%"
        max-width="100%"
        max-height="100%"
        color="#AED864"
        class="cursor-img"
        @click="onClickImageUpload"
        >
        <v-img
          :class="`rounded-lg`"
          width="100%"
          height="100%"
          max-width="100%"
          max-height="100%"
          v-if="imageUrl"
          :src="imageUrl"
          @click="onClickImageUpload"
        >
          <v-fade-transition>
            <v-overlay
              v-if="hover"
              absolute
              color="#036358"
            >
              <v-btn>대표사진 변경<input ref="imageInput" type="file" hidden @change="onChangeImages"></v-btn>
            </v-overlay>
          </v-fade-transition>
        </v-img>
        
        <!-- <v-fade-transition>
          <v-overlay
            v-if="hover"
            absolute
            color="#036358"
          >
            <v-btn>대표사진 변경<input ref="imageInput" type="file" hidden @change="onChangeImages"></v-btn>
          </v-overlay>
        </v-fade-transition> -->
      </v-avatar>
    </template>
  </v-hover>
  <!-- <v-col>
    <v-row>
        <v-avatar
          tile
          :class="`rounded-lg`"
          width="100%"
          height="250px"
          color="grey lighten-3"
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
  </v-col> -->
</template>

<script>
export default {
  name: "SeedThumbnail",
  data: function () {
    return {
      imageUrl: "https://t1.daumcdn.net/cfile/tistory/995A17455A409C9A28",
      // text: "썸네일 사진 변경",
      overlay: false,
      fileName: null,
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
        this.fileName = file.name;
        console.log(this.fileName);
        this.text = "";
    },
    onDeleteImage() {
      this.text = "썸네일 사진 변경"
      this.imageUrl = ""
      this.fileName = ""
    },
    transferThumbnail: function () {
      this.$emit('transferThumbnail', this.fileName)
    }
  }
}
</script>

<style scoped>

/* .cursor-img {
  cursor: pointer;
  } */


</style>
