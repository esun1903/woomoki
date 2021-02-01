<template>
  <v-row class="stat-info">
    <v-col>
      <div class="stat-name">월별 챌린지 인증 횟수</div>
      <GraphLine></GraphLine>
    </v-col>
    <v-col>
      <div class="stat-name">좋아하는 카테고리</div>
      <GraphPie></GraphPie>
    </v-col>
    <v-col>
      <div class="stat-name">당신의 성향</div>
      <GraphRadar></GraphRadar>
    </v-col>
  </v-row>
</template>

<script>
import GraphLine from "./GraphLine";
import GraphPie from "./GraphPie";
import GraphRadar from "./GraphRadar";

export default {
  name: "UserStat",
  components: {
    GraphLine,
    GraphPie,
    GraphRadar
  },
  data: function() {
    return {};
  }
};
</script>

<style scoped>
.stat-info {
  display: -webkit-flex;
  display: flex;
  /* display: inline; */
  -webkit-justify-content: space-around;
  justify-content: space-around;
  margin-top: 70px;
}

.stat-info .stat-name {
  margin-bottom: 20px;
  font-size: 20px;
  display: flex;
  justify-content: center;
}

/* 임시 */
.stat-info .stat-data {
  width: 200px;
  height: 200px;
  display: flex;
}

.v-sheet--offset {
  margin-top: 10px;
  position: relative;
}
</style>
