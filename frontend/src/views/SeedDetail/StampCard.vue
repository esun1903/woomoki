<template>
  <div class="card-box">
    <v-card>
      <v-toolbar
        flat
        color="transparent"
      >
        <v-toolbar-title> 
          <span>{{ user.nickname }}님의 <b>{{ seedTitle }}</b> 챌린지 도장카드</span>
        </v-toolbar-title>
      </v-toolbar>
      <v-divider></v-divider>
      <v-container>
        <!-- week만큼의 세로 동그라미 갯수 -->
        <div v-for="i in week" :key="i">
          <!-- day만큼의 가로 동그라미 갯수 -->
          <div v-for="j in day" :key="j">
            <!-- 원과 설명 -->
            <div :class=this.stampColor(i,j) @click="goCertInfo(i, j)"></div>
             <!-- :color=stampColor(i,j)  -->
          </div>
        </div>  
      </v-container>

      <v-card-actions>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";
import {mapState} from "vuex";
export default {
  name: 'StampCard',
  components: {  },
  directives: {  },
  data() {
    return {
      userId: this.$route.params.userId,
      seedId: this.$route.params.seedId,
      cngOwner: "",
      seedTitle: "",
      day: "",
      week: "",
      cert_count:"",
      result: "",
      coloringInfo: "",
    };
  },
  created () {
    this.drawCircles();
    this.getSeedInfo();
    this.coloringCircles();
  },
  mounted() {
    
  },
  computed: {
    ...mapState('UserStore', ['user']),
    stampColor() {
      return (a, b) => {
        const ordinal = (a-1) * this.day + (b-1)
        console.log('이게 바로 n번째다')
        console.log(ordinal)
        console.log(this.coloringInfo.length)
        if (ordinal < this.coloringInfo.length) {
          if (this.coloringInfo[ordinal][1] === 1) {
            return 'purple-circle'
          } else {
            return 'black-circle'
          }
        } else {
          return 'grey-circle'
        }

      }
    },

  },
  methods: {
    getSeedInfo: function () {
      const seedId = {}
      console.log("이게 챌린지 번호다")
      console.log(this.seedId)
      seedId["cgId"] = this.seedId
      axios.get(`http://127.0.0.1:8080/detailChallenge/${this.seedId}`, seedId)
        .then((res) => {
          console.log('타이틀, 오너 알고 싶다')
          console.log(res)
          this.seedTitle = res.data.title
          this.cngOwner = res.data.user_id
        })
        .catch((err) => {
          console.log(err)
        })      
    },
    drawCircles: function () {
      const seedId = {};
      seedId["cngId"] = this.seedId
      axios.get(`http://127.0.0.1:8080/certification/${this.seedId}`, seedId)
        .then((res) => {
          this.day = res.data.day
          this.week = res.data.week
          console.log(res)
          console.log('무엇을 스케치할까요')
        })
        .catch((err) => {
          console.log(err)
        })
    },
    coloringCircles: function () {
      const Info = {}
      Info["userid"] = this.userId
      Info["cngId"] = this.seedId
      axios.get(`http://127.0.0.1:8080/confirmstatus/${this.userId}/${this.seedId}`, Info)
        .then((res) => {
          console.log(res)
          console.log('무엇을 채색할까요')
          this.coloringInfo = res.data 
        })
        .catch((err) => {
          console.log(err)
        })
    },
    goCertInfo: function (a, b) {
      const ordinal = a * this.week + b
      const CertId = this.coloringInfo[ordinal][0]
      this.$router.push({ name: 'CertificationDetail', params: { cngUserId: this.cngOwner, cngId: this.seedId, certId: CertId } })
    }
  },
};
</script>

<style lang="scss" scoped>
.card-box {
  display: flex;
  justify-content: center;
  .v-card{
    width: 70vw;
    height: auto;
    margin-top: 12vh;
    .v-toolbar{
      .v-toolbar__content{
        .v-toolbar__title{
          
        }
      }
    }
    .container{    
      div{
        display: flex;
        div{
          margin-bottom: 3%;
          margin-right: 3%;
          // .circle {
          //   height: 10vh;
          //   width: 10vh;
          //   background-color: #bbb;
          //   border-radius: 50%;
          //   display: inline-block;
          // }
          .circle-info{

          }
        }
      }
    }
    .v-divider{

    }
    .v-card__actions{

    }
  }
}
.purple-circle {
  height: 10vh;
  width: 10vh;
  background-color: purple;
  border-radius: 50%;
  display: inline-block;
}
.black-circle {
  height: 10vh;
  width: 10vh;
  background-color: black;
  border-radius: 50%;
  display: inline-block;
} 
.grey-circle{
  height: 10vh;
  width: 10vh;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}
</style>