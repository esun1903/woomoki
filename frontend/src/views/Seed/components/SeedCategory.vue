<template>
  <v-container>
    Selected category: {{ category }}
    <br>
    isSubmit: {{ isSubmitCategory }}
    <v-btn-toggle
      v-model="category"
      class="d-flex justify-center flex-wrap"
      >

          <v-btn
            :value="lifestyle"
            width="10vw"
            height="10vw"
            color="grey lighten-5"
            class="ma-2"
            :ripple="false"
            @click="clickLifestyle"
          >
            <div>
              <v-icon 
                size="3vw"
                color="orange lighten-1"
              >
                fas fa-utensils
              </v-icon>
              <h2 class="category-font">생활습관</h2>
            </div>
          </v-btn>
        
          <v-btn
            :value="health"
            width="10vw"
            height="10vw"
            color="grey lighten-5"
            class="ma-2"
            :ripple="false"
            @click="clickHealth"
          >
            <div>
              <v-icon 
                size="3vw"
                color="light-green lighten-1"
              >
                fas fa-dumbbell
              </v-icon>
              <h2 class="category-font">건강</h2>
            </div>
        
          </v-btn>

          <v-btn
            value="book"
            width="10vw"
            height="10vw"
            color="grey lighten-5"
            class="ma-2"
            :ripple="false"
            @click="clickBook"
          >
            <div>
              <v-icon 
                size="3vw"
                color="teal lighten-1"
              >
              fas fa-book
              </v-icon>
              <h2 class="category-font">독서</h2>
            </div>
          </v-btn>
        
          <v-btn
            value="assets"
            width="10vw"
            height="10vw"
            color="grey lighten-5"
            class="ma-2"
            :ripple="false"
            @click="clickAssets"
          >
            <div>
              <v-icon 
                size="3vw"
                color="indigo lighten-1"
              >
              fas fa-chart-line
              </v-icon>
              <h2 class="category-font">자산</h2>
            </div>
          </v-btn>
        
          <v-btn
            value="grown"
            width="10vw"
            height="10vw"
            color="grey lighten-5"
            class="ma-2"
            :ripple="false"
            @click="clickGrown"
          >
            <div>
              <v-icon 
                size="3vw"
                color="purple lighten-1"
              >
              fas fa-running
              </v-icon>
              <h2 class="category-font">자기계발</h2>
            </div>
          </v-btn>
        
          <v-btn
            value="hobby"
            width="10vw"
            height="10vw"
            color="grey lighten-5"
            class="ma-2"
            :ripple="false"
            @click="clickHobby"
          >
            <div>
              <v-icon 
                size="3vw"
                color="pink lighten-1"
              >
              fas fa-camera
              </v-icon>
              <h2 class="category-font">취미</h2>
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
      lifestyle: "lifestyle",
      health: "health",
      book: "book",
      assets: "assets",
      grown: "grown",
      hobby: "hobby",
      category: undefined,
      isSubmitCategory: false,
    }
  },
  methods: {
    // 후.........................이렇게 만들고 싶지 않았는데.......
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
      // const CategoryObject = {
      //   isSubmit: this.isSubmitCategory,
      //   category: this.lifestyle
      // }
      this.$emit('transferLifestyle', this.isSubmitCategory, this.lifestyle)
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
      this.$emit('transferHealth', this.isSubmitCategory, this.health)
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
      this.$emit('transferBook', this.isSubmitCategory, this.book)
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
      this.$emit('transferAssets', this.isSubmitCategory, this.assets)
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
      this.$emit('transferGrown', this.isSubmitCategory, this.grown)
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
      this.$emit('transferHobby', this.isSubmitCategory, this.hobby)
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

.category-font {
  font-size: 1vw;
}

</style>
