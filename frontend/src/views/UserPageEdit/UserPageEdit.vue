<template>
  <v-container class="container-size">

    <v-row class="d-flex justify-center img-margin">
      <!-- <ImgEdit :profileImg="profileImg" @transferUpdateProfileImg="receiveUpdateProfileImg"></ImgEdit> -->
      <v-avatar class="cursor_img profile-img-margin" width="250" height="250" color="#AED864" @click="onClickImageUpload">
        <span v-if="!UserInfo.img" class="white--text">
          {{ this.text }}
        </span>
        <v-img v-if="UserInfo.img" :src="UserInfo.img"></v-img>
        <input ref="imageInput" type="file" hidden @change="onChangeImages">
      </v-avatar>
    </v-row>

    <validation-observer ref="observer" v-slot="{ invalid }">

      <form @submit.prevent="submit">

        <v-text-field color="#AED864" :value="UserInfo.nickname" label="아이디" readonly outlined></v-text-field>
        <v-text-field color="#AED864" :value="UserInfo.email" label="이메일" readonly outlined></v-text-field>

        <validation-provider v-slot="{ errors }" name="한 줄 소개">
          <v-text-field color="#AED864" v-model="UserInfo.introduce" :error-messages="errors" label="한 줄 소개"
            counter="20" outlined></v-text-field>
        </validation-provider>

        <validation-provider v-slot="{ errors }" name="핸드폰 번호" :rules="{
              required: true,
              digits: 11,
              regex: /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/
            }">
          <v-text-field color="#AED864" v-model="UserInfo.phone" :counter="11" :error-messages="errors"
            label="핸드폰 번호(-없이 입력해주세요)" outlined></v-text-field>
        </validation-provider>

        <validation-provider v-slot="{ errors }" name="새로운 비밀번호" rules="required|newPassword">
          <v-text-field color="#AED864" v-model="newPassword" :error-messages="errors" label="새로운 비밀번호 입력"
            :type="showPass ? 'text' : 'password'" outlined></v-text-field>
        </validation-provider>

        <validation-provider v-slot="{ errors }" name="새로운 비밀번호 확인" rules="required|passwordConfirm:@새로운 비밀번호"
          vid="newPassword">
          <v-text-field color="#AED864" v-model="passwordConfirmation" :error-messages="errors" label="새로운 비밀번호 확인"
            :type="showPass ? 'text' : 'password'" outlined></v-text-field>
        </validation-provider>

        <CategoryEdit></CategoryEdit>

        <validation-provider v-slot="{ errors }" rules="required" name="프로필 정보 변경 동의">
          <v-checkbox class="mt-5 mb-5" color="#AED864" v-model="checkbox" :error-messages="errors" value="1"
            label="프로필 편집에 동의합니다." type="checkbox" required></v-checkbox>

        </validation-provider>


        <v-row class="d-flex justify-end">
            <v-btn class="mr-4 white--text" type="submit" :disabled="invalid" @click="updateUserInfo" color="#AED864">
              적용
            </v-btn>

          <v-btn @click="clear" text>
            지우기
          </v-btn>

          <router-link :to="{ name: 'UserPage', params : { userNickname: userNickname }}">
            <v-btn class="ml-4" text>
              뒤로가기
            </v-btn>
          </router-link>

        </v-row>
        <UserDelete></UserDelete>
      </form>
    </validation-observer>
  </v-container>
</template>

<script>
  import {
    required,
    digits,
    max,
    regex,
    min
  } from "vee-validate/dist/rules";
  import {
    extend,
    ValidationObserver,
    ValidationProvider,
    setInteractionMode
  } from "vee-validate";
  import CategoryEdit from "./components/CategoryEdit.vue";
  // import ImgEdit from "./components/ImgEdit.vue";
  import UserDelete from "./components/UserDelete.vue";
  import axios from "axios";
  
import router from "@/router";

  setInteractionMode("eager");

  extend("min", {
    ...min,
    message: "{_field_}는 영문, 숫자 포함 4자 이상 10자 이하 입니다"
  });

  extend("digits", {
    ...digits,
    message: "{_value_}는(은) 올바른 전화번호가 아닙니다"
  });

  extend("required", {
    ...required,
    message: "{_field_}는(은) 필수항목입니다"
  });

  extend("max", {
    ...max,
    message: "최대 길이를 초과하였습니다"
  });

  extend("regex", {
    ...regex,
    message: "유효한 핸드폰 번호가 아닙니다"
  });

  extend("newPassword", {
    message: "문자, 숫자, 특수문자 포함 8자리 이상으로 입력해주세요.",
    validate: value => {
      return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value);
    }
  });

  extend("passwordConfirm", {
    params: ["target"],
    validate(value, {
      target
    }) {
      return value === target;
    },
    message: "새로운 패스워드가 일치하지 않습니다."
  });

  export default {
    name: "UserPageEdit",
    components: {
      ValidationProvider,
      ValidationObserver,
      CategoryEdit,
      // ImgEdit,
      UserDelete
    },
    props: {
      userId: Number,
    },
    data: function () {
      return {
        UserInfo: [],
        userNickname: this.$store.state.UserStore.user.nickname,
        newPassword: "",
        passwordConfirmation: "",
        showPass: false,
        checkbox: null,

        albumBucketName: "cert-photo-upload",
        bucketRegion: "ap-northeast-2",
        IdentityPoolId: "ap-northeast-2:8cf7cb29-d051-4f38-885f-09b1e4dd8153",

        photoURL: "https://s3.ap-northeast-2.amazonaws.com/cert-photo-upload/",

        profileImg: null,
        file: null,
        changedImg: false,
        photoKey:"",
        text:"프로필 사진 변경",
      };
    },
    methods: {
      originUserInfo: function () {
        const userNickname = this.$store.state.UserStore.user.nickname
        axios.get(`http://i4a303.p.ssafy.io/api/userPage/${userNickname}`)
          .then((res) => {
            this.UserInfo = res.data
            console.log("기존 데이터", res.data)
          })
          .catch((err) => {
            console.log(err)
          })
      },
      updateUserInfo: function () {

        const userId = this.$store.state.UserStore.user.user_id
        const ChangedUserInfo = {
          email: this.UserInfo.email,
          introduce: this.UserInfo.introduce,
          nickname: this.UserInfo.nickname,
          password: this.newPassword,
          phone: this.UserInfo.phone,
          id: userId,
          img: this.UserInfo.img,
        }

        if (this.changedImg === false) {
          axios.post("http://i4a303.p.ssafy.io/api/userPage/changeUser", ChangedUserInfo)
            .then(res => {
              console.log(res);
                router.push({ name: 'UserPage', params: { userNickname: this.UserInfo.nickname }})
            })
            .catch(err => {
              console.log(err);
            });
        } else {

          this.photoKey = this.profileImg
          ChangedUserInfo.img = this.photoURL + this.profileImg;
          console.log("바뀔 이미지: "+ChangedUserInfo.img);


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

          s3.upload({
              Key: this.photoKey,
              Body: this.file,
              ACL: 'public-read'
            }, (err, data) => {
              if (err) {
                console.log(err)
                return alert('There was an error uploading your photo: ', err.message);
              }
              console.log(data);
            }

          );
          axios.post("http://i4a303.p.ssafy.io/api/userPage/changeUser", ChangedUserInfo)
            .then(res => {
              console.log(res);
               router.push({ name: 'UserPage', params: { userNickname: this.UserInfo.nickname }})
           
            })
            .catch(err => {
              console.log(err);
            });

        }

      },
      submit() {
        this.$refs.observer.validate();
      },
      clear() {
        this.UserInfo.introduce = "",
          this.UserInfo.phone = "",
          this.checkbox = null;
        this.$refs.observer.reset();
      },

      // receiveUpdateProfileImg: function (file, profileImg, changedImg) {
      //   this.file = file
      //   this.profileImg = profileImg
      //   this.changedImg = changedImg
      //   console.log("넘어온 file정보: " + this.file)
      //   console.log("넘어온 profileImg이름 : " + this.profileImg)
      // },
      // img edit 컴포넌트
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
        this.UserInfo.img = URL.createObjectURL(this.file);
          this.text="";
        this.fileNameSetting();
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

        this.profileImg = user_id + "_" + realtime + "_" + this.file.name

      },
      // img edit 컴포넌트

    },
    created() {
      this.originUserInfo()
    },
    computed: {}
  };
</script>

<style lang="scss" scoped>
  a {
    text-decoration: none;
  }

  .container-size {
    width: 40%;
  }

  .img-margin {
    margin-top: 2%;
  }

  .cursor_img {
    cursor: pointer;
  }

  .profile-img-margin {
    margin-bottom: 10%;
  }
</style>