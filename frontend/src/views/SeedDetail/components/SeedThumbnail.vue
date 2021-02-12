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
              <v-chip :color=this.color class="white--text mb-2">{{this.category}}</v-chip>
              <v-row>
                <v-col>
                  <div>참여: / {{ this.SeedInfo.max_people }}</div>
                </v-col>
                <v-col>
                  <div>인증률: </div>
                </v-col>
              </v-row>
              <v-row class="d-flex justify-center mb-2">
                <div>
                  진행률
                </div>
              </v-row>
              <v-row>
                <v-progress-linear
                  v-if="this.isEnd === false"
                  :value="percentage"
                  :color="color"
                  height="25"
                  rounded
                  striped
                >
                  <template>
                    <strong class="white--text">{{ percentage }}%</strong>
                  </template>
                </v-progress-linear>
          
                <v-progress-linear
                  v-else
                  value="100"
                  :color="color"
                  height="25"
                  rounded
                  striped
                >
                  <template v-if="this.isEnd === false">
                    <strong>100%</strong>
                  </template>
                  <template v-else>
                    <strong class="white--text">이미 부화한 씨앗입니다</strong>
                  </template>
                </v-progress-linear>
              </v-row>
        
            </div>
            <!-- <v-btn v-if="isMySeed === false" class="star-position" icon @click="getScrap">
              <v-icon size="48" :color="scrapped ? 'yellow' : 'white' ">fas fa-star</v-icon>
            </v-btn> -->
            <v-btn v-if="isMySeed === false" class="star-position" icon @click="getScrap">
              <v-icon size="48" :color="scrapped ? 'red' : 'white' ">fas fa-heart</v-icon>
            </v-btn>
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
      scrapped: false,
      isMySeed: false,
      percentage: 20,
      isEnd: false,
    }
  },
  methods: {
    async getSeedThumbnail () {
      // 씨앗 정보 가져오기
      const seedId = this.seedId
      const SeedInfo = await axios.get(`http://127.0.0.1:8080/detailChallenge/${seedId}`)
      this.SeedInfo = SeedInfo.data 

      // 오늘 날짜
      const today = new Date();
      // // 시작 날짜 (월이 한달씩 밀려서 출력되는데 왜 그러지?.....)
      const start_year = this.SeedInfo.start_date.slice(0, 4)
      const startDate = new Date(Number(start_year), Number(this.SeedInfo.start_date.slice(5, 7))-1, Number(this.SeedInfo.start_date.slice(8, 10)));
      // // 끝나는 날짜 (월이 한달씩 밀려서 출력되는데 왜 그러지?.....)
      const end_year = this.SeedInfo.end_date.slice(0, 4)
      const endDate = new Date(Number(end_year), Number(this.SeedInfo.end_date.slice(5, 7))-1, Number(this.SeedInfo.end_date.slice(8, 10)));
      // 종료 날짜 - 오늘 날짜가 마이너스면 이미 종료된 씨앗
      if (endDate - today < 0) {
        this.isEnd = true
      } else {
        // 전체 기간 계산
      const deltaDate = Math.floor((endDate - startDate) / (1000*60*60*24))
      // 시작부터 오늘까지 날짜 계산
      const progressDate = Math.floor((today - startDate) / (1000*60*60*24))
      console.log(deltaDate, progressDate)
      // 퍼센트로 환산
      const percentage = (progressDate / deltaDate) * 100
      this.percentage = Math.round(percentage)
      }

      // 내가 만든 씨앗인지 구분
      const SeedUserId = this.$store.state.UserStore.user.user_id 
      const UserId = this.SeedInfo.user_id
      if (SeedUserId === UserId) {
        this.isMySeed = true;
      }
    },
    // 스크랩(좋아요) 하기
    getScrap () {
      // 클릭 때 마다 scrapped가 토글
      this.scrapped = !this.scrapped
      const seedId = this.seedId
      const userId = this.$store.state.UserStore.user.user_id
      // 스크랩이 되어있지 않을 때 스크랩
      if (this.scrapped) {
        axios.get(`http://127.0.0.1:8080/userPage/favChallenge/${userId}/${seedId}`)
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
      } else {
        // 스크랩 되어 있을 때 스크랩 취소
        axios.get(`http://127.0.0.1:8080/userPage/DeletefavChallenge/${userId}/${seedId}`)
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
      }
    },
    CheckisfavSeed: function () {
      const seedId = this.seedId
      const userId = this.$store.state.UserStore.user.user_id
      axios.get(`http://127.0.0.1:8080/userPage/ListfavChallenge/${userId}`)
      .then((res) => {
          console.log(res)
          const SeedList = res.data
          var i;
          for (i=0; i < SeedList.length; i++) {
            if (SeedList[i].id === seedId) {
              this.scrapped = true
            }
          }
        })
        .catch((err) => {
          console.log(err)
        })
    }
  },
  created() {
    this.getSeedThumbnail();
    this.CheckisfavSeed();
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
    },
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
     width: 30%;
     top:50%;
     left:50%;
     transform: translate(-50%, -50%);                                                                   
     font-size:1rem;
     color: white;
     z-index: 2;
     text-align: center;
}

.star-position {
  z-index: 3;
  position: absolute;
  right: 30px;
  bottom: 165px;
}

</style>