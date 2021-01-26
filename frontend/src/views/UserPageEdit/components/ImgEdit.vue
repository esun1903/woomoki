<template>
  <div>
    <v-avatar 
      color="success"
      width="300"
      height="300"
      >

      <v-btn>

      <v-file-input
        
        append-icon=""
        hide-input
        truncate-length="20"
      >
      </v-file-input>

      </v-btn>
    </v-avatar>
  </div>
</template>

<script>
export default {
  name: "ImgEdit",
  data: function () {
      return {
        imgSrc: new File("img.png")
      }
  },
  methods: {
  }
};
</script>

<style></style>
