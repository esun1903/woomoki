<template>
  <v-container class="container-size">
    <v-stepper v-model="e1" non-linear>
      <v-stepper-header>
        <v-stepper-step
          :complete="e1 > 1"
          editable
          step="1"
        >
          씨앗 종류
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          :complete="e1 > 2"
          editable
          step="2"
        >
          씨앗 이름
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          editable
          step="3"
        >
          기타 등등
        </v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          
          <SeedCategory class="pt-2"></SeedCategory>

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
          <SeedThumbnail class="pt-2"></SeedThumbnail>
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
          <SeedPeople class="pt-2"></SeedPeople>
          <SeedCertificationCount></SeedCertificationCount>
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
import SeedCertificationCount from "./components/SeedCertificationCount.vue"

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
    SeedCertificationCount,
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
  width: 50%;
}

</style>
