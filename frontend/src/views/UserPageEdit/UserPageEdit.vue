<template>
    <v-container 
      style="width: 500px; margin-top: 150px">
      <h1 style="text-align: center; margin-bottom: 50px;">프로필 편집 페이지</h1>
      <v-app>
        <v-avatar
         size="128"
         color="success"
        >사진
        </v-avatar>
        <validation-observer
          ref="observer"
          v-slot="{ invalid }"
        >
          <form @submit.prevent="submit">

            <validation-provider
              v-slot="{ errors }"
              name="아이디"
              :rules="{
                required: true,
                min: 4,
                regex: /^[a-zA-z0-9가-힣]{4,10}$/
                }"
            >
              <v-text-field
                v-model="name"
                :counter="10"
                :error-messages="errors"
                label="아이디"
                required
              ></v-text-field>
            </validation-provider>

            <validation-provider
              v-slot="{ errors }"
              name="한 줄 소개"
            >
              <v-text-field
                v-model="introduce"
                :error-messages="errors"
                label="한 줄 소개"
                counter="20"
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
                v-model="phoneNumber"
                :counter="11"
                :error-messages="errors"
                label="핸드폰 번호(-없이 입력해주세요)"
                required
              ></v-text-field>
            </validation-provider>

            <validation-provider
              v-slot="{ errors }"
              name="기존 비밀번호"
              rules="required|newPassword"
            >
              <v-text-field
                v-model="originPassword"
                :error-messages="errors"
                label="기존 비밀번호 입력"
                :type="showPass ? 'text' : 'password'"
                counter="12"
                required
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
              ></v-text-field>
            </validation-provider>
            <CategoryTag style="width: 500px"></CategoryTag>
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
            
        
            <v-btn
              class="mr-4"
              type="submit"
              :disabled="invalid"
            >
              적용
            </v-btn>
            <v-btn 
              @click="clear"
              >
              지우기
            </v-btn>
            <v-btn
              class="ml-4"
            >
              뒤로가기
            </v-btn>
          </form>
        </validation-observer>
        </v-app>
    </v-container>
  
</template>

<script>
import { required, digits, max, regex, min } from 'vee-validate/dist/rules'
import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'
import CategoryTag from './components/CategoryTag.vue'

setInteractionMode('eager')

extend('min', {
  ...min,
  message: '{_field_}는 영문, 숫자 포함 4자 이상 10자 이하 입니다',
})

extend('digits', {
  ...digits,
  message: '{_value_}는(은) 올바른 전화번호가 아닙니다',
})

extend('required', {
  ...required,
  message: '{_field_}는(은) 필수항목입니다',
})

extend('max', {
  ...max,
  message: '최대 길이를 초과하였습니다',
})

extend('regex', {
  ...regex,
  message: '아이디는 영문, 숫자 포함 4자 이상 10자 이하 입니다',
})

extend( "originPassword", value => {
  if (value === this.savedPassword) {
    return true;
  }
  else {
    return '기존의 패스워드가 일치하지 않습니다.';
  }
});

extend( "newPassword", {
  message: "문자, 숫자, 특수 문자 포함 8자리 이상 12자리 이하로 입력해주세요",
  validate: value => {
    return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value)
  }
});

extend("passwordConfirm", {
  params: ['target'],
  validate(value, { target }) {
    return value === target;
  },
  message: '새로운 패스워드가 일치하지 않습니다.'
});

export default {
  name: "UserPageEdit",
  components: {
    ValidationProvider,
    ValidationObserver,
    CategoryTag,
  },
  data: function () {
    return {
      name: '',
      phoneNumber: '',
      introduce: '',
      originPassword: '',
      savedPassword: '1q2w3e4r!',
      newPassword: '',
      passwordConfirmation: '',
      showPass: false,
      checkbox: null,
    }
  },
  methods: {
      submit () {
        this.$refs.observer.validate()
      },
      clear () {
        this.name = ''
        this.phoneNumber = ''
        this.originPassword = '',
        this.newPassword = '',
        this.passwordConfirmation = '',
        this.checkbox = null
        this.$refs.observer.reset()
      },
    },
}
</script>

<style lang="scss">

.width-500 {
  width: 500px;
}

</style>