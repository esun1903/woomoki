import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // favoriteCategories 관련 state
    items: [
      {
        text: '건강',
        icon: 'mdi-dumbbell',
        content: '식이조절, 복근 만들기, 클로이팅 챌린지, 식단일기 쓰기 등이 있어요! '
      },
      {
        text: '생활습관',
        icon: 'mdi-calendar-check',
        content: '미라클 모닝, 스크린 타임 4시간, 물 2L 마시기, 매일 일기쓰기 등이 있어요!'
      },
      {
        text: '독서',
        icon: 'mdi-bookshelf',
        content: '30분 책읽기, 코스모스 끝내기, 독서 기록장 쓰기, 한 달 두 권 읽기 등이 있어요!'
      },
      {
        text: '자산',
        icon: 'mdi-cash-usd-outline',
        content: '경제 기사 스크랩, 가계부 쓰기, 출근 길 택시 안 타기, 주식일기 쓰기 등이 있어요!'
      },
      {
        text: '자기계발',
        icon: 'mdi-school',
        content: '1일 2알고리즘, 매일 영단어 20개, 뉴스레터 밀리지 않기, 컴활 공부하기 등이 있어요'
      },
      {
        text: '취미',
        icon: 'mdi-piano',
        content: '매일 크로키, 기타 연습하기, 영상 편집하기, 프랑스자수-기초 스티치 마스터 등이 있어요!'
      },
    ],
    loading: false,
    selected: [],


  },
  getters: {
    // ----------------- FavoriteCategory용-----------------
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
      
      for (const favoriteCategory of state.selected)
        favoriteCategories.push(favoriteCategory.text)
        console.log(favoriteCategories)
      return favoriteCategories
    }
  },
  mutations: {
    GO_MAINPAGE: function (state) {
      state.loading = true

      setTimeout(() => {
        
        // !!!!!!!!!! 빽에 고른 카테고리 넘겨주기!!!!!!!!!! 
        // axios.post(`${SERVER_URL}/signup/favCategory`, this.getFfavoriteCategories)
        //   .then(() => {
        //     // console.log('로그인 성공')
        //     this.$router.push({ name: 'MainPage' })
        //   })
        //   .catch(err => console.log(err))

        state.search = ''
        state.selected = []
        state.loading = false
      }, 2000)
    },
  },
  actions: {},
  modules: {}
});
