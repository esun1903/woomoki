<template>
  <v-container class="container-size">
    <v-stepper non-linear v-model="e1">
    <v-stepper-header>
      <v-stepper-step
        editable
        :complete="e1 > 1"
        step="1"
      >
        씨앗 종류
      </v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step
        editable
        :complete="e1 > 2"
        step="2"
      >
        씨앗 내용
      </v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step 
        step="3"
        editable
      >
        기타
      </v-stepper-step>
    </v-stepper-header>

    <v-stepper-items>
      <v-stepper-content step="1">
        <SeedCategory class="pt-2"></SeedCategory>

        <v-btn
          color="success"
          @click="e1 = 2"
        >
          다음
        </v-btn>

        <v-btn text>
          취소
        </v-btn>
      </v-stepper-content>

      <v-stepper-content step="2">
        <SeedThumbnail class="pt-2"></SeedThumbnail>
        <SeedTitle></SeedTitle>
        <SeedContent></SeedContent>
      
        <v-btn
          color="success"
          @click="e1 = 3"
        >
          다음
        </v-btn>

        <v-btn text>
          취소
        </v-btn>
      </v-stepper-content>

      <v-stepper-content step="3">
        <SeedPeople class="pt-2"></SeedPeople>
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
