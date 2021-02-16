<template>
  <v-container class="container-size">
      
      <v-row class="d-flex justify-center img-margin">
        <ImgEdit></ImgEdit>
      </v-row>
      
      <validation-observer ref="observer" v-slot="{ invalid }">
        
        <form @submit.prevent="submit">

          <v-text-field
            color="#AED864"
            :value="UserInfo.nickname"
            label="아이디"
            readonly
            outlined
          ></v-text-field>
          <v-text-field
            color="#AED864"
            :value="UserInfo.email"
            label="이메일"
            readonly
            outlined
          ></v-text-field>
        
          <validation-provider 
            v-slot="{ errors }" 
            name="한 줄 소개">
            <v-text-field
              color="#AED864"
              v-model="UserInfo.introduce"
              :error-messages="errors"
              label="한 줄 소개"
              counter="20"
              outlined
            ></v-text-field>
          </validation-provider>

          <validation-provider
            v-slot="{ errors }"
            name="핸드폰 번호"
            :rules="{
              required: true,
              digits: 11,
              regex: /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/
            }"
          >
            <v-text-field
              color="#AED864"
              v-model="UserInfo.phone"
              :counter="11"
              :error-messages="errors"
              label="핸드폰 번호(-없이 입력해주세요)"
              outlined
            ></v-text-field>
          </validation-provider>

          <validation-provider
            v-slot="{ errors }"
            name="새로운 비밀번호"
            rules="required|newPassword"
          >
            <v-text-field
              color="#AED864"
              v-model="newPassword"
              :error-messages="errors"
              label="새로운 비밀번호 입력"
              :type="showPass ? 'text' : 'password'"
              counter="12"
              outlined
            ></v-text-field>
          </validation-provider>

          <validation-provider
            v-slot="{ errors }"
            name="새로운 비밀번호 확인"
            rules="required|passwordConfirm:@새로운 비밀번호"
            vid="newPassword"
          >
            <v-text-field
              color="#AED864"
              v-model="passwordConfirmation"
              :error-messages="errors"
              label="새로운 비밀번호 확인"
              :type="showPass ? 'text' : 'password'"
              counter="12"
              outlined
            ></v-text-field>
          </validation-provider>

          <CategoryEdit></CategoryEdit>

          <validation-provider
            v-slot="{ errors }"
            rules="required"
            name="프로필 정보 변경 동의"
          >
            <v-checkbox
              class="mt-5 mb-5"
              color="#AED864"
              v-model="checkbox"
              :error-messages="errors"
              value="1"
              label="프로필 편집에 동의합니다."
              type="checkbox"
              required
            ></v-checkbox>

          </validation-provider>
          

          <v-row class="d-flex justify-end">
            <router-link :to="{ name: 'UserPage', params: { userNickname: userNickname }}">
              <v-btn 
                class="mr-4" 
                type="submit" 
                :disabled="invalid"
                @click="updataUserInfo"
                color="#AED864  white--text">
                적용
              </v-btn>
            </router-link>

            <v-btn 
              @click="clear"
              text
              >
              지우기
            </v-btn>

            <router-link :to="{ name: 'UserPage', params : { userNickname: userNickname }}">
              <v-btn 
                class="ml-4"
                text
                >
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
import { required, digits, max, regex, min } from "vee-validate/dist/rules";
import {
  extend,
  ValidationObserver,
  ValidationProvider,
  setInteractionMode
} from "vee-validate";
import CategoryEdit from "./components/CategoryEdit.vue";
import ImgEdit from "./components/ImgEdit.vue";
import UserDelete from "./components/UserDelete.vue";
import axios from "axios";

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
  message: "문자, 숫자, 특수 문자 포함 8자리 이상 12자리 이하로 입력해주세요",
  validate: value => {
    return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value);
  }
});

extend("passwordConfirm", {
  params: ["target"],
  validate(value, { target }) {
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
    ImgEdit,
    UserDelete
  },
  props: {
    userId: Number,
  },
  data: function() {
    return {
      UserInfo: [],
      userNickname: this.$store.state.UserStore.user.nickname,
      newPassword: "",
      passwordConfirmation: "",
      showPass: false,
      checkbox: null,
    };
  },
  methods: {
    originUserInfo: function() {
      const userNickname = this.$store.state.UserStore.user.nickname 
      axios.get(`http://localhost:8080/userPage/${userNickname}`)
        .then((res) => {
          this.UserInfo = res.data
          console.log("기존 데이터",res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    updataUserInfo: function() {
      const userId = this.$store.state.UserStore.user.user_id 
      const ChangedUserInfo = {
        email: this.UserInfo.email,
        introduce: this.UserInfo.introduce,
        nickname: this.UserInfo.nickname,
        password: this.newPassword,
        phone: this.UserInfo.phone,
        id: userId,
        // img: this.UserInfo.img,
        // introduce: this.UserInfo.introduce,
      }
      // const nickname = this.$store.state.UserStore.user.nickname 
      axios.post("http://localhost:8080/userPage/changeUser", ChangedUserInfo)
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    submit() {
      this.$refs.observer.validate();
    },
    clear() {
      this.UserInfo = "",
      this.checkbox = null;
      this.$refs.observer.reset();
    },
  },
  created() {
    this.originUserInfo()
  },
  computed: {
  }
};
</script>

<style lang="scss" scoped>

a {text-decoration: none;}

.container-size {
  width: 40%;
}
</style>
