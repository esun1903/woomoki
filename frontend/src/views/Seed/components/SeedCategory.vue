<template>
  <v-container>
    <v-btn-toggle
      v-model="CategoryValue"
      >

    
      <v-row no-gutters>

        <v-col class="pa-1">
              <v-btn
                width="100%"
                height="200px"
                color="grey lighten-5"
                input-value="color"
                :ripple="false"
                @click="clickCategory"
                :class="[isSubmitCategory ? 'select' : 'unselect' ]"
              >
                <div>
                  <v-icon 
                    size="64"
                    color="light-green lighten-1"
                  >
                    fas fa-dumbbell
                  </v-icon>
                  <h2 class="black--text">건강</h2>
                </div>
            
              </v-btn>
            
        </v-col>

        <v-col class="pa-1">
          <v-btn
            width="100%"
            height="200px"
            color="grey lighten-5"
            :ripple="false"
          >
            <div>
              <v-icon 
                size="64"
                color="orange lighten-1"
              >
                fas fa-utensils
              </v-icon>
              <h2>생활습관</h2>
            </div>
          </v-btn>
        </v-col>

        <v-col class="pa-1">
          <v-btn
            width="100%"
            height="200px"
            color="grey lighten-5"
            :ripple="false"
          >
            <div>
              <v-icon 
                size="64"
                color="teal lighten-1"
              >
              fas fa-book
              </v-icon>
              <h2>독서</h2>
            </div>
          </v-btn>
        </v-col>
      </v-row>

      <v-row no-gutters>

        <v-col class="pa-1">
        <v-btn
            width="100%"
            height="200px"
            color="grey lighten-5"
            :ripple="false"
            active-class=""
          >
            <div>
              <v-icon 
                size="64"
                color="indigo lighten-1"
              >
              fas fa-chart-line
              </v-icon>
              <h2>자산</h2>
            </div>
          </v-btn>
        </v-col>

        <v-col class="pa-1">
          <v-btn
            width="100%"
            height="200px"
            color="grey lighten-5"
            :ripple="false"
          >
            <div>
              <v-icon 
                size="64"
                color="purple lighten-1"
              >
              fas fa-running
              </v-icon>
              <h2>자기계발</h2>
            </div>
          </v-btn>
        </v-col>

        <v-col class="pa-1">
          <v-btn
            width="100%"
            height="200px"
            color="grey lighten-5"
            :ripple="false"
          >
            <div>
              <v-icon 
                size="64"
                color="pink lighten-1"
              >
              fas fa-camera
              </v-icon>
              <h2>취미</h2>
            </div>
          </v-btn>
        </v-col>

      </v-row>
    </v-btn-toggle>
  </v-container>
</template>

<script>
export default {
  name: "SeedCategory",
  data: function () {
    return {
      hidden: false,
      isSubmitCategory: false,
      CategoryValue: ""
    }
  },
  methods: {
    clickCategory: function () {
      this.isSubmitCategory = !this.isSubmitCategory
      console.log(this.CategoryValue)
      this.$emit('input', this.isSubmitCategory)
    }
  }
}
</script>

<style scoped>

.select {
  color: red;
}
.unselect {
  color: ivory;
}

</style>
