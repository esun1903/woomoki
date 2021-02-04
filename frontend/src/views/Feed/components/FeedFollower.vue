<template>
  <carousel-3d @after-slide-change="onAfterSlideChange" @before-slide-change="onBeforeSlideChange" @last-slide="onLastSlide">
    <slide v-for="(slide, i) in slides" :index="i" :key="i">
      <img src="https://placehold.it/360x270">
    </slide>
  </carousel-3d>
</template>

<script>
export default {
  name: 'FeedFollower',
  components: {  },
  directives: {  },
  data() {
    return {
      slides: 7,    
    };
  },
  mounted() {
    
  },
  methods: {
    onAfterSlideChange(index) {
      console.log('@onAfterSlideChange Callback Triggered', 'Slide Index ' + index)
    },
    onBeforeSlideChange(index) {
      console.log('@onBeforeSlideChange Callback Triggered', 'Slide Index ' + index)
    },
    onLastSlide(index) {
      console.log('@onLastSlide Callback Triggered', 'Slide Index ' + index)
    }
  },
};
</script>

<style lang="scss" scoped>

</style>