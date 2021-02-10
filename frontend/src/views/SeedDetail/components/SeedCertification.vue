<template>
  <v-row>
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
  </v-row>
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
      cards: [],
    }
  },
  methods: {
    getSeedCertification: function () {
      const seedId = this.seedId
      console.log(this.seedId)
      axios.get(`http://127.0.0.1:8080/sameChallengeCertification/${seedId}`)
        .then((res) => {
          this.cards = res.data
          console.log("인증:",res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
  created() {
    this.getSeedCertification();
  }
}
</script>

<style lang="scss" scoped>

</style>