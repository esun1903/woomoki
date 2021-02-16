<template>
  <v-container>
    <v-card id="fav-card">
  
      <v-toolbar class="d-flex justify-center" flat color="transparent">
        <v-toolbar-title @click="initialFavCategory">관심 카테고리를 다시 설정해주세요!</v-toolbar-title>
      </v-toolbar>

      <v-row align="center" justify="center">
        <v-col
          v-for="(selection, i) in getSelections"
          :key="selection.text"
          class="shrink"
        > 
          <span v-if="selection.text === '건강'">
            <v-chip
              color="light-blue lighten-1"
              outlined
              :disabled="loading"
              close
              @click:close="selected.splice(i, 1)"
            >
              <v-icon left v-text="selection.icon"></v-icon>
              {{ selection.text }}
            </v-chip>
          </span>
          <span v-if="selection.text === '생활습관'">
            <v-chip
              color="orange lighten-1"
              outlined
              :disabled="loading"
              close
              @click:close="selected.splice(i, 1)"
            >
              <v-icon left v-text="selection.icon"></v-icon>
              {{ selection.text }}
            </v-chip>
          </span>
          <span v-if="selection.text === '독서'">
            <v-chip
              color="teal lighten-1"
              outlined
              :disabled="loading"
              close
              @click:close="selected.splice(i, 1)"
            >
              <v-icon left v-text="selection.icon"></v-icon>
              {{ selection.text }}
            </v-chip>
          </span>
          <span v-if="selection.text === '자산'">
            <v-chip
              color="indigo lighten-1"
              outlined
              :disabled="loading"
              close
              @click:close="selected.splice(i, 1)"
            >
              <v-icon left v-text="selection.icon"></v-icon>
              {{ selection.text }}
            </v-chip>
          </span>
          <span v-if="selection.text === '자기계발'">
            <v-chip
              color="purple lighten-1"
              outlined
              :disabled="loading"
              close
              @click:close="selected.splice(i, 1)"
            >
              <v-icon left v-text="selection.icon"></v-icon>
              {{ selection.text }}
            </v-chip>
          </span>
          <span v-if="selection.text === '취미'">
            <v-chip
              color="pink lighten-1"
              outlined
              :disabled="loading"
              close
              @click:close="selected.splice(i, 1)"
            >
              <v-icon left v-text="selection.icon"></v-icon>
              {{ selection.text }}
            </v-chip>
          </span>
        </v-col>
      </v-row>

      <v-divider v-if="!isAllSelected"></v-divider>

      <v-list>
        <template v-for="item in items">
          <v-list-item
            v-if="!selected.includes(item)"
            :key="item.text"
            :disabled="loading"
            @click="selected.push(item)"
            class="tooltip"
          >
            <div class="category-center">
              <v-list-item-avatar>
                <v-icon :disabled="loading" v-text="item.icon"></v-icon>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title v-text="item.text"></v-list-item-title>
                <div class="tooltip-content">
                  <v-list-item-subtitle class="hover-content-color"
                    v-text="item.content"
                  ></v-list-item-subtitle>
                </div>
              </v-list-item-content>
            </div>
          </v-list-item>
        </template>
      </v-list>

      <v-divider></v-divider>

      <v-card-actions>
        <v-spacer></v-spacer>
        <!-- <v-btn
          :disabled="!selected.length"
          :loading="loading"
          color="#AED864"
          text
          @click="sendFavoriteCategories"
          >카테고리 저장</v-btn
        > -->
        <v-btn
          :loading="loading"
          color="#AED864"
          class="white--text"
          @click="sendFavoriteCategories"
          >카테고리 저장</v-btn
        >
      </v-card-actions>
    </v-card>
    </v-container>
</template>

<script>
import axios from "axios"
import {mapState} from "vuex"

export default {
  name: "FavoriteCategory",
  components: {},
  directives: {},
  data: function() {
    return {
      // favoriteCategories 관련 state
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
      loading: false,
      selected: [],
      tmp: [],
      initCategory: [],
    };
  },
  computed: {
    ...mapState('UserStore', ['user']),
    // ----------------- FavoriteCategory용-----------------
    // 모든 아이템이 골라졌을 때 구분선 없애기 위함
    isAllSelected: function(state) {
      return state.selected.length === state.items.length;
    },
    // 선택 시 바로바로 칩 만들기 위함
    getSelections: function(state) {
      const selections = [];

      for (const selection of state.selected) {
        selections.push(selection);
      }

      return selections;
    },
    // 선택된 카테고리명만 백엔드에 넘기기 위함
    getFavoriteCategories: function (state) {
      const favoriteCategories = []
      for (const selectedCategory of state.selected) {
        const favoriteCategory = {}
        const user_id = this.user.user_id
        favoriteCategory["user_id"] = user_id
        if (selectedCategory.text === '건강') {
          favoriteCategory["category_id"] = 1
        } else if (selectedCategory.text === '생활습관') {
          favoriteCategory["category_id"] = 2          
        } else if (selectedCategory.text === '독서') {
          favoriteCategory["category_id"] = 3          
        } else if (selectedCategory.text === '자산') {
          favoriteCategory["category_id"] = 4          
        } else if (selectedCategory.text === '자기계발') {
          favoriteCategory["category_id"] = 5          
        } else {
          favoriteCategory["category_id"] = 6       
        }
        favoriteCategories.push(favoriteCategory)
      console.log("test1",favoriteCategories)
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
        axios.post("http://localhost:8080/userPage/UpdatefavCategory", this.getFavoriteCategories)
          .then((res) => {
            // console.log("유저 아이디",this.user.user_id)
            // console.log("test",this.getFavoriteCategories)
            // console.log('카테고리 담기 성공')
            console.log(res)
            // this.$router.push({ to: 'MainPage' })
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
    async initialFavCategory () {
      const userNickname = this.user.nickname
      await axios.get(`http://localhost:8080/userPage/favCategoryName/${userNickname}`)
        .then((res) => {
          this.tmp = res.data
        })
        .catch((err) => {
          console.log(err)
        })

        for (let i=0; i < this.items.length; i++) {
          for (let j=0; j < this.tmp.length; j++) {
            if (this.items[i].text === this.tmp[j].name) {
              this.selected.push(this.items[i])
            }
          }
        }
        console.log(this.selected)
    },
    test: function() {
      console.log(this.selected)
    }
  },
  watch: {
    selected: function () {
      console.log("?")
    }
  },
  created() {
    this.initialFavCategory();
  }
};
</script>

<style lang="scss" scoped>
#fav-card {
  width: 100%;
  top:15%;
  padding: 0px 50px;
}
.v-divider {
  margin-top: 20px;
}
.v-chip{
  color: #9CCC65 !important;
  border-color: #9CCC65 !important;
}

.category-center {
  display: flex;
  justify-content: center;
}

.hover-content-color {
  color: white !important;
}

.tooltip {
  position: relative;
  display: inline-block;
  margin: auto;
}

.tooltip .tooltip-content {
  visibility: hidden;
  background-color: #9CCC65;
  padding: 10px;
  margin-top: 10px;
  text-align: center;
  position: absolute;
  z-index: 1;
  border-radius: 10px;
  bottom: 110%;
}

.tooltip .tooltip-content::after {
  content: " ";
  position: absolute;
  top: 100%;
  left: 5%;
  margin-left: -10px;
  border-width: 10px;
  border-style: solid;
  border-color: #9CCC65 transparent transparent transparent;
}

.tooltip:hover .tooltip-content {
  visibility: visible;
}
</style>
