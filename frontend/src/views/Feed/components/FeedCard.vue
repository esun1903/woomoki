<template>
  <splide-slide>
    <div class="image-box">
      <img :src=CertificationImg>
    </div>
    <div class="mask">
      내용 보이게
    </div>
  </splide-slide>
</template>

<script>
export default {
  name: 'FeedCard',
  components: {  },
  directives: {  },
  props: {
    slide: Object,
  },
  data() {
    return {
      
    };
  },
  mounted() {
    
  },
  methods: {
    
  },
  computed: {
    CertificationImg: function () {
      return this.slide.img 
    },
  }
};
</script>

<style lang="scss" scoped>
// .splide__slide {
//   vertical-align: middle;
  .image-box {
    display:flex;
    justify-content: center;
    img {
    width: 250px;
    height: 300px;
    object-fit: cover;
      padding: 0 5%;
      margin: 0;
      &:hover + .mask{
        display: block;
        color: red;
      }
    }
  }
  .mask {
    display: none;
  }
// 
</style>