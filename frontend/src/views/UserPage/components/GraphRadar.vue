<template>
  <div id="chart">
    <apexchart type="radar" width="300" height="300" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
export default {
  name: "GraphRadar",
  data: function () {
    return {
      series: [{
            name: 'Series 1',
            data: [80, 50, 30, 40, 100, 20],
          }],
          chartOptions: {
            chart: {
              height: 350,
              type: 'radar',
            },
            title: {
              text: ''
            },
            xaxis: {
              categories: ['운동', '공부', '생활습관', '취미', '자산', '독서']
            }
          },
    }
  }
}
</script>

<style>

</style>