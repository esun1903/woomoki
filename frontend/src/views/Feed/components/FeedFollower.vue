<template>
  <v-container>
    
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
        this.certifications = certifications
        })
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