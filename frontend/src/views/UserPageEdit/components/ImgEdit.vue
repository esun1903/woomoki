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
        changedImg:"",
        tmpImg: "",
      }
    },
    created() {
      this.getImage();
    },
    props: {
      profileImg: String,
    },
    watch: {
    },
    methods: {
      getImage() {
        this.tmpImg = this.profileImg
        this.imageUrl = this.tmpImg;
        console.log("받은 이미지: " + this.tmpImg);
        console.log("보여줄 이미지: " + this.imageUrl);
      },
      onClickImageUpload() {
        this.$refs.imageInput.click();
      },
      onChangeImages(e) {
        console.log(e.target.files)
        this.file = e.target.files[0]; 
        if(e.target.files.length === 1){
          this.changedImg = true ; 
        }else{
          this.changedImg = false; 
        }
        this.imageUrl = URL.createObjectURL(this.file);
        this.fileNameSetting();
        this.transferUpdateProfileImg();
        this.text = ""
      },
      transferUpdateProfileImg: function () {
        this.$emit('transferUpdateProfileImg', this.file, this.fileName,  this.changedImg)
      },
      async fileNameSetting() {

        const user_id = this.$store.state.UserStore.user.user_id;

        var now = new Date();

        var year = now.getFullYear(); 
        var month = now.getMonth() + 1;
        var date = now.getDate(); 
        var hours = now.getHours(); 
        var minutes = now.getMinutes(); 
        var seconds = now.getSeconds(); 
        var milliseconds = now.getMilliseconds(); 

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