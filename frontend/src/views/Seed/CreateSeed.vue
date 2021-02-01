<template>
  <v-container class="container-size">
    <SeedThumbnail></SeedThumbnail>
    <v-row>
      <SeedCategory></SeedCategory>
      <SeedPeople></SeedPeople>
    </v-row>
    <SeedTitle></SeedTitle>
    <SeedContent></SeedContent>
    <SeedCertificationImg></SeedCertificationImg>
    <SeedDate></SeedDate>
    <SeedButton></SeedButton>
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
