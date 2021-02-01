<template>
  <v-row class="stat-info">
    <v-col>
      <div class="stat-name">월별 챌린지 인증 횟수</div>
      <GraphLine class="d-flex justify-center"></GraphLine>
    </v-col>
    <v-col>
      <div class="stat-name">좋아하는 카테고리</div>
      <GraphPie class="d-flex justify-center"></GraphPie>
    </v-col>
    <v-col>
      <div class="stat-name">당신의 성향</div>
      <GraphRadar class="d-flex justify-center"></GraphRadar>
    </v-col>
    <v-col>
      <div class="stat-name">챌린지 성공률</div>
      <GraphPer class="d-flex justify-center"></GraphPer>
    </v-col>
  </v-row>
</template>

<script>
import GraphLine from "./GraphLine";
import GraphPie from "./GraphPie";
import GraphRadar from "./GraphRadar";
import GraphPer from "./GraphPer";

export default {
  name: "UserStat",
  components: {
    GraphLine,
    GraphPie,
    GraphRadar,
    GraphPer
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
  -webkit-justify-content: space-around;
  justify-content: space-around;
}

.stat-info .stat-name {
  font-size: 20px;
  display: flex;
  justify-content: center;
}

.v-sheet--offset {
  position: relative;
}
</style>
