<template>
  <v-container>
    <v-row>
      <v-col>
        <v-tabs
          v-model="tab"
          background-color="transparent"
          color="success"
          right
          >
          <v-tab
            style="font-size: 1vw;"
            v-for="item in items"
            :key="item"
            >
            {{ item }}
          </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item
            v-for="item in items"
            :key="item"
            >
            <v-card
              width="100vw"
              color="basil"
              flat
              >
              <v-card-text v-if="item === '모든 유저의 기록'"><FeedAll/></v-card-text>
              <v-card-text v-if="item === '팔로우한 유저의 기록'"><FeedFollower/></v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import FeedAll from "@/views/Feed/components/FeedAll.vue"
import FeedFollower from "@/views/Feed/components/FeedFollower.vue"
export default {
  name: 'Feed',
  components: { 
    FeedAll, FeedFollower
   },
  directives: { 
    
   },
  data: function () {
    return {
      tab: null,
      items: ['모든 유저의 기록', '팔로우한 유저의 기록'],
    };
},
  mounted() {
    
  },
  methods: {
    
  },
};
</script>

<style lang="scss" scoped>
.container {
  width: 80%;
  margin-top: 7%;
}
</style>