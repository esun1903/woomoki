<template>

  <v-container class="container-size">
    <span class="feed-title">팔로우 유저 피드</span>
      <v-row>
        <v-col
          v-for="(card, $idx) in Followslides"
          :key="$idx"
          class="d-flex child-flex"
          cols="4"
        >
          <v-img
            @click="detailCertification(card)"
            :src="card.img"
            :lazy-src="card.img"
            aspect-ratio="1"
            class="grey lighten-2 cursor_img"
          >
          <template v-slot:placeholder>
            <v-row
              class="fill-height ma-0"
              align="center"
              justify="center"
            >
              <v-progress-circular
                indeterminate
                color="grey lighten-5"
              ></v-progress-circular>
            </v-row>
          </template>
          </v-img>
        </v-col>
      </v-row>             
      <infinite-loading @infinite="FollowinfiniteHandler" spinner="waveDots">
        <div class="infinite-margin" slot="no-more">
          "보살핌 후기가 더 이상 없습니다"
        </div>
        <div class="infinite-margin" slot="no-results">
          "보살핌 후기가 없습니다 씨앗에 물을 줘보세요"
        </div>
      </infinite-loading>
      
    </v-container>

<!-- 
  <v-container class="container-size">
    <div class="feed-title">팔로우 유저 피드</div>
                  <v-row>
                    <v-col
                      v-for="(card, $idx) in Followslides"
                      :key="$idx"
                      class="d-flex child-flex"
                      cols="4"
                      >

                        <v-img
                          :src="card.img"
                          :lazy-src="card.img"
                          aspect-ratio="1"
                          class="grey lighten-2 cursor_test"
                        >
                      <template v-slot:placeholder>
                        <v-row
                          class="fill-height ma-0"
                          align="center"
                          justify="center"
                        >
                          <v-progress-circular
                            indeterminate
                            color="grey lighten-5"
                          ></v-progress-circular>
                        </v-row>
                      </template>
                      </v-img>

                    </v-col>
  
      <infinite-loading @infinite="FollowinfiniteHandler" spinner="waveDots">
        <div class="infinite-margin" slot="no-more">
          "보살핌 후기가 더 이상 없습니다"
        </div>
        <div class="infinite-margin" slot="no-results">
          "보살핌 후기가 없습니다 씨앗에 물을 줘보세요"
        </div>
      </infinite-loading> -->
      
    </v-container>
</template>

<script>
import axios from "axios"
import {mapState} from "vuex";
// import FeedAll from "@/views/Feed/components/FeedAll.vue"
// import FeedFollower from "@/views/Feed/components/FeedFollower.vue"

export default {
  name: 'FeedFollowUser',
  components: { 
    // FeedAll, 
    // FeedFollower
   },
  directives: { 
    
   },
  data: function () {
    return {
      Followslides: [],
      Followtotal: [],
    };
  },
  created () {

    // 팔로우 피드
    const userId_num = this.user.user_id
    const userId = {};
    userId["userid"] = userId_num;
    axios.get(`http://127.0.0.1:8080/feed/follower/${userId_num}`, userId)
      .then((res) => {
        const certifications = res.data
        certifications.sort(function(a,b) {
          return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
        })
        console.log("res.data",res.data)
        this.Followtotal = certifications
        console.log('Followtotal',this.Followtotal)
        console.log(this.Followtotal)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  methods: {
    FollowinfiniteHandler($state) {
      setTimeout(() => {
        if (this.Followtotal.length) {
          // console.log("옮겨지기 전 cards: ", this.cards)
          // console.log("옮겨지기 전 total: ", this.total)
          this.Followslides.push(...this.Followtotal.splice(0, 3))
          // console.log("옮겨진 후 cards: ", this.cards)
          // console.log("옮겨진 후 total: ", this.total)
          $state.loaded();
        } else {
          $state.complete();
        }
        }, 500)
    },
    detailCertification: function (val) {
      axios.get(`http://127.0.0.1:8080/detailChallenge/${val.cng_id}`)      
        .then((res) => {
          console.log("카드 정보")
          console.log(val)
          console.log("챌린지디테일 불러오기 성공")
          console.log(res.data)
          const ChallengeUserId = res.data.user_id
          this.$router.push({
            name: 'CertificationDetail',
            params: {
              cngUserId: ChallengeUserId,
              cngId: val.cng_id,
              certId: val.id,
            }
          });
        })
        .catch((err) => {
          console.log("에러는 노노 ")
          console.log(err)
        })
    },
  },
  computed: {
    ...mapState('UserStore', ['user']),
  }
};
</script>

<style lang="scss" scoped>
.container {
  width: 80%;
  margin-top: 7%;
}

.feed-title {
  font-size: 2rem;
  margin-bottom: 5vh;
  display: flex;
  justify-content: center;
}

.infinite-margin {
   margin-top: 5vh;
}

.cursor_img {
  cursor: pointer;
}

</style>