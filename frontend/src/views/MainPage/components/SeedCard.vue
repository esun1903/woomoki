<template>
  <v-card>
    <v-img :src=SeedImg @click="goSeedDetail(seed.id)" aspect-ratio="1.5" class="cursor"></v-img>

    <div class="seed-card-top">
      <v-chip id="category-chip" :ripple="false" :color=this.color class="white--text"> {{ this.category }}</v-chip>
      <div class="btns">
        <v-btn icon @click.native="getLikes">
          <v-icon :color="liked ? 'red' : '' ">mdi-heart</v-icon>
        </v-btn>
        <v-btn icon @click.native="getScrap">
          <v-icon :color="scrapped ? '#FFC400' : '' ">fas fa-bookmark</v-icon>
        </v-btn>      
      </div>
    </div>

    <div>
      <v-tooltip bottom> 
        <template v-slot:activator="{ on }">
          <v-card-title v-on="on">
            {{seed.title}}
          </v-card-title>
        </template>
        <span class="full-title">{{seed.title}}</span>
      </v-tooltip>
    </div>
    <div class="seed-card-bottom">
      <div>
        <v-chip label :ripple="false">
          {{ seed.week }}주
        </v-chip>
        <v-chip label :ripple="false">
          주 {{ seed.day }}회
        </v-chip>
      </div>
    </div>
  </v-card>
</template>


<script>
import axios from "axios";
import {mapState} from "vuex";
export default {
  name: 'SeedCard',
  components: {  },
  directives: {  },
  props: {
    seed: Object, 
  },
  data() {
    return {
      liked: false,
      scrapped: false,
    };
  },
  mounted() {

  },
  methods: {
    // 해당 게시글 아이디 담아줘야해
    goSeedDetail: function (val) {
      this.$router.push({ name: 'SeedDetail', params: { seedId: val } })
    },
    getLikes: function () {
      const likeInfo = {};
      const userId_num = this.user.user_id;
      const seedId_num = this.seed.id;
      likeInfo["userId"] = userId_num;
      likeInfo["cngId"] = seedId_num;
      if (this.liked) {
        axios.put(`http://127.0.0.1:8080/likeDownChallenge/${userId_num}/${seedId_num}`, likeInfo )
        this.liked = false
        
      } else {
        axios.put(`http://127.0.0.1:8080/likeUpChallenge/${userId_num}/${seedId_num}`, likeInfo )
        this.liked = true
      }
    },
    getScrap: function () {
      const userId_num = this.user.user_id;
      const seedId_num = this.seed.id;
      if (this.scrapped) {      
        axios.get(`http://127.0.0.1:8080/userPage/DeletefavChallenge/${userId_num}/${seedId_num}`)
        .then(() => {
          console.log('스크랩취소성공')
        })
        .catch((err) => {
          console.log(err)
          console.log('스크랩취소실패')
        })
        this.scrapped = false  
      } else {
        axios.get(`http://127.0.0.1:8080/userPage/favChallenge/${userId_num}/${seedId_num}`)
        .then(() => {
          console.log('스크랩성공')
        })
        .catch((err) => {
          console.log(err)
          console.log('스크랩실패')
        })
        this.scrapped = true
      }
    },
    CheckisScrapped: function () {
      const userId_num = this.user.user_id;
      const seedId_num = this.seed.id;
      axios.get(`http://127.0.0.1:8080/userPage/LikeAndfavChallenge/${userId_num}`)
      .then((res) => {
        console.log(res)
        const seedList = res.data
        var i;
        for (i=0; i < seedList.length; i++) {
          if (seedList[i].id === Number(seedId_num)) {
            this.scrapped = true
          }
        }
      })
      .catch((err) => {
        console.log(err)
      })
    },
    CheckisLiked: function () {
      const userId = {};
      const userId_num = this.user.user_id;
      const seedId_num = this.seed.id;
      userId["userId"] = userId_num;
      axios.get(`http://127.0.0.1:8080/LikeAndChallenge/${userId_num}`, userId)
      .then((res) => {
        console.log(res)
        const seedList = res.data
        var i;
        for (i=0; i < seedList.length; i++) {
          if (seedList[i].id === Number(seedId_num)) {
            this.liked = true
          }
        }
      })
      .catch((err) => {
        console.log(err)
      })
    },
  },
  created() {
    this.CheckisScrapped();
    this.CheckisLiked();
  },
  computed: {
    ...mapState('UserStore', ['user']),
    SeedImg: function () {
      return this.seed.sum_img 
    },
    category: function () {
      if (this.seed.category_id === 1) {
        return '건강'
      } else if (this.seed.category_id === 2) {
        return '생활습관'
      } else if (this.seed.category_id === 3) {
        return '독서'
      } else if (this.seed.category_id === 4) {
        return '자산'
      } else if (this.seed.category_id === 5) {
        return '자기계발'
      } else {
        return '취미'
      }
    },
    color: function () {
      if (this.seed.category_id === 1) {
        return 'light-blue lighten-1'
      } else if (this.seed.category_id === 2) {
        return 'orange lighten-1'
      } else if (this.seed.category_id === 3) {
        return 'teal lighten-1'
      } else if (this.seed.category_id === 4) {
        return 'indigo lighten-1'
      } else if (this.seed.category_id === 5) {
        return 'purple lighten-1'
      } else {
        return 'pink lighten-1'
      }
    }
  }
};
</script>


<style lang="scss" scoped>

.cursor {
  cursor: pointer;
}

.v-chip {
  height: 100%;
  font-size: 0.8rem;
  justify-content: center;
  }
.seed-card-top {
  display: flex;
  justify-content: space-between;
  margin-top: 2%;
}
#category-chip {
  margin-top: 3%;
  margin-left: 3%;
  // background-color: rgb(143, 209, 165);
}
.seed-card-bottom {
  margin-left: 3%;
  div {
    .v-chip {
      margin-bottom: 5%;
      margin-right:2%;
    }
  }
}
.v-card__title {
  font-size: 1.3em;
  font-weight: bold;
  margin: 2% 3%;
  padding: 0;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
}
.full-title {
  font-size: 0.8em;
}
</style>