<template>
  <div>
    {{ $route.query.id }}
  </div>
</template>

<script>
export default {
  name: 'SearchedPage',
  components: {  },
  directives: {  },
  data() {
    return {
      searched: this.$route.query.id
    };
  },
  mounted() {
    
  },
  methods: {
    
  },
  created () {

  }
};
</script>

<style lang="scss" scoped>

</style>