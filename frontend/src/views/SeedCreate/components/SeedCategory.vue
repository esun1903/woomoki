<template>
  <v-container>
    <v-btn-toggle
      borderless
      color="lime accent-3"
      value="test"
      v-model="category"
      class="d-flex justify-center flex-wrap"
    >
      <v-btn
        value="health"
        width="12vw"
        height="12vw"
        color="grey lighten-5"
        class="ma-2"
        :ripple="false"
        @click="clickHealth"
      >
        <div>
          <v-icon 
            size="4vw"
            color="light-blue lighten-1"
          >
            fas fa-dumbbell
          </v-icon>
          <h2 class="category-font-health">건강</h2>
        </div>
    
      </v-btn>
      
      <v-btn
        value="lifestyle"
        width="12vw"
        height="12vw"
        color="grey lighten-5"
        class="ma-2"
        :ripple="false"
        @click="clickLifestyle"
      >
        <div>
          <v-icon 
            size="4vw"
            color="orange lighten-1"
          >
          fas fa-calendar-check
          </v-icon>
          <h2 class="category-font-lifestyle">생활습관</h2>
        </div>
      </v-btn>

      <v-btn
        value="book"
        width="12vw"
        height="12vw"
        color="grey lighten-5"
        class="ma-2"
        :ripple="false"
        @click="clickBook"
      >
        <div>
          <v-icon 
            size="4vw"
            color="teal lighten-1"
          >
          fas fa-book
          </v-icon>
          <h2 class="category-font-book">독서</h2>
        </div>
      </v-btn>
    
      <v-btn
        value="assets"
        width="12vw"
        height="12vw"
        color="grey lighten-5"
        class="ma-2"
        :ripple="false"
        @click="clickAssets"
      >
        <div>
          <v-icon 
            size="4vw"
            color="indigo lighten-1"
          >
          fas fa-chart-line
          </v-icon>
          <h2 class="category-font-assets">자산</h2>
        </div>
      </v-btn>
    
      <v-btn
        value="grown"
        width="12vw"
        height="12vw"
        color="grey lighten-5"
        class="ma-2"
        :ripple="false"
        @click="clickGrown"
      >
        <div>
          <v-icon 
            size="4vw"
            color="purple lighten-1"
          >
          fas fa-graduation-cap
          </v-icon>
          <h2 class="category-font-grown">자기계발</h2>
        </div>
      </v-btn>
    
      <v-btn
        value="hobby"
        width="12vw"
        height="12vw"
        color="grey lighten-5"
        class="ma-2"
        :ripple="false"
        @click="clickHobby"
      >
        <div>
          <v-icon 
            size="4vw"
            color="pink lighten-1"
          >
          fas fa-camera
          </v-icon>
          <h2 class="category-font-hobby">취미</h2>
        </div>
      </v-btn>
    </v-btn-toggle>
  </v-container>
</template>

<script>
export default {
  name: "SeedCategory",
  data: function () {
    return {
      health: "health",
      lifestyle: "lifestyle",
      book: "book",
      assets: "assets",
      grown: "grown",
      hobby: "hobby",
      category: undefined,
      isSubmitCategory: false,
      test: false
    }
  },
  methods: {
    clickLifestyle: function () {
      if (this.category === undefined) {
        this.isSubmitCategory = true
      }
      else if (this.category !== this.lifestyle) {
        this.isSubmitCategory = true
      }
      else {
        this.isSubmitCategory = false
      }
      console.log(this.test)
      this.$emit('transferLifestyle', this.isSubmitCategory)
    },
    clickHealth: function () {
      if (this.category === undefined) {
        this.isSubmitCategory = true
      }
      else if (this.category !== this.health) {
        this.isSubmitCategory = true
      }
      else {
        this.isSubmitCategory = false
      }
      this.$emit('transferHealth', this.isSubmitCategory)
    },
    clickBook: function () {
      if (this.category === undefined) {
        this.isSubmitCategory = true
      }
      else if (this.category !== this.book) {
        this.isSubmitCategory = true
      }
      else {
        this.isSubmitCategory = false
      }
      this.$emit('transferBook', this.isSubmitCategory)
    },
    clickAssets: function () {
      if (this.category === undefined) {
        this.isSubmitCategory = true
      }
      else if (this.category !== this.assets) {
        this.isSubmitCategory = true
      }
      else {
        this.isSubmitCategory = false
      }
      this.$emit('transferAssets', this.isSubmitCategory)
    },
    clickGrown: function () {
      if (this.category === undefined) {
        this.isSubmitCategory = true
      }
      else if (this.category !== this.grown) {
        this.isSubmitCategory = true
      }
      else {
        this.isSubmitCategory = false
      }
      this.$emit('transferGrown', this.isSubmitCategory)
    },
    clickHobby: function () {
      if (this.category === undefined) {
        this.isSubmitCategory = true
      }
      else if (this.category !== this.hobby) {
        this.isSubmitCategory = true
      }
      else {
        this.isSubmitCategory = false
      }
      this.$emit('transferHobby', this.isSubmitCategory)
    },
      // // 여러 값 중 하나만 true가 한개만 있어도
      // // isSubmitCategory 가 true로 바뀌고
      // this.valueHealth = !this.valueHealth
      // console.log(this.valueHealth)
      // // true인 값에 카테고리 이름을 넣어 emit
      // this.$emit('input', this.valueHealth)
      // // 보내고 난 후 카테고리 이름이 들어간 값을 false로 초기화
  }
}
</script>

<style scoped>

.category-font-health {
  color: #42A5F5;
  margin-top: 20px;
  font-size: 1.3vw;
}

.category-font-lifestyle {
  color: #FFA726;
  margin-top: 20px;
  font-size: 1.3vw;
}

.category-font-book {
  color: #26A69A;
  margin-top: 20px;
  font-size: 1.3vw;
}

.category-font-assets {
  color: #5C6BC0;
  margin-top: 20px;
  font-size: 1.3vw;
}

.category-font-grown {
  color: #AB47BC;
  margin-top: 20px;
  font-size: 1.3vw;
}

.category-font-hobby {
  color: #EC407A;
  margin-top: 20px;
  font-size: 1.3vw;
}


</style>
