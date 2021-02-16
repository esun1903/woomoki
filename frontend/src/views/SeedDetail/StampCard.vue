<template>
  <div class="card-box">
    <v-card>
      <v-toolbar
        flat
        color="transparent"
      >
        <v-toolbar-title> 
          <span>{{ user.nickname }}님의 {{ seedId }}챌린지 도장카드</span>
        </v-toolbar-title>
      </v-toolbar>

      <v-container>
      </v-container>
      <v-divider></v-divider>

      <v-card-actions>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import {mapState} from "vuex";
export default {
  name: 'StampCard',
  components: {  },
  directives: {  },
  data() {
    return {
      userNickname: '',
      userId: this.$route.params.userId,
      seedId: this.$route.params.cngId,
    };
  },
  mounted() {
    
  },
  computed: {
    ...mapState('UserStore', ['user']),
  },
  methods: {
    
  },
};
</script>

<style lang="scss" scoped>
.card-box {
  display: flex;
  justify-content: center;
  .v-card{
    width: 50vw;
    height: 50vh;
    margin-top: 12vh;
    .v-toolbar{
      .v-toolbar__content{
        .v-toolbar__title{
          
        }
      }
    }
    .container{

    }
    .v-divider{

    }
    .v-card__actions{

    }
}
}
</style>