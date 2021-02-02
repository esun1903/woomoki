<template>
  <v-container class="container-size">
    <v-stepper v-model="e1" non-linear>
      <v-stepper-header>
        <v-stepper-step
          :rules="[() => false]"
          :complete="e1 > 1"
          editable
          step="1"
        >
          카테고리를 선택해주세요
          <small>Alert message</small>
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          :rules="[() => false]"
          :complete="e1 > 2"
          editable
          step="2"
        >
          썸네일 이미지와 제목 내용
          <small>Alert message</small>
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          :rules="[() => false]"
          editable
          step="3"
        >
          기타 등등
          <small>Alert message</small>
        </v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          
          <SeedCategory></SeedCategory>

          <v-btn
            color="primary"
            @click="e1 = 2"
          >
            Continue
          </v-btn>

          <v-btn text>
            Cancel
          </v-btn>
        </v-stepper-content>

        <v-stepper-content step="2">
          <SeedThumbnail></SeedThumbnail>
          <SeedTitle></SeedTitle>
          <SeedContent></SeedContent>

          <v-btn
            color="primary"
            @click="e1 = 3"
          >
            Continue
          </v-btn>

          <v-btn text>
            Cancel
          </v-btn>
        </v-stepper-content>

        <v-stepper-content step="3">
          <SeedPeople></SeedPeople>
          <SeedCertificationImg></SeedCertificationImg>
          <SeedDate></SeedDate>
          <SeedButton></SeedButton>

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
import SeedButton from "./components/SeedButton.vue"
import SeedCategory from "./components/SeedCategory.vue"
import SeedPeople from "./components/SeedPeople.vue"
import SeedCertificationImg from "./components/SeedCertificationImg.vue"

export default {
  name: 'CreateSeed',
  components: {
    SeedThumbnail,
    SeedTitle,
    SeedContent,
    SeedDate,
    SeedButton,
    SeedCategory,
    SeedPeople,
    SeedCertificationImg,
  },
  data: function () {
    return {
      joinDeposit: "",
      e1: 1,
    }
  },
  methods: {
    
  },
  
  watch: {
  }
}
</script>

<style scoped>

.container-size {
  width: 30%;
}

</style>
