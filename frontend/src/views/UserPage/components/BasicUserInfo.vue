<template>
<div>
    <!-- <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="유저사진" class="profile-size"> -->
    <v-avatar 
      color="indigo"
      width="300"
      height="300"
      class="profile-img"
      >
      <span class="white--text headline">사진</span>
    </v-avatar>
    <div class="inline-Info">
      <div class="align-Id-follow-edit">
        <span class="user-id">rladydals123</span>
        <v-btn 
          class="btn-margin"
          plain
          width="50"
          >
          팔로우
        </v-btn>
        <v-btn 
          class="btn-margin"
          plain
          width="100"
          >
          프로필 편집
        </v-btn>
      </div>
      <div class="user-level">👩‍💼 Lv. 17</div>
      <div class="user-introduce">안녕하세용~~ㅎㅎ</div>
      <div class="user-wallet">나의 캐시 : 3000원</div>
      <div class="follow-info">
        <div>
          <v-row justify="center">
            <v-dialog
              v-model="dialog.dialog"
              scrollable
              max-width="300px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="black"
                  plain
                  v-bind="attrs"
                  v-on="on"
                >
                  <div>팔로잉</div>
                  <div>116</div>
                </v-btn>
              </template>
              <v-card>
                  <v-card-title class="title-close-inline">팔로잉
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
                  color="black"
                  plain
                  v-bind="attrs"
                  v-on="on"
                >
                  <div>팔로잉</div>
                  <div>116</div>
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
        </div>
      </div>
    </div>
</div>
</template>

<script>

export default {
  name: "BasicUserInfo",
  data: function () {
    return {
      dialog: {
        dialogm1: '',
        dialog: false,
        dialogm2: '',
        dialog2: false,
      }
    }
  },
  methods: {
    BasicUserInfo: function () {
      // axios.post("??")
    }
  }
}
</script>

<style>
/* 안됨..
@import "./BasicUserInfo.scss"; */

.inline-Info {
  display: inline-block;
  margin-top: 100px;
}

.profile-img {
  margin-right: 150px;
  margin-bottom: 200px;
}

.user-id {
  font-size: 30px;
  margin-right: 30px;
}

.align-Id-follow-edit {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}

.user-level {
  font-size: 15px;
  margin-bottom: 20px;
}

.user-introduce {
  font-size: 20px;
  margin-top: 10px;
  margin-bottom: 20px;
}

.user-wallet {
  margin-bottom: 30px;
}

.follow-info {
  font-size: 30px;
  margin-right: 30px;
  margin-top: 10px;
  display: flex;
  justify-content: space-around;
}

.btn-profile .btn-margin {
  margin-right: 10px;
}

.positon-center {
  justify-content: center;
}

.btn {
  opacity: 1;
}
  
.title-close-inline {
  display: flex;
  justify-content: space-between;

}
</style>