<template>
  <div id="chart">
    <apexchart
      type="line"
      width="400"
      height="400"
      :options="chartOptions"
      :series="series"
    ></apexchart>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "GraphLine",
  data: function() {
    return {
      certifications: [],
      months: [],
      series: [
        {
          name: "횟수",
          data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        }
      ],
      chartOptions: {
        colors: ["#9CCC65"],
        chart: {
          height: 350,
          type: "line",
          zoom: {
            enabled: false
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: "straight"
        },
        title: {
          text: "",
          align: "left"
        },
        grid: {
          row: {
            colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
            opacity: 0.5
          }
        },
        xaxis: {
          categories: [
            "1월",
            "2월",
            "3월",
            "4월",
            "5월",
            "6월",
            "7월",
            "8월",
            "9월",
            "10월",
            "11월",
            "12월"
          ]
        }
      }
    };
  },
  methods: {
    async UserCertification () {
      const userid = this.$store.state.UserStore.user.user_id
      await axios.get(`http://127.0.0.1:8080/userCertification/${userid}`)
        .then((res) => {
          this.certifications = res.data
        })
      var i;
      const tmp = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
      for (i=0; i < this.certifications.length; i++) {
        tmp[Number(this.certifications[i].create_date.slice(5, 7))] += 1
        // this.months.push(Number(this.certifications[i].create_date.slice(5, 7)))
        // console.log(this.series[0].data)
        }
      this.months = tmp
      console.log(this.series[0].data)
      console.log(tmp)
      },
  },
  created() {
    this.UserCertification();
  }
};
</script>

<style scoped></style>
