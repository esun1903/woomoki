<template>
  <div class="stat-info">
    <div>
      <span class="stat-name">당신이 좋아하는 카테고리</span>
      <img src="https://t1.daumcdn.net/cfile/tistory/0141D44A5127C6BE21" alt="" class="stat-data">
    </div>
    <div>
      <span class="stat-name">파이형 그래프 통계 수치</span>
      <img src="https://docs.tibco.com/pub/spotfire_server/11.0.0/doc/html/ko-KR/TIB_sfire_bauthor-consumer_usersguide/GUID-5E23F5F5-6290-4780-959F-D4E403B4B1EC-display.png" alt="" class="stat-data">
    </div>
    <div>
      <span class="stat-name">히스토그램 통계</span>
      <img src="https://codetorial.net/matplotlib/_images/histogram_00.png" alt="" class="stat-data">
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