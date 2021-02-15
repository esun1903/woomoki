<template>
  <v-container class="container-size">
    <v-row>
      <v-text-field
        color="light-green lighten-2"
        clear-icon="fas fa-times"
        v-model="SeedInfo.sum_img"
        outlined
        label="썸네일"
        clearable
      ></v-text-field>
    </v-row>
    <v-row>
      <v-text-field
        color="light-green lighten-2"
        v-model="SeedInfo.title"
        outlined
        label="제목"
        readonly
      ></v-text-field>
    </v-row>
    <v-row>
      <v-text-field
        color="light-green lighten-2"
        clear-icon="fas fa-times"
        v-model="SeedInfo.content"
        outlined
        label="내용"
        clearable
      ></v-text-field>
    </v-row>
    <v-row>
      <v-text-field
        color="light-green lighten-2"
        v-model="SeedInfo.example_img"
        outlined
        label="예시 이미지"
        clearable
      ></v-text-field>
    </v-row>
    <v-row>
      <v-text-field
        suffix="원"
        color="light-green lighten-2"
        v-model="SeedInfo.join_deposit"
        outlined
        label="참여 금액"
        readonly
      ></v-text-field>
    </v-row>
    <v-row>
      <v-text-field
        suffix="명"
        color="light-green lighten-2"
        clear-icon="fas fa-times"
        v-model="SeedInfo.max_people"
        outlined
        label="최대 인원"
        clearable
      ></v-text-field>
    </v-row>
    <v-row>
      <v-text-field
        color="light-green lighten-2"
        v-model="dateRangeText"
        outlined
        label="기간"
        readonly
      ></v-text-field>
    </v-row>
    <v-row class="d-flex justify-end">
      <router-link :to="{ name: 'SeedDetail', params: { seedId: this.seedId } }">
        <v-btn
          color="light-green lighten-2 white--text"
          @click="UpdateSeed"
          >
          적용
        </v-btn>
      </router-link>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: "SeedUpdate",
  data: function () {
    return {
      seedId: this.$route.params.seedId,
      SeedInfo: [],
      dates: [],
    }
  },
  methods: {
    getSeedDetail: function () {
      const seedId = this.seedId
      axios.get(`http://127.0.0.1:8080/detailChallenge/${seedId}`)
        .then((res) => {
          console.log(res.data)
          this.SeedInfo = res.data
          this.dates.push(this.SeedInfo.start_date)
          this.dates.push(this.SeedInfo.end_date)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    UpdateSeed: function () {
      const ChangedUserInfo = {
        id : this.seedId,
        category_id: this.SeedInfo.category_id,
        user_id: this.SeedInfo.user_id,
        title: this.SeedInfo.title,
        content: this.SeedInfo.content,
        start_date: this.SeedInfo.start_date,
        end_date: this.SeedInfo.end_date,
        cert_count: this.SeedInfo.cert_count,
        max_people: this.SeedInfo.max_people,
        example_img: this.SeedInfo.example_img,
        join_deposit: this.SeedInfo.join_deposit,
        like_cnt: this.SeedInfo.like_cnt, 
        result : this.SeedInfo.result,
        week: this.SeedInfo.week
      }
      axios.put('http://127.0.0.1:8080/updateChallenge/', ChangedUserInfo)
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
    }
  },
  created() {
    this.getSeedDetail();
  },
  computed: {
    dateRangeText () {
      return this.dates.join(' ~ ')
    },
  }
}
</script>

<style lang="scss" scoped>

a {
  text-decoration: none;
}

.container-size {
  width: 40%;
}

</style>