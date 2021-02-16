<template>
  <v-col class="mt-15 d-flex justify-end" cols="auto">
    <v-dialog
      transition="dialog-top-transition"
      width="400"
      height="100"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn 
          plain 
          text
          v-bind="attrs"
          v-on="on"
          >회원 탈퇴하시겠습니까?
        </v-btn>
      </template>
      <template v-slot:default="dialog">
        <v-card>
          <div>
            <v-toolbar
              class="d-flex justify-end message"
              color="#AED864"
              width="100%"
              dark
            >
          
              <v-col>
                <v-btn
                  icon
                  right
                  @click="dialog.value = false"
                  >
                  <v-icon>fas fa-times</v-icon>
                </v-btn>
              </v-col>

          </v-toolbar>
          </div>
          <v-card-text>
            <div class="pt-10">
              <div class="message pb-5">정말 탈퇴하시겠습니까?</div>
              <div class="message">탈퇴 후 회원정보는 모두 삭제됩니다</div>
            </div>
          </v-card-text>
          <v-card-actions class="justify-end">
            <v-btn
              text
              color="red"
              @click="[dialog.value = false, userDelete()]"
            >탈퇴</v-btn>
          </v-card-actions>
        </v-card>
      </template>
    </v-dialog>
  </v-col>
</template>

<script>
import axios from "axios"

export default {
  name: "UserDelete",
  methods: {
    userDelete: function () {
      const userId = this.$store.state.UserStore.user.user_id 
      axios.delete(`http://localhost:8080/userPage/${userId}`)
        .then((res) => {
          this.$router.push({name: 'Main'})
          this.$store.dispatch('UserStore/logOut')
          console.log(res)
        })
    },
  }
}
</script>

<style lang="scss" scoped>

.message {
  font-size: 18px;
}

</style>