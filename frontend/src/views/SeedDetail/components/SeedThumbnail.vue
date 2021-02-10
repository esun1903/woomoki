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
            :src="this.SeedInfo.sum_img"
          >
            <div class="content">
              <h1>{{ SeedInfo.title }}</h1>
              <br>
              <v-chip :color=this.color class="white--text">{{this.category}}</v-chip>
              <v-row>
                <v-col>
                  <div>참여</div>
                </v-col>
                <v-col>
                  <div>인증률: </div>
                </v-col>
              </v-row>
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
      seedId: this.$route.params.seedId,
      SeedInfo: [],
      titme: "",
      summary: "",
    }
  },
  methods: {
    async getSeedThumbnail () {
      const seedId = this.seedId
      const SeedInfo = await axios.get(`http://127.0.0.1:8080/detailChallenge/${seedId}`)
      this.SeedInfo = SeedInfo.data 
      }
  },
  created() {
    this.getSeedThumbnail();
  },
  computed: {
    category: function () {
      if (this.SeedInfo.category_id === 1) {
        return '건강'
      } else if (this.SeedInfo.category_id === 2) {
        return '생활습관'
      } else if (this.SeedInfo.category_id === 3) {
        return '독서'
      } else if (this.SeedInfo.category_id === 4) {
        return '자산'
      } else if (this.SeedInfo.category_id === 5) {
        return '자기계발'
      } else {
        return '취미'
      }
    },
    color: function () {
      if (this.SeedInfo.category_id === 1) {
        return 'light-green lighten-1'
      } else if (this.SeedInfo.category_id === 2) {
        return 'orange lighten-1'
      } else if (this.SeedInfo.category_id === 3) {
        return 'teal lighten-1'
      } else if (this.SeedInfo.category_id === 4) {
        return 'indigo lighten-1'
      } else if (this.SeedInfo.category_id === 5) {
        return 'purple lighten-1'
      } else {
        return 'pink lighten-1'
      }
    }
  }
}
</script>

<style lang="scss" scoped>

.img{
    position: relative;                                                             
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