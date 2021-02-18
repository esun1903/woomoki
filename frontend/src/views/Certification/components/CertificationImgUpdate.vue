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
          v-if="currentSelectedImg"
          :src="currentSelectedImg"
        >
          <v-fade-transition>
            <v-overlay v-if="hover" absolute color="#036358">
              <v-btn>인증 사진 변경<input ref="imageInput" type="file" hidden @change="onChangeImages"></v-btn>
            </v-overlay>
          </v-fade-transition>
        </v-img>
      </v-avatar>
    </template>
  </v-hover>
</template>

<script>
  export default {
    name: "CertificationImgUpdate",
    data: function () {
      return {
        imageUrl: "",
        overlay: false,
        fileName: null,
        file: null,
        changedImg:"",
      }
    },
    props:{
        currentSelectedImg: String,
    },
    created(){
      this.settingImg();
        console.log("selected: "+this.currentSelectedImg);
        console.log("imgurl: "+ this.imageUrl)
    },
    methods: {
        settingImg(){
        this.imageUrl = this.currentSelectedImg;
        },
      onClickImageUpload() {
        this.$refs.imageInput.click();
      },
      onChangeImages(e) {
        console.log(e.target.files)
        console.log("length "+e.target.files.length)
        if(e.target.files.length === 1){
          this.changedImg = true ; // 파일을 다시 선택했을 때 
        }else{
          this.changedImg = false; // 그 외
        }
        console.log("값: "+ this.changedImg);
        this.file = e.target.files[0]; // Get first index in files
        this.currentSelectedImg = URL.createObjectURL(this.file); // Create File URL
        this.fileNameSetting();
        this.transferCertImg();
      },
      onDeleteImage() {
        this.currentSelectedImg = ""
        this.fileName = ""
        this.file = ""
      },
      transferCertImg: function () {
        console.log("file값: " + this.file);
        this.$emit('transferCertImg', this.file, this.fileName, this.changedImg)
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