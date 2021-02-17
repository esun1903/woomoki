<template>
  <v-col class="d-flex justify-center">
    <v-dialog
      v-model="dialog"
      scrollable
      max-width="300px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          :ripple="false"
          color="white"
          plain
          v-bind="attrs"
          v-on="on"
        >
          참여: {{ joinUser.length }} / {{ SeedInfo.max_people }}명
        </v-btn>
      </template>
      <v-card>
          <v-card-title class="d-flex justify-space-between">참여중인 유저
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
          <JoinUser v-for="(user, idx) in joinUser" :key="idx" :user="user"></JoinUser>
        </v-card-text>
        <v-card-actions>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-col>
</template>

<script>
import axios from "axios"
import JoinUser from "./JoinUser"

export default {
  name: "JoinList",
  components: {
    JoinUser
  },
  data: function () {
    return {
      dialogm1: "",
      dialog: false,
    }
  },
  props: {
    joinUser: Array,
    SeedInfo: [Object, Array]
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