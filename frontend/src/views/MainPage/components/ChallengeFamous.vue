<template>
  <div>
    <div id="famous-title">
      <h1>요즘 많은 사랑을 받고 있는 씨앗이에요!</h1>
    </div>
    <div class="container">
      <v-row dense>
        <v-col cols="3" v-for="(challenge, index) in challenges" :key="index">
          <ChallengeCard  id="challenge-card" :challenge="challenge"/>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import ChallengeCard from "@/views/MainPage/components/ChallengeCard.vue"
export default {
  name: 'ChallengeFamous',
  components: { ChallengeCard },
  directives: {  },
  props: {
    // isSearching,
  },
  data() {
    return {
      challenges: [
        { title: '새벽 러닝', category: '건강', term: '2주', time: "주 3회", src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg'},
        { title: '미라클모닝', category: '생활습관', term: '1주', time: "주 3회", src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'},
        { title: '코스모스 읽기', category: '독서', term: '4주', time: "주 2회", src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg'},
        { title: '영화노트 작성하기', category: '취미', term: '2주', time: "주 7회", src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg'},
        { title: '영상 편집하기', category: '자기계발', term: '2주', time: "주 2회", src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg'},
        { title: '기타 초급 떼기', category: '취미', term: '1주', time: "주 5회", src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg'},
        { title: '프랑스자수 기본 스티치', category: '취미', term: '3주', time: "주 1회", src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg'},
        { title: '주식 매매일지 쓰기', category: '자산', term: '1주', time: "주 6회", src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg'},
      ],
    };
  },
  mounted() {
    
  },
  methods: {
    
  },
};
</script>

<style lang="scss" scoped>
#famous-title {
  display: flex;
  justify-content: center;
  margin-bottom: 40px;
}
.v-text-title {
  display: flex;
  margin-top: 40px;
}
#title {
  display: block;
  width: 100%;
}
.container {
  width:100%;
  left:12% !important;
  margin-left: 0;
}
#challenge-card {
  display: flex;
  justify-content: center;
  width: 100%;
}

</style>