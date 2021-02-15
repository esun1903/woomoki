<template>
  <splide-slide>
    <div class="image-box">
      <img :src=CertificationImg>
      <div class="mask">
        <div class="slide-info">
          <div class="datetime">{{ slide.create_date | formatDate }}</div>
          <div class="writer">{{ slide.nickname }}</div>
          <div class="seed-title">{{ slide.title }}</div>
          <div class="content">{{ slide.content }}</div>
          <v-btn @click="goCertDetail">MORE</v-btn>
        </div>
      </div>
    </div>
  </splide-slide>
</template>

<script>
import moment from 'moment'
export default {
  name: 'FeedCard',
  components: {  },
  directives: {  },
  props: {
    slide: Object,
  },
  data() {
    return {
      dialogm1: '',
      dialog: false,
    };
  },
  filters: {
    formatDate: function(value) {
      if (value) {
        return moment(String(value)).format('YYYY년 MM월 DD일 hh:mm')
      }
    },
  }, 
  mounted() {
    
  },
  methods: {
    getChallengeOwner: function () {
      const cngId = {}
      const cngId_num = this.slide.cng_id
      cngId["cgId"] = cngId_num 
      axios.get(`http://127.0.0.1:8080/detailChallenge/${cngId_num}`, cngId)
        .then((res) => {
          const cngUserId = res.data.user_id
          return cngUserId
        })
        .catch((err) => {
          console.log(err)
        })
    },
    goCertDetail: function () {
      const UserId = this.getChallengeOwner()
      this.$router.push({ name: 'CertificationDetail', params: { cngUserId: UserId, cngId: this.slide.cng_id, certId: this.slide.id } })
    },
  },
  computed: {
    CertificationImg: function () {
      console.log('확인룡룡')
      console.log(this.slide)
      return this.slide.img 
    },
  }
};
</script>

<style lang="scss" scoped>
.image-box {
  display:flex;
  justify-content: center;
  position: relative;
  img {
  width: 320px;
  height: 300px;
  object-fit: cover;
  padding: 0 3%;
  margin: 0;
  opacity: 1;
  display: block;
  transition: .5s ease;
  }
  .mask {
    width: 100%;
    height: 300px;
    padding: 5% 10%;
    transition: .5s ease;
    opacity: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    .slide-info {
      width: 100%;
      height: 100%;
      text-align: center;
      color: black;
      .datetime{
        display: flex;
        justify-content: flex-start;
        font-size: 1rem;
      }
      .writer{
        display: flex;
        justify-content: flex-start;
        margin-top:3%;
        margin-bottom:15%;
        font-size: 1rem;
      }
      .seed-title{
        display: flex;
        justify-content: center;
        font-weight: bold;
        font-size: 1.3rem;
        margin-bottom:5%;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
      }
      .content{
        font-size: 1rem;
        margin-bottom:5%;
        height: 30%;
      }
      .btn{
        color: green;
      }
    }
  }
}
.image-box:hover img {
  opacity: 0.4 ;
}

.image-box:hover .mask {
  opacity: 1;
}

.v-card {
  .v-card__text{
    padding: 3%;
    .cert-image {
      img {
        width: 40%;
        height: 150px;
      }
    }
  }
}
</style>