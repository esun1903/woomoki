<template>
  <v-container>
    <div class="title">
      <p>꽃피운 씨앗</p>
    </div>
    <div class="cards">
      <v-row dense>
        <v-col cols="3" class="card" v-for="(challenge, index) in challenges" :key="index">
          <SeedCard :challenge="challenge"/>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import SeedCard from "./components/SeedCard.vue"

export default {
  name: "SeedSuccess",
  components: {
    SeedCard
  },
  data: function () {
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
    }
  }

}
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 89.5vh;
  margin-bottom: 10%;
  .title {
    display: flex;
    justify-content: center;
    margin: 1% 0;
    p {
      font-size: 1.5em;
      font-weight: bold;
    }
  }
  .cards {
    width: 100%;
    height: 100%;
    .row {
      .card {
        padding: 1%;
        width: 100%;
        height: 40%;
        .v-card {
          width: 100%;
          height: 50%;
        }
      }    
    }
  }
}
</style>