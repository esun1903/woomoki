<template>
    <v-row class="d-flex justify-center category-margin-top">
      <div>
        <v-row class="d-flex justify-center category-margin-bottom">
          <h3>{{ this.user.nickname }} 님이 좋아하는 카테고리</h3>
        </v-row>
        <div class="category-width category-margin-bottom">
          <v-row class="d-flex justify-center">
          <span class="category" v-for="(category, idx) in selected" :key="idx">
            <span v-if="category.name === '건강'"> 
              <v-chip large class="chip-font white--text" color='light-blue lighten-1'>
                {{ category.name }}
              </v-chip>
            </span>
            <span v-if="category.name === '생활습관'"> 
              <v-chip large class="chip-font white--text" color='orange lighten-1'>
                {{ category.name }}
              </v-chip>
            </span>
            <span v-if="category.name === '독서'"> 
              <v-chip large class="chip-font white--text" color='teal lighten-1'>
                {{ category.name }}
              </v-chip>
            </span>
            <span v-if="category.name === '자산'"> 
              <v-chip large class="chip-font white--text" color='indigo lighten-1'>
                {{ category.name }}
              </v-chip>
            </span>
            <span v-if="category.name === '자기계발'"> 
              <v-chip large class="chip-font white--text" color='purple lighten-1'>
                {{ category.name }}
              </v-chip>
            </span>
            <span v-if="category.name === '취미'"> 
              <v-chip large class="chip-font white--text" color='pink lighten-1'>
                {{ category.name }}
              </v-chip>
            </span>
          </span>
          </v-row>
        </div>
      </div>
    </v-row>
</template>

<script>
import axios from "axios"
import {mapState} from "vuex"

export default {
  name: "FavoritCategory",
  data: function () {
    return {
      items: [
        {
          text: "건강",
          category_id: '1',
          icon: "mdi-dumbbell",
          content:
            "식이조절, 복근 만들기, 클로이팅 챌린지, 식단일기 쓰기 등이 있어요! "
        },
        {
          text: "생활습관",
          category_id: '2',
          icon: "mdi-calendar-check",
          content:
            "미라클 모닝, 스크린 타임 4시간, 물 2L 마시기, 매일 일기쓰기 등이 있어요!"
        },
        {
          text: "독서",
          category_id: '3',
          icon: "mdi-bookshelf",
          content:
            "30분 책읽기, 코스모스 끝내기, 독서 기록장 쓰기, 한 달 두 권 읽기 등이 있어요!"
        },
        {
          text: "자산",
          category_id: '4',
          icon: "mdi-cash-usd-outline",
          content:
            "경제 기사 스크랩, 가계부 쓰기, 출근 길 택시 안 타기, 주식일기 쓰기 등이 있어요!"
        },
        {
          text: "자기계발",
          category_id: '5',
          icon: "mdi-school",
          content:
            "1일 2알고리즘, 매일 영단어 20개, 뉴스레터 밀리지 않기, 컴활 공부하기 등이 있어요!"
        },
        {
          text: "취미",
          category_id: '6',
          icon: "mdi-piano",
          content:
            "매일 크로키, 기타 연습하기, 영상 편집하기 프랑스자수-기초 스티치 마스터 등이 있어요!"
        }
      ],
      selected: [],
    }
  },
  computed: {
    ...mapState('UserStore', ['user']),
  },
  created () {
    this.initialFavCategory();
  },
  methods: {
    async initialFavCategory () {
      const userNickname = this.user.nickname
      await axios.get(`http://localhost:8080/userPage/favCategoryName/${userNickname}`)
        .then((res) => {
          this.selected = res.data
          console.log("카테고리", this.selected)
        })
        .catch((err) => {
          console.log(err)
        })

        // for (let i=0; i < this.items.length; i++) {
        //   for (let j=0; j < this.tmp.length; j++) {
        //     if (this.items[i].text === this.tmp[j].name) {
        //       this.selected.push(this.items[i])
        //     }
        //   }
        // }
        
    },
  }
}
</script>

<style lang="scss" scoped>

.category-margin-top {
  margin-top: 5%;
}

.category-margin-bottom {
  margin-bottom: 5%;
}

.category-width {
  width: 800px;
}

.category {
  margin: 1%;
}

.chip-font {
  font-size: 25px;
}

</style>