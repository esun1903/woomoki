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
        <SeedThumbnail class="pt-3 mb-5"></SeedThumbnail>
        <SeedTitle></SeedTitle>
        <SeedContent></SeedContent>

        <v-row class="d-flex justify-end mb-5">
          <v-btn
            color="success"
            @click="e1 = 3"
            :disabled="isSubmitContent === false"
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
        <SeedCertificationImg class="pt-3 mb-5"></SeedCertificationImg>
        <SeedDate></SeedDate>
        <SeedPeople></SeedPeople>
        <SeedCheckbox></SeedCheckbox>
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
                :disabled="isSubmitContent === true"
                color="success lighten-2"
                dark
                v-bind="attrs"
                v-on="on"
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
    SeedCertificationImg,
    SeedCheckbox
  },
  data: function () {
    return {
      e1: 1,
      slides: 7,
      sheet: false,
      category: "",
      isSubmitCategory: false,
      isSubmitContent: false,
      isSubmitEtc: false,
    }
  },
  methods: {
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
  },
  computed: {
  }
}
</script>

<style scoped>

a {
  text-decoration: none;
}

.container-size {
  width: 40%;
}

</style>
