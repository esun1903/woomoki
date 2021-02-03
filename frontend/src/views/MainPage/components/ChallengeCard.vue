<template>
  <v-card>
    <v-img :src=ChallengeImg @click="goChallengeDetail(challenge.id)"></v-img>
    <div class="challenge-card-top">
      <v-chip id="category-chip" :ripple="false"> {{ challenge.category }}</v-chip>
      <v-btn icon @click.native="getScrap">
        <v-icon :color="scrapped ? 'red' : '' ">mdi-heart</v-icon>
      </v-btn>
    </div>

    <div>
      <v-card-title>
        {{challenge.title}}
      </v-card-title>
    </div>
    <div class="challenge-card-bottom">
      <div>
        <v-chip label :ripple="false">
          {{ challenge.term }}
        </v-chip>
        <v-chip label :ripple="false">
          {{ challenge.time }}
        </v-chip>
      </div>
    </div>
  </v-card>
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
.v-chip {
  height: 100%;
  font-size: 0.8rem;
  justify-content: center;
  }
.challenge-card-top {
  display: flex;
  justify-content: space-between;
  margin-top: 2%;
}
#category-chip {
  margin-top: 3%;
  margin-left: 3%;
  background-color: rgb(143, 209, 165);
}
.challenge-card-bottom {
  margin-left: 3%;
  div {
    .v-chip {
      margin-bottom: 5%;
      margin-right:2%;
    }
  }
}
.v-card__title {
  font-size: 1.3em;
  font-weight: bold;
  margin: 2% 3%;
  padding: 0;
}
  // .v-image {
  //   height: 30%;
  // }

// .v-card {
//  height: 280px;
//  width: 300px;
// }


// .v-responsive {
//   max-height: 55%;
// }
// #category-chip {
//   width: 70px;
//   right: -68%;
//   font-weight: bold;
//   background-color: rgb(143, 209, 165);
// }
// .v-chip--label {
//   margin-top: 5px;
// }
// .v-card__title {
//   // margin-top: 7px;
//   margin-left: 10px;
//   padding: 0;
//   font-weight: bold;
// }
// #card-bottom {
//   display: flex;
//   direction: column;
//   align-items: center;
//   justify-content: space-between;
//   margin-top:10px;
// }
// .v-btn {
//   background-color: trasparent;
//   margin-right: 5px;
// }


.v-chip {

  div {
    .v-card__title {

    }
  }
}
</style>