<template>
  <div id="chart">
    <apexchart type="line" width="400" height="400" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
export default {
  name: "GraphLine",
  data: function () {
    return {
      series: [{
              name: "횟수",
              data: [10, 4, 5, 1, 9, 6, 6, 9, 8, 12, 14, 10]
          }],
          chartOptions: {
            colors : ['#80FF00'],
            chart: {
              height: 350,
              type: 'line',
              zoom: {
                enabled: false
              }
            },
            dataLabels: {
              enabled: false
            },
            stroke: {
              curve: 'straight'
            },
            title: {
              text: '',
              align: 'left'
            },
            grid: {
              row: {
                colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                opacity: 0.5
              },
            },
            xaxis: {
              categories: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            }
          },
          
    }
  }
}
</script>

<style scoped>

</style>