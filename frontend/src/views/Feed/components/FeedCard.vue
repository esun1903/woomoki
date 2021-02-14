<template>
  <splide-slide>
    <div class="image-box">
      <img :src=CertificationImg>
      <div class="mask">
        <div class="text">보이나??</div>
      </div>
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
.image-box {
  display:flex;
  justify-content: center;
  img {
  width: 320px;
  height: 300px;
  object-fit: cover;
  padding: 0 1%;
  margin: 0;

  display: block;
  transition: .5s ease;
  backface-visibility: hidden;
    &:hover {
      opacity: 0.8;
    }
    // &:hover + .mask{
    //   display: block;
    //   color: red;
    // }
  }
  .mask {
    transition: .5s ease;
    opacity: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    text-align: center;
    &:hover {
      opacity: 1;
    }
    .text {
      background-color: white;
      color: black;
      font-size: 16px;
      padding: 16px 32px;
    }
  }
}
.container:hover .image {
  opacity: 0.3;
}

.container:hover .middle {
  opacity: 1;
}
  // .mask {
  //   display: none;
  // }
// 
</style>