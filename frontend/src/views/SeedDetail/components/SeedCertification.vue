<template>
  <!-- <v-row>
    <v-col
      v-for="(card, idx) in cards"
      :key="idx"
      class="d-flex child-flex"
      cols="4"
    >
      <v-img
        :src="card.img"
        :lazy-src="card.img"
        aspect-ratio="1"
        class="grey lighten-2"
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
  </v-row> -->

  <div>
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

    <infinite-loading @infinite="infiniteHandler" spinner="waveDots">
      <div slot="no-more">
        데이터가 없습니다
      </div>
    </infinite-loading> 
  </div>
</template>

<script>
import axios from "axios"

export default {
  name: "SeedCertification",
  components: {

  },
  data: function () {
    return {
      seedId: this.$route.params.seedId,
      total: [],
      cards: [],
    }
  },
  methods: {
    getSeedCertification: function () {
      const seedId = this.seedId
      console.log(this.seedId)
      axios.get(`http://127.0.0.1:8080/sameChallengeCertification/${seedId}`)
        .then((res) => {
          this.total = res.data
          console.log("인증:",res.data)
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
      }
  },
  created() {
    this.getSeedCertification();
  }
}
</script>

<style lang="scss" scoped>

</style>