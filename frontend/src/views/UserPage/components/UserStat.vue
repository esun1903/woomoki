<template>
  <div class="stat-info">
    <div>
      <span class="stat-name">당신이 좋아하는 카테고리</span>
      <img src="https://lh3.googleusercontent.com/proxy/ZPbr0DIWhpHXE1bEyZPd3fHwZq-sx1f5MGDxJPYxYZS6I6Xg6wqsNuhWV0DyFOnWfrPYftFyx4IEikKgt-T0bO6ffQxQB_smoancZmTiXMDLuNCC5PttHGZtVuAy5KvHL5Su" alt="" class="stat-data">
    </div>
    <div>
      <span class="stat-name">파이형 그래프 통계 수치</span>
      <img src="https://docs.tibco.com/pub/spotfire_server/11.0.0/doc/html/ko-KR/TIB_sfire_bauthor-consumer_usersguide/GUID-5E23F5F5-6290-4780-959F-D4E403B4B1EC-display.png" alt="" class="stat-data">
    </div>
    <div>
      <span class="stat-name">히스토 그램 통계</span>
      <img src="https://lh3.googleusercontent.com/proxy/hIgKJj7wVKknoyGMMr6RyaEwFZS_WXPVEFHkIqLTZA_jJDuAOecX04jxitqd4Y0Bx2rO7YaqZssUex6YiBdgS_srly6aP8WE-Q7Gyuf2ZLgTLitG3E0Tcqwio_r-1lPtYew6_9fb_nZEx_QDtGFoOSgZUpRm5oji_XKaqGkkKA_wtATeZ8_7R-8x3qi7ld6lbUw" alt="" class="stat-data">
    </div>
  </div>
</template>

<script>
  export default {
    name: "UserStat",
  
  }
</script>

<style>

.stat-info {
  display: -webkit-flex;
  display: flex;
  -webkit-justify-content: space-around;
  justify-content: space-around;
}

.stat-info .stat-name {
  display: flex;
  justify-content: center;
}

/* 임시 */
.stat-info .stat-data {
  width: 200px;
  height: 200px;
  display: flex;
}
</style>