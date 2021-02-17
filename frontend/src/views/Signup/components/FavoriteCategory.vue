<template>
  <div class="d-flex justify-center">
      <v-card class="fav-card">
        <v-toolbar
          flat
          color="transparent"
        >
          <v-toolbar-title>관심있는 카테고리를 담아주세요!</v-toolbar-title>
        </v-toolbar>
        <v-divider></v-divider>
    
        <v-container>
          <v-row>
            <v-col
              v-for="(selection, i) in getSelections"
              :key="selection.category"
              class="shrink"
            >
              <v-chip 
                :color=chipColor(selection.category)
                outlined
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
            color="#AED864"
            text
            @click="sendFavoriteCategories"
          >우목이 시작하기!</v-btn>
        </v-card-actions>
      </v-card>

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
          content: '30분 책읽기, 코스모스 끝내기, 한 달 두 권 읽기 등이 있어요!'
        },
        {
          category: '자산',
          category_id: '4',
          icon: 'mdi-cash-usd-outline',
          content: '경제 기사 스크랩, 가계부 쓰기, 출근 길 택시 안 타기 등이 있어요!'
        },
        {
          category: '자기계발',
          category_id: '5',
          icon: 'mdi-school',
          content: '1일 2알고리즘, 매일 영단어 20개, 뉴스레터 밀리지 않기 등이 있어요'
        },
        {
          category: '취미',
          category_id: '6',
          icon: 'mdi-piano',
          content: '기타 연습하기, 영상 편집하기, 프랑스자수-기초 스티치 마스터 등이 있어요!'
        },
      ],
    loading: false,
    selected: [],
    };
  },
  computed: {
    ...mapState('UserStore', ['user']),
    chipColor () {
      return (val) => {
        if (val === '건강') {
          return 'light-blue lighten-1'
        } else if (val === '생활습관') {
          return 'orange lighten-1'
        } else if (val === '독서') {
          return 'teal lighten-1'
        } else if (val === '자산') {
          return 'indigo lighten-1'
        } else if (val === '자기계발') {
          return 'purple lighten-1'
        } else {
          return 'pink lighten-1'
        }
      }
    },
    isAllSelected: function (state) {
      return state.selected.length === state.items.length
    },
    getSelections: function (state) {
      const selections = []

      for (const selection of state.selected) {
        selections.push(selection)
      }
      console.log(selections)
      return selections
    },
    getFavoriteCategories: function (state) {
      const favoriteCategories = []
      for (const selectedCategory of state.selected) {
        const favoriteCategory = {}
        const user_id = this.user.user_id
        favoriteCategory["user_id"] = user_id
        favoriteCategory["category_id"] = selectedCategory.category_id
        favoriteCategories.push(favoriteCategory)
      }
      return favoriteCategories
    },
  },
  mounted() {},
  methods: {
    sendFavoriteCategories: function (state) {
      state.loading = true
      
      setTimeout(() => {
        
        // !!!!!!!!!! 빽에 고른 카테고리 넘겨주기!!!!!!!!!! 
        axios.post("http://127.0.0.1:8080/signup/favCategory", this.getFavoriteCategories)
          .then((res) => {
            console.log('카테고리 담기 성공')
            console.log(res)
            // this.$router.push({ name: 'Main' })
            this.$router.push({ name: 'Main' , params: { userNickname: this.$store.state.UserStore.user.nickname }})
          })
          .catch(err => {
            console.log(err)
            console.log('카테고리 담기 실패')
            console.log(this.getFavoriteCategories)
          })

        state.selected = []
        state.loading = false
      },2000)
    },
  }
};
</script>

<style lang="scss" scoped>

.fav-card {
  width: 40%;
  margin-top: 10vh;
  margin-bottom: 0;
  top: 10%;
  padding: 0 2%;
  .v-toolbar{
    display: flex;
    justify-content: center;
    .v-toolbar__content{

      .v-toolbar__title{
            text-align: center;
      }
    }
  }
  .v-divider{
    margin: 0 0 7% 0;
    margin-bottom: 7%;
  }
  .container{
    .row{
        display: flex;
        justify-content: center;
      .col{

        .v-chip{
          .v-chip__content{
            .v-icon{

            }
            .v-icon{

            }
          }
        }
      }
    }
  }
  .v-divider{
    
  }
  .v-list{
  }
  .v-divider{
    margin: 2% 0;
  }
  .v-card__actions{

  }
}

// .v-divider {

// }

// .v-chip{
//   color: green !important;
//   border-color: green !important;
// }

</style>
