<template>
  <div id="chart">
    <apexchart type="pie" width="380" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>

export default {
  name: "GraphLine",
  components: {
  },
  data: function () {
    return {
      series: [4, 5, 1, 1, 2, 2],
          chartOptions: {
            chart: {
              width: 380,
              type: 'pie',
            },
            labels: ['운동', '자산', '취미', '생활습관', '독서', '공부'],
            responsive: [{
              breakpoint: 50,
              options: {
                chart: {
                  width: 200
                },
                legend: {
                  show: false,
                  position: 'left',
                  horizontalAlign: 'left',
                  floating: true,
                }
              }
            }]
    }
  }
}
}
</script>

<style>

</style>