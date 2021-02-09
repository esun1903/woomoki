<template>
  <v-container class="container-size">
      
      <v-row class="d-flex justify-center">
        <ImgEdit></ImgEdit>
      </v-row>
      
      <validation-observer ref="observer" v-slot="{ invalid }">
        
        <form @submit.prevent="submit">

          <v-text-field
            :value="UserInfo.nickname"
            label="아이디"
            readonly
            outlined
          ></v-text-field>
          <v-text-field
            :value="UserInfo.email"
            label="이메일"
            readonly
            outlined
          ></v-text-field>
        
          <validation-provider 
            v-slot="{ errors }" 
            name="한 줄 소개">
            <v-text-field
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
              v-model="UserInfo.phone"
              :counter="11"
              :error-messages="errors"
              label="핸드폰 번호(-없이 입력해주세요)"
              required
              outlined
            ></v-text-field>
          </validation-provider>

          <validation-provider
            v-slot="{ errors }"
            name="새로운 비밀번호"
            rules="required|newPassword"
          >
            <v-text-field
              v-model="newPassword"
              :error-messages="errors"
              label="새로운 비밀번호 입력"
              :type="showPass ? 'text' : 'password'"
              required
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
              v-model="passwordConfirmation"
              :error-messages="errors"
              label="새로운 비밀번호 확인"
              required
              :type="showPass ? 'text' : 'password'"
              counter="12"
              outlined
            ></v-text-field>
          </validation-provider>

          <!-- <CategoryEdit></CategoryEdit> -->

          <validation-provider
            v-slot="{ errors }"
            rules="required"
            name="프로필 정보 변경 동의"
          >
            <v-checkbox
              v-model="checkbox"
              :error-messages="errors"
              value="1"
              label="프로필 편집에 동의합니다."
              type="checkbox"
              required
            ></v-checkbox>

          </validation-provider>

          <v-row class="d-flex justify-end">
            <router-link :to="{ name: 'UserPage', params: { userNickname: UserInfo.nickname }}">
              <v-btn 
                class="mr-4" 
                type="submit" 
                :disabled="invalid"
                @click="updataUserInfo"
                color="success">
                적용
              </v-btn>
            </router-link>

            <v-btn 
              @click="clear"
              color="success">
              지우기
            </v-btn>

            <v-btn 
              class="ml-4"
              color="success">
              <router-link to="/userPage">뒤로가기</router-link>
            </v-btn>
          </v-row>

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
// import CategoryEdit from "./components/CategoryEdit.vue";
import ImgEdit from "./components/ImgEdit.vue";
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

// extend("originPassword", {
//   message: "문자, 숫자, 특수 문자 포함 8자리 이상 12자리 이하로 입력해주세요",
//   validate: value => {
//     return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value);
//   }
// });

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
    // CategoryEdit,
    ImgEdit,
  },
  props: {
    userId: Number,
  },
  data: function() {
    return {
      UserInfo: [],
      newPassword: "",
      passwordConfirmation: "",
      showPass: false,
      checkbox: null
    };
  },
  methods: {
    originUserInfo: function() {
      const userId = this.$store.state.UserStore.user.user_id 
      axios.get(`http://localhost:8080/userPage/${userId}`)
        .then((res) => {
          this.UserInfo = res.data
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    updataUserInfo: function() {
      const userId = this.$store.state.UserStore.user.user_id 
      const ChangedUserInfo = {
        email: this.UserInfo.email,
        nickname: this.UserInfo.nickname,
        password: this.UserInfo.password,
        phone: this.UserInfo.phone,
        id: userId,
        newPassword: this.newPassword,
        // img: this.UserInfo.img,
        // introduce: this.UserInfo.introduce,
      }
      // const nickname = this.$store.state.UserStore.user.nickname 
      axios.post("http://localhost:8080/userPage/changePassword", ChangedUserInfo)
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
    }
  },
  created() {
    this.originUserInfo()
  }
};
</script>

<style lang="scss" scoped>

a {text-decoration: none;}

.container-size {
  width: 30%;
}

</style>
