<template>
  <v-app class="certification-feed">
    <v-container fluid>
      <v-row dense>
        <v-col
          v-for="card in cards"
          :key="card.title"
          :cols="card.flex"
        >
          <v-card>
            <v-img
              :src="card.src"
              class="white--text align-end content"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              width="300px"
              height="300px"
            >
              <v-card-title v-text="card.title"></v-card-title>
              <v-btn icon class="content-overlay content-details fadeIn-bottom">
                <!-- <v-icon size="64">mdi-heart</v-icon> -->
                <h1 style="color: white;">매일매일 운동하기</h1>
              </v-btn>
            </v-img>

            <v-card-actions>
              <v-spacer></v-spacer>

            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>

  </v-app>
  <!-- <div class="certification-group certification-container">
    <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="" class="certification-img">
    <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="" class="certification-img">
    <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="" class="certification-img">
    <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="" class="certification-img">
    <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="" class="certification-img">
    <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="" class="certification-img">
    <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="" class="certification-img">
    <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="" class="certification-img">
    <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="" class="certification-img">
    <img src="https://cdn.clien.net/web/api/file/F01/10333937/4379c3e76c59d8.jpg" alt="" class="certification-img">
  </div> -->
</template>

<script>
export default {
  name: "UserCertifications",
  data: function () {
    return {
      cards: [
        { title: '9주차 인증샷', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 4 },
        { title: '8주차 인증샷', src: 'https://cdn.vuetifyjs.com/images/cards/road.jpg', flex: 4 },
        { title: '7주차 인증샷', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 4 },
        { title: '6주차 인증샷', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 4 },
        { title: '5주차 인증샷', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 4 },
        { title: '4주차 인증샷', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 4 },
        { title: '3주차 인증샷', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 4 },
        { title: '2주차 인증샷', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 4 },
        { title: '1주차 인증샷', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 4 },
      ],
    }
  },
  methods: {
    UserCertification: function () {
      // axios.post("?")
    },
    CertificationDetail: function () {
      // 인증 디테일 페이지로 이동
      // this.$push()
    }
  }
}
</script>

<style>

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
</style>