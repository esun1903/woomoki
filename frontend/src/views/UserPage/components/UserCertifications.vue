<template>
  <v-row>
    <v-col
      v-for="(card, $idx) in cards"
      :key="$idx"
      class="d-flex child-flex"
      cols="4"
    >
      <v-img
        :src="card.img"
        :lazy-src="card.img"
        aspect-ratio="1"
        class="grey lighten-2 cursor_test"
        @click="CertificationDetail"
      >
        <template v-slot:placeholder>
          <v-row
            class="fill-height ma-0"
            align="center"
            justify="center"
          >
            <v-progress-circular
              indeterminate
              color="grey lighten-5"
            ></v-progress-circular>
          </v-row>
        </template>
      </v-img>
    </v-col>
    
  </v-row>
</template>

<script>
import axios from 'axios'

export default {
  name: "UserCertifications",
  data: function () {
    return {
      cards: [],
      limit : 0,
    }
  },
  methods: {
    UserCertification($state) {
      const userid = 0 
      axios.get(`http://127.0.0.1:8080/userCertification/${userid}`, {
        params: {
          limit : this.limit ,
          },
        })
        .then(({data}) => {
          console.log(data)
          if (data.length) {
            this.cards = this.cards.concat(data);
            this.limit += 3;
            $state.loaded();
          } else {
            $state.complete();
          }
        })
    },
    CertificationDetail: function () {
      console.log("CertificationDetail")
      // 인증 디테일 페이지로 이동
      // this.$push()
    },
  },
  mounted() {
    this.UserCertification();
  }
}
</script>

<style scoped>

/* .certification-group {
  display: flex;
  flex-direction: row;
}

.certification-img {
  width: 100px;
  height: 100px;
}

.certification-container {
  width: 1000px;
  margin-left: -280px;
} */

.certification-feed {
  margin-left: -280px;
}

.content {
  position: relative;
  width: 100%;
  max-width: 400px;
  margin: auto;
  overflow: hidden;
}


.content:hover .content-details {
  top: 50%;
  left: 50%;
  opacity: 1;
}

.content-overlay {
  width: 100%;
  height: 100%;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  opacity: 1;
  -webkit-transition: all 0.4s ease-in-out 0s;
  -moz-transition: all 0.4s ease-in-out 0s;
  transition: all 0.4s ease-in-out 0;
}

.content-overlay:hover {
  opacity: 0.6;
}

.content-details {
  text-align: center;
  width: 100;
  top: 50%;
  left: 50%;
  opacity: 0;
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  -webkit-transition: all 0.3s ease-in-out 0s;
  -moz-transition: all 0.3s ease-in-out 0s;
  transition: all 0.3s ease-in-out 0s;
}

.fadeIn-bottom {
  top: 70%;
}

.cursor_test {cursor: pointer;}
</style>
