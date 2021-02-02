<template>
  <div id="chart">
    <apexchart type="radar" width="400" height="400" :options="chartOptions" :series="series"></apexchart>
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
            colors : ['#9CCC65'],
            chart: {
              height: 350,
              type: 'radar',
            },
            title: {
              text: ''
            },
            xaxis: {
              categories: ['꾸준함', '활동적', '리더십', '섬세함', '소통', '끈기']
            }
          },
    }
  }
}
</script>

<style scoped>

</style>