<template>
  <div id="chart">
    <apexchart type="pie" width="250" height="250" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>

export default {
  name: "GraphLine",
  components: {
  },
  data: function () {
    return {
      series: [5, 1, 4, 5, 3, 2],
          chartOptions: {
            chart: {
              width: '100%',
              type: 'pie',
            },
            labels: ["운동", "공부", "취미", "자산", "독서", "자기계발"],
            theme: {
              monochrome: {
                enabled: true
              }
            },
            plotOptions: {
              pie: {
                dataLabels: {
                  offset: -5
                }
              }
            },
            title: {
              text: ""
            },
            dataLabels: {
              formatter(val, opts) {
                const name = opts.w.globals.labels[opts.seriesIndex]
                return [name, val.toFixed(1) + '%']
              }
            },
            legend: {
              show: false
            }
          },
  }
}
}
</script>

<style scoped>

</style>