<template>
  <v-container>
    
    <v-row>
      <SeedThumbnail></SeedThumbnail>
    </v-row>

    <v-row>

      <v-tabs
        v-model="tab"
        color="success"
        right
      >
        <v-tab
          v-for="item in items"
          :key="item"
          >
          {{ item }}
        </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tab">
        <v-tab-item
          v-for="item in items"
          :key="item"
          >
          <v-card width="100vw">
            <v-card-text class="d-flex justify-center" v-if="item === '씨앗 정보'">
              <SeedBasicInfo></SeedBasicInfo>
            </v-card-text>
            <v-card-text class="d-flex justify-center" v-if="item === '보살핌 후기'">
              <SeedCertification></SeedCertification>
            </v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs-items>

    </v-row>
    </v-container>
</template>

<script>
import SeedThumbnail from "./components/SeedThumbnail"
import SeedBasicInfo from "./components/SeedBasicInfo"
import SeedCertification from "./components/SeedCertification"

export default {
  name: "SeedDetail",
  components: {
    SeedThumbnail,
    SeedBasicInfo,
    SeedCertification
  },
  data: function () {
    return {
      tab: null,
      items: ["씨앗 정보", "보살핌 후기"],
    }
  }
}
</script>

<style lang="scss" scoped>


</style>