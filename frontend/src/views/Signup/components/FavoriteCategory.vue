<template>
  <div>
    <v-app>
      <v-card id="fav-card">
        <v-toolbar
          flat
          color="transparent"
        >
          <v-toolbar-title>관심있는 카테고리를 담아주세요!</v-toolbar-title>
        </v-toolbar>
    
        <v-container class="py-0">
          <v-row align="center" justify="start">
            <v-col
              v-for="(selection, i) in getSelections"
              :key="selection.category"
              class="shrink"
            >
              <v-chip outlined
                :disabled="loading"
                close
                @click:close="selected.splice(i, 1)"
              >
                <v-icon
                  left
                  v-text="selection.icon"
                ></v-icon>
                {{ selection.category }}
              </v-chip>
            </v-col>
          </v-row>
        </v-container>
    
        <v-divider v-if="!isAllSelected"></v-divider>
    
        <v-list>
          <template v-for="item in items">
            <v-list-item
              v-if="!selected.includes(item)"
              :key="item.category"
              :disabled="loading"
              @click="selected.push(item)"
            >
              <v-list-item-avatar>
                <v-icon
                  :disabled="loading"
                  v-text="item.icon"
                ></v-icon>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title v-text="item.category"></v-list-item-title>
                <v-list-item-subtitle v-text="item.content"></v-list-item-subtitle>
              </v-list-item-content>

            </v-list-item>
          </template>
        </v-list>
    
        <v-divider></v-divider>
    
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            :disabled="!selected.length"
            :loading="loading"
            color="green"
            text
            @click="sendFavoriteCategories"
          >GRITREE 시작하기!</v-btn>
        </v-card-actions>
      </v-card>
    </v-app>
  </div>
</template>

<script>
import axios from "axios";
import {mapState} from "vuex";
export default {
  name: "FavoriteCategory",
  components: {},
  directives: {},
  data: function() {
    return {
          // favoriteCategories 관련 state
    items: [
        {
          category: '건강',
          category_id: '1',
          icon: 'mdi-dumbbell',
          content: '식이조절, 복근 만들기, 클로이팅 챌린지, 식단일기 쓰기 등이 있어요! '
        },
        {
          category: '생활습관',
          category_id: '2',
          icon: 'mdi-calendar-check',
          content: '미라클 모닝, 스크린 타임 4시간, 물 2L 마시기, 매일 일기쓰기 등이 있어요!'
        },
        {
          category: '독서',
          category_id: '3',
          icon: 'mdi-bookshelf',
          content: '30분 책읽기, 코스모스 끝내기, 독서 기록장 쓰기, 한 달 두 권 읽기 등이 있어요!'
        },
        {
          category: '자산',
          category_id: '4',
          icon: 'mdi-cash-usd-outline',
          content: '경제 기사 스크랩, 가계부 쓰기, 출근 길 택시 안 타기, 주식일기 쓰기 등이 있어요!'
        },
        {
          category: '자기계발',
          category_id: '5',
          icon: 'mdi-school',
          content: '1일 2알고리즘, 매일 영단어 20개, 뉴스레터 밀리지 않기, 컴활 공부하기 등이 있어요'
        },
        {
          category: '취미',
          category_id: '6',
          icon: 'mdi-piano',
          content: '매일 크로키, 기타 연습하기, 영상 편집하기, 프랑스자수-기초 스티치 마스터 등이 있어요!'
        },
      ],
    loading: false,
    selected: [],
    };
  },
  computed: {
    ...mapState('UserStore', ['user']),

    // 모든 아이템이 골라졌을 때 구분선 없애기 위함
    isAllSelected: function (state) {
      return state.selected.length === state.items.length
    },
    // 선택 시 바로바로 칩 만들기 위함
    getSelections: function (state) {
      const selections = []

      for (const selection of state.selected) {
        selections.push(selection)
      }

      return selections
    },
    // 선택된 카테고리명만 백엔드에 넘기기 위함
    getFavoriteCategories: function (state) {
      const favoriteCategories = []
      for (const selectedCategory of state.selected) {
        const favoriteCategory = {}
        const user_id = this.user.user_id
        favoriteCategory["user_id"] = user_id
        favoriteCategory["category_id"] = selectedCategory.category_id
        favoriteCategories.push(favoriteCategory)
      }
      console.log(userFavoriteCateogories)
      return favoriteCategories
    },
  },
  mounted() {},
  methods: {
    sendFavoriteCategories: function (state) {
      state.loading = true
      
      setTimeout(() => {
        
        // !!!!!!!!!! 빽에 고른 카테고리 넘겨주기!!!!!!!!!! 
        axios.post("http://localhost:8080/signup/favCategory", this.getFavoriteCategories)
          .then((res) => {
            console.log('카테고리 담기 성공')
            console.log(res)
            this.$router.push({ name: 'MainPage' })
          })
          .catch(err => {
            console.log(err)
            console.log('카테고리 담기 실패')
            console.log(this.getFavoriteCategories)
          })

        state.selected = []
        state.loading = false
      },2000)
      
      // setTimeout(() => {
        
      //   // !!!!!!!!!! 빽에 고른 카테고리 넘겨주기!!!!!!!!!! 
      //   axios.post("http://localhost:8080/signup/favCategory", this.getFavoriteCategories)
      //     .then((res) => {
      //       console.log('카테고리 담기 성공')
      //       console.log(res)
      //       // console.log(this.getFavoriteCategories)
      //       this.$router.push({ name: 'MainPage' })
      //     })
      //     .catch(err => {
      //       console.log(err)
      //       console.log('카테고리 담기 실패')
      //       console.log(this.getFavoriteCategories)
      //     })

      //   state.search = ''
      //   state.selected = []
      //   state.loading = false
      // }, 2000)
    },
  }
};
</script>

<style lang="scss" scoped>
#fav-card {
  width: 50%;
  top:15%;
  left: 25%;
  padding: 0px 50px;
}
.v-divider {
  margin-top: 20px;
}
.v-chip{
  color: green !important;
  border-color: green !important;
}
</style>
