<template>
  <div>
    <v-card>
      <v-img :src=ChallengeImg @click="goChallengeDetail(challenge.id)"></v-img>
      <v-chip id="category-chip" :ripple="false"> {{ challenge.category }}</v-chip>
      <div>
        <v-card-title>
          {{challenge.title}}
        </v-card-title>
      </div>
      <div id="card-bottom">
        <div>
          <v-chip label :ripple="false">
            {{ challenge.term }}
          </v-chip>
          <v-chip label :ripple="false">
            {{ challenge.time }}
          </v-chip>
        </div>
        <v-btn icon @click.native="getScrap">
          <v-icon :color="scrapped ? 'red' : '' ">mdi-heart</v-icon>
        </v-btn>
      </div>
    </v-card>
  </div>
</template>

<script>
export default {
  name: 'ChallengeCard',
  components: {  },
  directives: {  },
  props: {
    challenge: Object, 
  },
  data() {
    return {
      scrapped: false,
    };
  },
  mounted() {
    
  },
  methods: {
    // 해당 게시글 아이디 담아줘야해
    goChallengeDetail: function (challengeId) {
      this.$router.push({ name: 'ChallengeDetail', params: { challengeId: challengeId } }) 
    },
    getScrap: function () {
      if (this.scrapped) {
        this.scrapped = false
        // 스크랩 기능 구현 아직 안 함.....
      } else {
        this.scrapped = true
      }
    }
  },
  computed: {
    ChallengeImg: function () {
      return this.challenge.src 
    },
  }
};
</script>

<style lang="scss" scoped>
.v-card {
 height: 280px;
 width: 300px;
}
.v-responsive__content { 
  max-height: 157.38px !important;
  overflow: hidden !important;
}
.v-chip {
  height: 25px !important;
  font-size: 12px !important;
  margin-top: 10px;
  margin-left: 10px;
  justify-content: center;
}
.v-responsive {
  max-height: 55%;
}
#category-chip {
  width: 70px;
  right: -68%;
  font-weight: bold;
  background-color: rgb(143, 209, 165);
}
.v-chip--label {
  margin-top: 5px;
}
.v-card__title {
  // margin-top: 7px;
  margin-left: 10px;
  padding: 0;
  font-weight: bold;
}
#card-bottom {
  display: flex;
  direction: column;
  align-items: center;
  justify-content: space-between;
  margin-top:10px;
}
.v-btn {
  background-color: trasparent;
  margin-right: 5px;
}
</style>