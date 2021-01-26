<template>
  <div class="search-wrapper">
      <div class="input-holder">
          <input type="text" class="search-input" placeholder="Type to search" />
          <button class="search-icon" onclick="searchToggle(this, event);"><span></span></button>
      </div>
      <span class="close" onclick="searchToggle(this, event);"></span>
  </div>
</template>

<script>


export default {
  name: 'SearchBar',
  components: {  },
  directives: {  },
  data() {
    return {
      
    };
  },
  mounted() {
    
  },
  methods: {
    
  },
};
</script>

<style lang="scss" scoped>
.search-wrapper {
    position: absolute;
    transform: translate(-50%, -50%);
    top:50%;
    left:50%;
}
.search-wrapper.active {}

</style>