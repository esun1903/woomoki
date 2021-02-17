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
        >
          <v-fade-transition>
            <v-overlay v-if="hover" absolute color="#036358">
              <v-btn>인증 사진 등록<input ref="imageInput" type="file" hidden @change="onChangeImages"></v-btn>
            </v-overlay>
          </v-fade-transition>
        </v-img>
      </v-avatar>
    </template>
  </v-hover>
</template>CertificationImg

<script>
  export default {
    name: "SeedThumbnail",
    data: function () {
      return {
        imageUrl: "https://www.nicepng.com/png/detail/102-1020827_polaroid-picture-frame-free-image-on-pixabay-polaroid.png",
        text: "썸네일 사진 변경",
        overlay: false,
        fileName: null,
        file: null,
      }
    },
    methods: {
      onClickImageUpload() {
        this.$refs.imageInput.click();
      },
      onChangeImages(e) {
        console.log(e.target.files)
        this.file = e.target.files[0]; // Get first index in files
        this.imageUrl = URL.createObjectURL(this.file); // Create File URL
        this.fileNameSetting();
        this.transferCertImg();
      },
      onDeleteImage() {
        this.text = "썸네일 사진 변경"
        this.imageUrl = ""
        this.fileName = ""
        this.file = ""
      },
      transferCertImg: function () {
        console.log("file값: " + this.file);
        this.$emit('transferCertImg', this.file, this.fileName)
      },
      async fileNameSetting() {

        const user_id = this.$store.state.UserStore.user.user_id;

        var now = new Date();

        var year = now.getFullYear(); // 연도
        var month = now.getMonth() + 1; // 월
        var date = now.getDate(); // 일
        var hours = now.getHours(); // 시간
        var minutes = now.getMinutes(); // 분
        var seconds = now.getSeconds(); // 초
        var milliseconds = now.getMilliseconds(); // 밀리초

        // console.log("현재 : ", now);
        var realtime = year + "" + month + "" + date + "_" + hours + minutes + seconds + milliseconds;
        console.log(realtime);

         this.fileName = user_id + "_" + realtime + "_" + this.file.name
        
      }
    }
  }
</script>

<style scoped>
  /* .cursor-img {
  cursor: pointer;
  } */
</style>