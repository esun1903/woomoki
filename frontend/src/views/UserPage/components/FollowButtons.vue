<template>
  <div>
    <!-- <div>
        <v-row justify="center">
          <v-dialog
            v-model="dialog.dialog"
            scrollable
            max-width="300px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                :ripple="false"
                color="black"
                plain
                v-bind="attrs"
                v-on="on"
              >
                <div>팔로워</div>
                <div>116</div>
              </v-btn>
            </template>
            <v-card>
                <v-card-title class="title-close-inline">팔로워
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="dialog.dialog = false"
                  >
                    Close
                  </v-btn>
                </v-card-title>
              <v-divider></v-divider>
              
              <v-card-text style="height: 300px;">
                <div>
                  <div>홍지희</div>
                  <div>최은선</div>
                  <div>김효진</div>
                  <div>표기동</div>
                  <div>박경민</div>
                  <div>박용미</div>
                  <div>신채린</div>
                  <div>신채원</div>
                  <div>현성섭</div>
                  <div>최정휴</div>
                  <div>손준희</div>
                  <div>권기현</div>
                  <div>홍지희</div>
                  <div>최은선</div>
                  <div>김효진</div>
                  <div>표기동</div>
                  <div>박경민</div>
                  <div>박용미</div>
                  <div>신채린</div>
                  <div>신채원</div>
                  <div>현성섭</div>
                  <div>최정휴</div>
                  <div>손준희</div>
                  <div>권기현</div>
                  <div>홍지희</div>
                  <div>최은선</div>
                  <div>김효진</div>
                  <div>표기동</div>
                  <div>박경민</div>
                  <div>박용미</div>
                  <div>신채린</div>
                  <div>신채원</div>
                  <div>현성섭</div>
                  <div>최정휴</div>
                  <div>손준희</div>
                  <div>권기현</div> 
                </div>
              </v-card-text>
              <v-card-actions>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </div>
      <div>
        <v-row justify="center">
          <v-dialog
            v-model="dialog.dialog2"
            scrollable
            max-width="300px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                :ripple="false"
                color="black"
                plain
                v-bind="attrs"
                v-on="on"
              >
                <div>팔로잉</div>
                <div>122</div>
              </v-btn>
            </template>
            <v-card>
                <v-card-title class="title-close-inline">팔로잉
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="dialog.dialog2 = false"
                  >
                    Close
                  </v-btn>
                </v-card-title>
              <v-divider></v-divider>
              
              <v-card-text style="height: 300px;">
                <div>
                  <div>차은우</div>
                  <div>문가영</div>
                  <div>배상연</div>
                  <div>김상훈</div>
                  <div>강병훈</div>
                  <div>조민형</div>
                  <div>김대인</div>
                  <div>김영재</div>
                  <div>차은우</div>
                  <div>문가영</div>
                  <div>배상연</div>
                  <div>김상훈</div>
                  <div>강병훈</div>
                  <div>조민형</div>
                  <div>김대인</div>
                  <div>김영재</div>
                  <div>차은우</div>
                  <div>문가영</div>
                  <div>배상연</div>
                  <div>김상훈</div>
                  <div>강병훈</div>
                  <div>조민형</div>
                  <div>김대인</div>
                  <div>김영재</div>
                </div>
              </v-card-text>
              <v-card-actions>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </div> -->
  </div>
</template>

<script scoped>
export default {
  name: "FollowButtons",
  data: function () {
    return {
      dialog: {
        dialogm1: '',
        dialog: false,
        dialogm2: '',
        dialog2: false,
      },
    }
  },
}
</script>

<style>


</style>
