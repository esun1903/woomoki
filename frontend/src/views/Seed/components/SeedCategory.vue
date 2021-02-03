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
                <v-icon 
                  size="64"
                  color="light-green lighten-1"
                >
                  fas fa-dumbbell
                </v-icon>

                <!-- <div>건강</div> -->
            
              </v-btn>
            
        </v-col>

        <v-col class="pa-1">
          <v-btn
            width="100%"
            height="200px"
            color="grey lighten-5"
            :ripple="false"
          >
            <v-icon 
              size="64"
              color="orange lighten-1"
            >
              fas fa-utensils
            </v-icon>
            <!-- <h1>생활습관</h1> -->
          </v-btn>
        </v-col>

        <v-col class="pa-1">
          <v-btn
            width="100%"
            height="200px"
            color="grey lighten-5"
            :ripple="false"
          >
            <v-icon 
              size="64"
              color="teal lighten-1"
            >
            fas fa-book
            </v-icon>
            <!-- <h1>독서</h1> -->
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
            <v-icon 
              size="64"
              color="indigo lighten-1"
            >
            fas fa-chart-line
            </v-icon>
            <!-- <h1>자산</h1> -->
          </v-btn>
        </v-col>

        <v-col class="pa-1">
          <v-btn
            width="100%"
            height="200px"
            color="grey lighten-5"
            :ripple="false"
          >
            <v-icon 
              size="64"
              color="purple lighten-1"
            >
            fas fa-running
            </v-icon>
            <!-- <h1>자기계발</h1> -->
          </v-btn>
        </v-col>

        <v-col class="pa-1">
          <v-btn
            width="100%"
            height="200px"
            color="grey lighten-5"
            :ripple="false"
          >
            <v-icon 
              size="64"
              color="pink lighten-1"
            >
            fas fa-camera
            </v-icon>
            <!-- <h1>취미</h1> -->
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
