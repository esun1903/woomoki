<template>
  <!-- <v-row class="d-flex justify-center"> -->
    <v-avatar
          tile
          :class="`rounded-lg`"
          width="100%"
          height="450px"
          max-width="100%"
          max-height="100%"
          color="grey lighten-3"
          class="cursor-img"
          >
          <v-img
            class="img"
            :src="imageUrl"
          >
            <div class="content">
              <h1>{{ SeedInfo.title }}</h1>
              <br>
              <div v-if="SeedInfo.category_id === 1">건강</div>
              <div v-if="SeedInfo.category_id === 2">생활습관</div>
              <div v-if="SeedInfo.category_id === 3">독서</div>
              <div v-if="SeedInfo.category_id === 4">자산</div>
              <div v-if="SeedInfo.category_id === 5">자기계발</div>
              <div v-if="SeedInfo.category_id === 6">취미</div>
            </div>
            <div class="img-cover"></div>
          </v-img>
    </v-avatar>
  <!-- </v-row> -->
</template>

<script>
import axios from "axios"

export default {
  name: "SeedThumbnail",
  data: function () {
    return {
      SeedInfo: [],
      titme: "",
      summary: "",
      imageUrl: "https://lh3.googleusercontent.com/proxy/gcelZf3h5JPsSdZCaCj38iN8ZHcLTKsWUZann9BfsPARcn2L_SB7LHQex6NCR592vRCvQWUvpNwHemE91u4MKx_a0TVNvh_y8E_2HZSXx4KG85i5f9VcUTp1ZEZFosnG87fYica8",
    }
  },
  methods: {
    async getSeedThumbnail () {
      const seedId = 5
      const SeedInfo = await axios.get(`http://127.0.0.1:8080/detailChallenge/${seedId}`)
      this.SeedInfo = SeedInfo.data 
      //카테고리 이름을 불러와야함
      // const CategoryName = await axios.get(`???`)
    }
  },
  created() {
    this.getSeedThumbnail();
  }
}
</script>

<style lang="scss" scoped>

.img{
    position: relative;
    background-image: url("https://t1.daumcdn.net/cfile/tistory/995A17455A409C9A28");                                                               
    height: 100vh;
    background-size: cover;
}

.img-cover{
   position: absolute;
   height: 100%;
   width: 100%;
   background-color: rgba(0, 0, 0, 0.4);                                                                 
   z-index:1;
}

.img .content{
     position: absolute;
     top:50%;
     left:50%;
     transform: translate(-50%, -50%);                                                                   
     font-size:1rem;
     color: white;
     z-index: 2;
     text-align: center;
}

</style>