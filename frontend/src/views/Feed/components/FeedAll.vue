<template>
  <div>
    <splide :options="options" has-slider-wrapper>
      <splide-slide v-for="(certification, index) in certifications" :key="index">
        <img :src="certification.img">
        <div class="mask">
          내용 보이게
        </div>
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
import { Splide, SplideSlide } from '@splidejs/vue-splide';
// import { createSlides } from "./slides.js"
import '@splidejs/splide/dist/css/themes/splide-sea-green.min.css';

// const IMAGE_URL = 'https://source.unsplash.com/random/800x450';

export default {
  name: 'FeedAll',
  components: { Splide, SplideSlide },
  directives: {  },
  data() {
    return {
      certifications: [],

      options: {
        rewind: true,
        perPage: 2,
        gap: '2rem',
        padding: {
          left: 0,
          right: 0
        },
        autoplay: true,
        pauseOnHover: false,
        arrows : 'slider',
        type: 'loop',
        focus: 'center',
        height: '40%',
        width: '100%',
        easing: 'ease',
        cover  : true,
      },
      slides: createSlides(),

  }
  },
  mounted() {

  },
  methods: {
    // createSlides: function ( length = 10, sig = 0 ) {
    //   return Array.apply( null, Array( length ) ).map( ( value, index ) => {
    //     index = sig || index;

    //     return {
    //       src: IMAGE_URL,
    //       alt: '33',
    //     };
    //   });
    // }
  },
  created () {
    axios.get("http://localhost:8080/allCertification")
      .then((res) => {
        const certifications = res.data
        certifications.sort(function(a,b) {
          return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
        })
        console.log(certifications)
        this.certifications = certifications
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
  padding: 1.5%;
  &__autoplay {
    margin-top: 1em;
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
img {
  &:hover + .mask{
    display: block;
    color: red;
  }
}
.mask {
  display: none;
}

</style>