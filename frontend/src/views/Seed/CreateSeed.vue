<template>
  <v-container class="container-size">

    <v-stepper
      non-linear
      v-model="e1">
    <v-stepper-header>
      <v-stepper-step
        color="success"
        edit-icon="$complete"
        editable
        :complete="e1 > 1"
        step="1"
      >
        씨앗 종류
      </v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step
        color="success"
        edit-icon="$complete"
        editable
        :complete="e1 > 2"
        step="2"
      >
        씨앗 내용
      </v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step
        color="success"
        edit-icon="$complete"
        step="3"
        editable
      >
        기타
      </v-stepper-step>
    </v-stepper-header>

    <v-stepper-items>
      
      <v-stepper-content step="1">
        <SeedCategory 
          class="pt-3" 
          @transferLifestyle="receiveLifestyle" 
          @transferHealth="receiveHealth"
          @transferBook="receiveBook"
          @transferAssets="receiveAssets"
          @transferGrown="receiveGrown"
          @transferHobby="receiveHobby"
          ></SeedCategory>

        <v-row class="d-flex justify-end mb-5">
          <v-btn
            color="success"
            @click="e1 = 2"
            :disabled="isSubmitCategory === false"
          >
            다음
          </v-btn>

          <v-btn text>
            취소
          </v-btn>

        </v-row>
      </v-stepper-content>

      <v-stepper-content step="2">
        <SeedThumbnail @transferThumbnail="receiveThumbnail" class="pt-3 mb-5"></SeedThumbnail>
        <SeedTitle @transferTitle="receiveTitle"></SeedTitle>
        <SeedContent @transferContent="receiveContent"></SeedContent>

        <v-row class="d-flex justify-end mb-5">
          <v-btn
            color="success"
            @click="e1 = 3"
            :disabled="BasicInfo.isSubmitBasicInfo === false"
          >
            다음
          </v-btn>

          <v-btn text>
            취소
          </v-btn>

          <v-btn 
            text
            @click="e1 = 1"
          >
            뒤로가기
          </v-btn>
          
        </v-row>
      </v-stepper-content>

      <v-stepper-content step="3">
        <SeedCertificationImg @transferCertificationImg="receiveCertificationImg" class="pt-3 mb-5"></SeedCertificationImg>
        <SeedDate @transferDate="receiveDate"></SeedDate>
        <SeedPeople @transferPeople="receivePeople"></SeedPeople>
        <SeedDeposit @transferDeposit="receiveDeposit"></SeedDeposit>
        <SeedCheckbox @transferCheckbox="receiveCheckbox"></SeedCheckbox>
        <!-- <SeedButton></SeedButton>

        <v-row class="d-flex justify-end mb-5">
          <v-btn
            color="success"
            :disabled="isSubmitContent === false"
          >
            생성
          </v-btn>

          <v-btn text>
            취소
          </v-btn>

          <v-btn 
            text
            @click="e1 = 1"
          >
            뒤로가기
          </v-btn>
          
        </v-row> -->

        <div class="text-end">
          <v-bottom-sheet
            v-model="sheet"
            inset
          >
            <template v-slot:activator="{ on, attrs }">
            
              <v-btn
                color="success lighten-2"
                dark
                v-bind="attrs"
                v-on="on"
                :disabled="isSubmitTotalSeedData === false"
              >생성
              </v-btn>
            </template>
            <v-sheet
              class="text-center"
              height="200px"
            >
              <v-btn
                class="mt-6"
                text
                color="error"
                @click="sheet = !sheet"
              >
                닫기
              </v-btn>
              <div>
                <v-icon 
                  size="40" 
                  color="red"
                  class="mt-5 mb-5"
                >fas fa-exclamation-triangle</v-icon>
              </div>
              <h3 class="my-3">
                참여자가 있다면 중간에 씨앗을 없앨 수 없어요!
              </h3>
            </v-sheet>
          </v-bottom-sheet>
          <v-btn 
            class="ma-2"
            color="red lighten-3"
            text
            >
            <router-link to="/">취소</router-link>
          </v-btn>
        </div>


      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>

  </v-container>
</template>

<script>
import SeedThumbnail from "./components/SeedThumbnail.vue"
import SeedTitle from "./components/SeedTitle.vue"
import SeedContent from "./components/SeedContent.vue"
import SeedDate from "./components/SeedDate.vue"
// import SeedButton from "./components/SeedButton.vue"
import SeedCategory from "./components/SeedCategory.vue"
import SeedPeople from "./components/SeedPeople.vue"
import SeedDeposit from "./components/SeedDeposit.vue"
import SeedCertificationImg from "./components/SeedCertificationImg.vue"
import SeedCheckbox from "./components/SeedCheckbox.vue"

export default {
  name: 'CreateSeed',
  components: {
    SeedThumbnail,
    SeedTitle,
    SeedContent,
    SeedDate,
    // SeedButton,
    SeedCategory,
    SeedPeople,
    SeedDeposit,
    SeedCertificationImg,
    SeedCheckbox
  },
  data: function () {
    return {
      e1: 1,
      slides: 7,
      sheet: false,
      category: "",
      thumbnail: "",
      title: "",
      content: "",
      certificationImg: "",
      date: "",
      people: 0,
      joinDeposit: "",
      checkbox: false,
      isSubmitCategory: false,
      BasicInfo: {
        isSubmit: {
          isSubmitThumbnail: false,
          isSubmitTitle: false,
          isSubmitContent: false,
        },
        isSubmitBasicInfo: false,
      },
      EtcInfo: {
        isSubmit: {
          isCertificationImg: false,
          isDate: false,
          isPeople: false,
          isjoinDeposit: false,
          isCheckbox: false,
        },
        isSubmitEtc: false,
      },
      isSubmitTotalSeedData: false,  
    }
  },
  methods: {
    // 컴포넌트에서 데이터 받아오기
    receiveLifestyle: function (isSubmitCategory, lifestyle) {
      this.isSubmitCategory = isSubmitCategory
      this.category = lifestyle
      console.log(this.category)
    },
    receiveHealth: function (isSubmitCategory, health) {
      this.isSubmitCategory = isSubmitCategory
      this.category = health
      console.log(this.category)
    },
    receiveBook: function (isSubmitCategory, health) {
      this.isSubmitCategory = isSubmitCategory
      this.category = health
      console.log(this.category)
    },
    receiveAssets: function (isSubmitCategory, health) {
      this.isSubmitCategory = isSubmitCategory
      this.category = health
      console.log(this.category)
    },
    receiveGrown: function (isSubmitCategory, health) {
      this.isSubmitCategory = isSubmitCategory
      this.category = health
      console.log(this.category)
    },
    receiveHobby: function (isSubmitCategory, health) {
      this.isSubmitCategory = isSubmitCategory
      this.category = health
      console.log(this.category)
    },
    receiveThumbnail: function (thumbnail) {
      this.thumbnail = thumbnail
    },
    receiveTitle: function (title) {
      this.title = title
    },
    receiveContent: function (content) {
      this.content = content
    },
    receiveCertificationImg: function (certificationImg) {
      this.certificationImg = certificationImg
    },
    receiveDate: function (date) {
      this.date = date
    },
    receivePeople: function (people) {
      this.people = people
    },
    receiveDeposit: function (joinDeposit) {
      this.joinDeposit = joinDeposit
    },
    receiveCheckbox: function (checkbox) {
      this.checkbox = checkbox
    },
    // 검사
    checkFormBasicInfo: function () {
      // 씨앗 이름 검사
      if (this.title.length >= 5 && this.title.length <= 20) {
        this.BasicInfo.isSubmit.isSubmitTitle = true
      } else {
        this.BasicInfo.isSubmit.isSubmitTitle = false
      }
      // 씨앗 내용 검사
      if (this.content.length >= 10 && this.content.length <= 200) {
        this.BasicInfo.isSubmit.isSubmitContent = true
      } else {
        this.BasicInfo.isSubmit.isSubmitContent = false
      }
      // 씨앗 사진, 이름, 내용 중 하나라도 false면 isSubmitBasicInfo는 false
      Object.values(this.BasicInfo.isSubmit).map(v => {
        if (!v) { this.BasicInfo.isSubmitBasicInfo = false; }
        else {this.BasicInfo.isSubmitBasicInfo = true;}
      })
    },
    checkFormEtc: function () {
      // 예시 이미지 검사
      if (this.EtcInfo.certificationImg.length > 0) {
        this.EtcInfo.isSubmit.isCertificationImg = true
      } else {
        this.EtcInfo.isSubmit.isCertificationImg = false
      }

      // 날짜 검사
      if (this.EtcInfo.date.length > 0) {
        this.EtcInfo.isSubmit.isDate = true
      } else {
        this.EtcInfo.isSubmit.isDate = false
      }

      // 참여 인원
      if (this.EtcInfo.people > 0) {
        this.EtcInfo.isSubmit.isPeople = true
      } else {
        this.EtcInfo.isSubmit.isPeople = false
      
      // 참여 금액 검사
      if (/^[0-9]+$/.test(this.EtcInfo.joinDeposit)) {
        this.EtcInfo.isSubmit.isjoinDeposit = true
      } else {
        this.EtcInfo.isSubmit.isjoinDeposit = false
      }

      // 체크박스 검사
      if (this.EtcInfo.checkbox === true) {
        this.EtcInfo.isSubmit.isCheckbox = true
      } else {
        this.EtcInfo.isSubmit.isCheckbox = false
      }

      Object.values(this.EtcInfo.isSubmit).map(v => {
        if (!v) { this.EtcInfo.isSubmitEtc = false; }
        else {this.EtcInfo.isSubmitEtc = true;}
      })
    }}
  },
  watch: {
    title: function() {
      this.checkFormBasicInfo();
    },
    content: function() {
      this.checkFormBasicInfo();
    },
    certificationImg: function() {
      this.checkFormEtc();
    },
    date: function() {
      this.checkFormEtc();
    },
    people: function() {
      this.checkFormEtc();
    },
    joinDeposit: function() {
      this.checkFormEtc();
    },
    checkbox: function() {
      this.checkFormEtc();
    },
  }
}
</script>

<style scoped>

a {
  text-decoration: none;
}

.container-size {
  width: 50%;
}

</style>
