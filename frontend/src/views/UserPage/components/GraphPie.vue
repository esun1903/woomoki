<template>
  <div id="chart">
    <apexchart type="pie" width="400" height="400" :options="chartOptions" :series="series"></apexchart>
    <v-btn @click="CategoryPer">test</v-btn>
  </div>
</template>

<script>
import axios from 'axios'
import {mapState} from "vuex";

export default {
  name: "GraphLine",
  components: {
  },
  data: function () {
    return {
      seeds: [],
      series: [0, 0, 0, 0, 0, 0],
          chartOptions: {
            fill: {colors: ['#33691E', '#558B2F', '#689F38', '#7CB342', '#9CCC65', '#9CCC65']},
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
    },
    methods: {
      CategoryPer: function () {
        const id = {};
        id["id"] = this.user.user_id
        console.log(id.id)
        axios.get(`http://localhost:8080/challengeResultSort/${id.id}/0`)
          .then((res) => {
            const seeds = res.data
            // seeds.sort(function(a,b) {
              //   return a.id > b.id ? -1 : a.id < b.id ? 1 : 0;
            // })
            // seeds.splice(8)
            this.seeds = seeds
          
            for (var i=0; i < this.seeds.length; i++) {
              if (this.seeds[i].category_id === 1) {
                this.series[0] += 1
              } else if (this.seeds[i].category_id === 2) {
                this.series[1] += 1
              } else if (this.seeds[i].category_id === 3) {
                this.series[2] += 1
              } else if (this.seeds[i].category_id === 4) {
                this.series[3] += 1
              } else if (this.seeds[i].category_id === 5) {
                this.series[4] += 1
              } else {
                this.series[5] += 1
              }
            }
          console.log(this.series)
          })
          .catch((err) => {
            console.log(err)
          })
      }
    },
  computed: {
  ...mapState('UserStore', ['user']),
  },
  mounted() {
    // this.CategoryPer();
  }

}
</script>

<style scoped>

</style>