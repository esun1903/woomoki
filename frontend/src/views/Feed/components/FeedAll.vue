<template>
  <div>
    <splide :options="options" :slides="slides" has-slider-wrpper>
      <splide-slide v-for="(slide, index) in slides" :key="index">
        <FeedCard :slide="slide" class="image-card" />
      </splide-slide>
      <template v-slot:controls>
        <div class="splide__progress">
          <div class="splide__progress__bar">
          </div>
        </div>

        <div class="splide__autoplay">
          <button class="splide__play">
            <v-icon>far fa-play-circle</v-icon>
          </button>
          <button class="splide__pause">
            <v-icon>far fa-pause-circle</v-icon>
          </button>
        </div>
      </template>
    </splide>
  </div>
</template>

<script>
import FeedCard from "@/views/Feed/components/FeedCard.vue"
import { Splide, SplideSlide } from '@splidejs/vue-splide';
import '@splidejs/splide/dist/css/themes/splide-sea-green.min.css';
import axios from "axios";

export default {
  name: 'FeedAll',
  components: { FeedCard, Splide, SplideSlide },
  directives: {  },
  data() {
    return {
      slides: [],

      options: {
        rewind: true,
        perPage: 3,
        // padding: {
        //   left: 0,
        //   right: 0
        // },
        width: '100%',
        autoplay: true,
        pauseOnHover: false,
        arrows : 'slider',
        type: 'loop',
        focus: 'center',
        easing: 'ease',
        cover  : true,
      },

  }
  },
  mounted() {

  },
  methods: {

  },
  created () {
    axios.get("http://127.0.0.1:8080/allCertification")
      .then((res) => {
        const certifications = res.data
        certifications.sort(function(a,b) {
          return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
        })
        this.slides = certifications
        console.log(this.slides)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  computed: {
  }
};
</script>

<style lang="scss" scoped>
$color: #20b2aa;
.splide {
  width: 150%;
  padding: 5%;
  &__autoplay {
    margin-top: 1.5em;
    margin-bottom: 1.5em;
    text-align: center;
  }
  &__play, &__pause {
    color: $color;
    transition: color .2s linear;
    cursor: pointer;
    padding: .3em 1em;
    .v-icon{
      font-size: 2rem;
      margin-top: 10%;
      margin-bottom:20%;
      &:hover {
        color: darken( $color, 20% );
      }
    }
  }
}
.image-card {
  line-height: 120%;
}
</style>