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
              <v-btn>대표 사진을 입력하세요<input ref="imageInput" type="file" hidden @change="onChangeImages"></v-btn>
            </v-overlay>
          </v-fade-transition>
        </v-img>
      </v-avatar>
    </template>
  </v-hover>
</template>

<script>
  export default {
    name: "SeedThumbnail",
    data: function () {
      return {
        imageUrl: "https://t1.daumcdn.net/cfile/tistory/995A17455A409C9A28",
        overlay: false,
        fileName: null,
        file: null,
        checkImg:"",
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
        if(e.target.files.length === 1){
          this.checkImg = true ; // 파일을 다시 선택했을 때 
        }else{
          this.checkImg = false; // 그 외
        }
        this.fileNameSetting();
        this.transferThumbnail();
      },
      transferThumbnail: function () {
        console.log("file값: " + this.file);
        this.$emit('transferThumbnail', this.file, this.fileName, this.checkImg)
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
        
      }
    }
  }
</script>

<style scoped>
  /* .cursor-img {
  cursor: pointer;
  } */
</style>