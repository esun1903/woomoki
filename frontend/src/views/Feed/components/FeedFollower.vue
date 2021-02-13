<template>
  <v-container>
    <ul class="flashcard-list">
      <li v-on:click="toggleCard(certification)" v-for="(certification, index) in certifications">
        <transition name="flip">
          <p v-bind:key="certification.flipped" class="certification">
              {{ card.flipped ? card.back : card.front }}
              <span v-on:click="cards.splice(index, 1)" class="delete-card">X</span>
          </p>
        </transition>
      </li>
    </ul>
  </v-container>
</template>

<script>
export default {
  name: "FeedFollower",
  components: {},
  directives: {},
  data() {
    return {
      certifications: []
    };
  },
  mounted() {},
  methods: {
  },
  created () {
    axios.get("http://localhost:8080/allCertification")
      .then((res) => {
        const certifications = res.data
        certifications.sort(function(a,b) {
          return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
        })
        this.certifications = certifications
      })
      .catch((err) => {
        console.log(err)
      })
  },
  computed: {}
};
</script>

<style lang="scss" scoped>
.img {
  max-width: 100%;
  height: auto;
}
.carousel-3d-container figure {
  margin:0;
}

.carousel-3d-container figcaption {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.5);
  color: #fff;
  bottom: 0;
  position: absolute;
  bottom: 0;
  padding: 15px;
  font-size: 12px;
  min-width: 100%;
  box-sizing: border-box;
  text-align: center;
  height: 30%;
}
</style>