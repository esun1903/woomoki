<template>
  <div class="card-box">
    <v-card>
      <v-toolbar
        flat
        color="transparent"
      >
        <v-toolbar-title> 
          <span>
            <p class="nickname">{{ user.nickname }}</p>
            님의
            <p class="seed">🌱</p>
            <p class="seed-title">( "{{ seedTitle }}" )</p>
            <p class="card-name">도장 카드</p>
          </span>
        </v-toolbar-title>
      </v-toolbar>
      <v-divider></v-divider>
      <v-container>
        <div v-for="i in week" :key="i">
          <div v-for="j in day" :key="j">
            <div :class=stampColor(i,j) @click="goDetailorInsert(i, j)"></div>
          </div>
        </div>  
      </v-container>
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
      console.log(this.coloringInfo)
      return (a, b) => {
        const ordinal = (a-1) * this.day + (b-1)
        console.log('이게 바로 n번째다')
        console.log(ordinal)
        console.log(this.coloringInfo.length)
        if (ordinal < this.coloringInfo.length) {
          if (this.coloringInfo[ordinal][1] === 1) {
            return 'confirmed-stamp'
          } else {
            return 'unconfirmed-stamp'
          }
        } else {
          return 'empty-stamp'
        }

      }
    },

  },
  methods: {
    getSeedInfo: function () {
      const seedId = {}
      seedId["cgId"] = this.seedId
      axios.get(`http://127.0.0.1:8080/detailChallenge/${this.seedId}`, seedId)
        .then((res) => {
          this.seedTitle = res.data.title
          this.cngOwner = res.data.user_id
        })
        .catch((err) => {
          console.log('왕 실패')
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
    goDetailorInsert: function (a, b) {
      const ordinal = (a-1) * this.day + (b-1)
      console.log('글 쓰러 간 다앗')
      console.log(a)
      console.log(b)
      if (ordinal >= this.coloringInfo.length) {
        this.$router.push({ name: 'CertificationInsert', params: { cngId: this.seedId, currentWeek: a, currentDay: b } }) 
      } else {
        const CertId = this.coloringInfo[ordinal][0]
        this.$router.push({ name: 'CertificationDetail', params: { cngUserId: this.cngOwner, cngId: this.seedId, certId: CertId } })
      }
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
      display: flex;
      justify-content: center;
      margin-top: 1.3vh;
      .v-toolbar__content{      
        .v-toolbar__title{  
          span{
            display: flex;
            .nickname{
              font-size: 1.3rem;
              font-weight: bold;
              color: #AED864;
              margin-right: 0.6rem;
            }
            .seed{
              margin-left: 1rem;
            }
            .seed-title{
              margin: 0 0.5rem;
              color: rgb(107, 107, 107);
            }
            .card-name {
              font-weight: bold;
              margin-left: 0.5rem;
              font-size: 1.3rem;
            }
          }
        }
      }
    }
    .container{ 
      padding: 5% 0;
      div{
        display: flex;
        justify-content: center;
        div{
          margin-bottom: 1.5%;
          margin-right: 1%;
          text-align: center;
          .confirmed-stamp {
            height: 5.5vh;
            width: 16vh;
            border-radius: 50%;
            display: inline-block;
            color: #43A047;
            font-size: 0.9rem;
            font-weight: 700;
            border: 0.25rem solid #43A047;
            padding: 5% 4%;
            text-transform: uppercase;
            border-radius: 1rem;
          }
          .confirmed-stamp:before{
            content: "confirmed";
          }
          .unconfirmed-stamp {
            height: 5.5vh;
            width: 16vh;
            border-radius: 50%;
            display: inline-block;
            color: #BCAAA4;
            font-size: 0.85rem;
            font-weight: 700;
            border: 0.25rem solid #BCAAA4;
            padding: 5% 4%;
            text-transform: uppercase;
            border-radius: 1rem;
          } 
          .unconfirmed-stamp:before {
            content: "unconfirmed";
          }
          .empty-stamp{
            height: 5.5vh;
            width: 16vh;
            border-radius: 50%;
            display: inline-block;
            color: #BDBDBD;
            font-size: 0.9rem;
            font-weight: 700;
            border: 0.25rem solid #BDBDBD;
            padding: 5% 4%;
            text-transform: uppercase;
            border-radius: 1rem;
          }
          .empty-stamp:before {
            content: "empty"
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
</style>