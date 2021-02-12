<template>
  <v-container>
    
    <v-row>
      <SeedThumbnail></SeedThumbnail>
    </v-row>

    <v-row>
      <v-col>

      <v-tabs
        v-model="tab"
        color="success"
        right
      >
        <v-tab
          v-for="item in items"
          :key="item"
          @click="CheckisBasicInfo(item)"
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
              <!-- <SeedCertification></SeedCertification> -->

              <v-row>
                <v-col
                  v-for="(card, $idx) in cards"
                  :key="$idx"
                  class="d-flex child-flex"
                  cols="4"
                >
                <v-img
                  :src="card.img"
                  :lazy-src="card.img"
                  aspect-ratio="1"
                  class="grey lighten-2 cursor_test"
                >
                  <template v-slot:placeholder>
                    <v-row
                      class="fill-height ma-0"
                      align="center"
                      justify="center"
                    >
                      <v-progress-circular
                        indeterminate
                        color="grey lighten-5"
                      ></v-progress-circular>
                    </v-row>
                  </template>
                </v-img>
              </v-col>
              
              </v-row>

            </v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
      </v-col>
    </v-row>
    <infinite-loading v-if="isBasicInfo === false" @infinite="infiniteHandler" spinner="waveDots">
      <div slot="no-more">
        데이터가 없습니다
      </div>
    </infinite-loading> 
    </v-container>
</template>

<script>
import axios from "axios"
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
      seedId: this.$route.params.seedId,
      total: [],
      cards: [],
      isBasicInfo: true,
    }
  },
  methods: {
    getSeedCertification: function () {
      const seedId = this.seedId
      console.log(this.seedId)
      axios.get(`http://127.0.0.1:8080/sameChallengeCertification/${seedId}`)
        .then((res) => {
          this.total = res.data
          // console.log("인증:",res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    infiniteHandler($state) {
      setTimeout(() => {
        if (this.total.length) {
          // console.log("옮겨지기 전 cards: ", this.cards)
          // console.log("옮겨지기 전 total: ", this.total)
          this.cards.push(...this.total.splice(0, 3))
          console.log("옮겨진 후 cards: ", this.cards)
          // console.log("옮겨진 후 total: ", this.total)
          $state.loaded();
        } else {
          $state.complete();
        }
      }, 1000)
    },
    CheckisBasicInfo: function (item) {
      if (item === "씨앗 정보") {
        this.isBasicInfo = true
      } else {
        this.isBasicInfo = false
      }
      console.log(this.isBasicInfo)
    }
  },
  created() {
    this.getSeedCertification();
  }
}
</script>

<style lang="scss" scoped>


</style>