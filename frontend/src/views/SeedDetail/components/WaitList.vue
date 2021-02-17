<template>
  <!-- <v-col class="d-flex justify-end"> -->
    <v-dialog
      v-model="dialog"
      scrollable
      max-width="300px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          :ripple="false"
          color="#AED864"
          class="white--text mr-6"
          v-bind="attrs"
          v-on="on"
        >
          참여 대기 목록
        </v-btn>
      </template>
      <v-card>
          <v-card-title class="d-flex justify-space-between">참여 대기 목록
          <v-btn
            color="#AED864"
            text
            icon
            @click="dialog = false"
            class="d-flex justify-center"
          >
            <v-icon>fas fa-times</v-icon>
          </v-btn>
          </v-card-title>
        <v-divider></v-divider>
        
        <v-card-text class="dialog-height mt-4">
          <WaitUser v-for="(user, idx) in waitUser" :key="idx" :user="user"></WaitUser>
        </v-card-text>
        <v-card-actions>
        </v-card-actions>
      </v-card>
    </v-dialog>
  <!-- </v-col> -->
</template>

<script>
import WaitUser from "./WaitUser"

export default {
  name: "WaitList",
  components: {
    WaitUser
  },
  data: function () {
    return {
      dialogm1: "",
      dialog: false,
    }
  },
  props: {
    waitUser: Array,
  },
  methods: {
    
  },
  watch: {
    dialog: function () {
      if (this.dialog === false) {
        this.$router.go(this.$router.currentRoute)
      }
    }
  }
}
</script>

<style lang="scss" scoped>


.dialog-height {
  height: 300px;
}

</style>