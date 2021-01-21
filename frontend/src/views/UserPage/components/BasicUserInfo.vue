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
      <span class="user-id">rladydals123</span>
      <span class="user-level">👩‍💼 Lv. 17</span>
      <div class="user-introduce">안녕하세용~~ㅎㅎ</div>
      <div>나의 캐시 : 3000원</div>
      <div class="follow-info">
        <div>
          <div>팔로워</div>
          <div>130</div>
        </div>
        <div>
          <div>팔로잉</div>
          <div>128</div>
        </div>
      </div>
      <div class="btn-profile">
        <v-btn class="btn-margin">팔로우</v-btn>
        <v-btn class="btn-margin">프로필 편집</v-btn>
        <v-btn class="btn-margin">회원 탈퇴</v-btn>
      </div>
    </div>
</div>
</template>

<script>

export default {
  name: "BasicUserInfo",
  data: () => ({
    icons: {
    }
  })
}
</script>

<style>

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

.user-level {
  font-size: 15px;
}

.user-introduce {
  font-size: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
}

.follow-info {
  font-size: 30px;
  margin-right: 30px;
  margin-top: 10px;
  display: flex;
  justify-content: space-around;
}

.btn-profile {
  margin-top: 20px;
}

.btn-profile .btn-margin {
  margin-right: 10px;
}

.positon-center {
  justify-content: center;
}

</style>