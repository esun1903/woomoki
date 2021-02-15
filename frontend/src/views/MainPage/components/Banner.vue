<template>
  <agile :nav-buttons="false" :autoplay-speed="3000" :speed="2500" fade pause-on-hover pause-on-dots-hover autoplay>
      <div class="slide" v-for="(bannerImg, index) in bannerImgs" :key="index">
        <img :src="require(`@/assets/images/banner/${bannerImg}.jpg`)">
      </div>
  </agile>
</template>

<script>
import { VueAgile } from 'vue-agile'
export default {
  name: 'Banner',
  components: { agile: VueAgile, },
  directives: {  },
  data() {
    return {
      bannerImgs : [
        "banner1",
        "banner2",
        "banner3",
        "banner4",
      ]
    };
  },
  mounted() {
    
  },
  methods: {
    
  },
};
</script>

<style lang="scss" scoped>
.agile{
  .agile__list{
    .agile__track{
      .agile__slides{
          .slide{
            display: block;
            object-fit: cover;
            margin-top:1%;
            img{
              width: 100%;
              height: auto;
            }
        }
      }
    }
  }
}
</style>