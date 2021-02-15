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
          color="light-green lighten-2"
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
                <v-btn>인증 예시 사진 변경<input ref="imageInput" type="file" hidden @change="onChangeImages"></v-btn>
              </v-overlay>
            </v-fade-transition>
          </v-img>
        
        <!-- <v-fade-transition>
          <v-overlay
            v-if="hover"
            absolute
            color="#036358"
          >
            <v-btn>인증 예시 사진 변경<input ref="imageInput" type="file" hidden @change="onChangeImages"></v-btn>
          </v-overlay>
        </v-fade-transition> -->
      </v-avatar>
    </template>
  </v-hover>
</template>

<script>
import AWS from 'aws-sdk'

export default {
  name: "SeedCertificationImg",
  data: function () {
    return {
      text: "인증 예시 사진",
      imageUrl : "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/1P88/image/MK3vcYmgiNjgHG0lyRfRPRxtW7M.jpg",
      exampleImg: null,

      file: null,
      // albumBucketName, bucketRegion, IdentityPoolId = AWS S3 bucket value -> .env save 
      albumBucketName: "cert-photo-upload",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:8cf7cb29-d051-4f38-885f-09b1e4dd8153",          
      selectedImage: null,
      photoURL: "https://s3.ap-northeast-2.amazonaws.com/cert-photo-upload/",
      fileList: [],
      select: [],
      items: [],
      search: "", //sync search
      certForm: {
        cng_id: "",
        user_id: "",
        content: "",
        img: "",
        // select: [],
      }
    }
  },
  methods: {
    onClickImageUpload() {
    this.$refs.imageInput.click();
    },
    onChangeImages(e) {
        console.log(e.target.files)
        const file = e.target.files[0]; // Get first index in files
        this.selectedImage = URL.createObjectURL(file); // Create File URL
        this.text = ""
    },
    onDeleteImage() {
      this.text = "인증 사진 변경"
      this.imageUrl = ""
    },
    transferThumbnail: function () {
      this.$emit('transferCertificationImg', this.imageUrl)
    },
    writeCert() {
      // AWS Setting Start
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
                IdentityPoolId: this.IdentityPoolId
        })
      });
      const s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: {
          Bucket: this.albumBucketName
        }
      });
      const user_id = this.$store.state.UserStore.user.user_id;
      this.certForm.user_id = user_id;
      //챌린지 id값 받아오기
      this.certForm.cng_id = 1;

      var now = new Date();

      var year = now.getFullYear(); // 연도
      var month = now.getMonth() + 1; // 월
      var date = now.getDate(); // 일
      var hours = now.getHours(); // 시간
      var minutes = now.getMinutes(); // 분
      var seconds = now.getSeconds(); // 초
      var milliseconds = now.getMilliseconds(); // 밀리초

      var realtime = year + "" + month + "" + date + "_" + hours + minutes + seconds + milliseconds;
      console.log(realtime);

      let photoKey = user_id + "_" + realtime + "_" + this.file.name
      this.certForm.img = photoURL + photoKey;
      
      // 여기는 파일명
      // let photoKey = "http://www.topstarnews.net/news/photo/first/201709/img_306795_1.jpg"
                
      this.certForm.img = photoKey;
      
      console.log(this.certForm.content);
      console.log(this.certForm.img);
      console.log(this.certForm.user_id);
      console.log(this.certForm.cng_id);

      // this.$store.dispatch("CertStore/writeCert", this.certForm);
      // console.log(this.certForm);

      s3.upload({
        Key: photoKey,
        Body: this.file,
        ACL: 'public-read'
        }, (err, data) => {
          if (err) {
              console.log(err)
              return alert('There was an error uploading your photo: ', err.message);
            }
          this.$store.dispatch("CertStore/writeCert", this.certForm);
          console.log(this.certForm);
          console.log(data);
          this.getFiles();
        }
      )
    },
    // getFiles() {
    //   // AWS Setting Start

    //   AWS.config.update({
    //     region: this.bucketRegion,
    //     credentials: new AWS.CognitoIdentityCredentials({
    //         IdentityPoolId: this.IdentityPoolId
    //     })
    //   });

    //   const s3 = new AWS.S3({
    //     apiVersion: "2006-03-01",
    //     params: {
    //         Bucket: this.albumBucketName
    //     }
    //   });

    //   // AWS Setting End
    //   s3.listObjects({
    //         Delimiter: '/'
    //     }, (err, data) => {
    //       // [ (err, data) => ]의 형태의 arrow function 으로 해주지 않고 [ function (err, data) ]으로 사용하면 function{...}의 ... 부분으로 인식되어 this가 상단부에서 선언한 값으로 처리되지 않는다. 
    //       if (err) {
    //         return alert('There was an error listing your albums: ' + err.message);
    //       } else {
    //         this.fileList = data.Contents;
    //         console.log(data);
    //       }
    //     }
    //   );
    // },
    // deleteFile(key) {
    //   // AWS Setting Start

    //   AWS.config.update({
    //       region: this.bucketRegion,
    //       credentials: new AWS.CognitoIdentityCredentials({
    //               IdentityPoolId: this.IdentityPoolId
    //       })
    //   });

    //   const s3 = new AWS.S3({
    //     apiVersion: "2006-03-01",
    //     params: {
    //         Bucket: this.albumBucketName
    //     }}
    //   );

    //   // AWS Setting End

    //   s3.deleteObject({
    //       Key: key
    //   }, (err, data) => {
    //       if (err) {
    //           return alert('There was an error deleting your photo: ', err.message);
    //       }

    //       alert('Successfully deleted photo.');
    //       console.log(data)
    //       this.getFiles()
    //     }
    //   );
    // }
  },
  
}
</script>

<style scoped>

.cursor-img {
  cursor: pointer;
  }

</style>
