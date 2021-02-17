<template>
  <v-avatar class="cursor_img profile-img-margin" width="250" height="250" color="#AED864" @click="onClickImageUpload">
    <span v-if="!imageUrl" class="white--text">
      {{ this.text }}
    </span>
    <v-img v-if="imageUrl" :src="imageUrl"></v-img>
    <input ref="imageInput" type="file" hidden @change="onChangeImages">
  </v-avatar>
</template>

<script>
  export default {
    name: "ImgEdit",
    data: function () {
      return {
        imageUrl: null,
        text: "프로필 변경",
        file: null,
        fileName: null,
      }
    },
    created() {
      this.getImage();
    },
    props: {
      profileImg: String,
    },
    methods: {
      getImage() {

        this.imageUrl = this.profileImg;
        console.log("받은 이미지: " + this.profileImg);
        console.log("보여줄 이미지: " + this.imageUrl);
      },
      onClickImageUpload() {
        this.$refs.imageInput.click();
      },
      onChangeImages(e) {
        console.log(e.target.files)
        this.file = e.target.files[0]; // Get first index in files
        this.imageUrl = URL.createObjectURL(this.file); // Create File URL
        this.fileNameSetting();
        this.transferUpdateProfileImg();
        this.text = ""
      },
      transferUpdateProfileImg: function () {
        this.$emit('transferUpdateProfileImg', this.file, this.fileName)
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

      },
    }
  };
</script>

<style scoped>
  .cursor_img {
    cursor: pointer;
  }

  .profile-img-margin {
    margin-bottom: 10%;
  }
</style>