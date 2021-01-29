<template>
  <div id="feed">
    <SearchBar id="feed-component"/>
    <div id="feed-toggle">
      <v-btn-toggle
        v-model="toggle_exclusive"
        mandatory
      >
        <v-btn @click="openAll">전체유저의 인증샷</v-btn>
        <v-btn @click="openFollowers">팔로워들의 인증샷</v-btn>
      </v-btn-toggle>
      <v-card>
        <FeedAll id="feed-component-result"/>
        <!-- <FeedFollower id="feed-component-result"/> -->
      </v-card>
    </div>

  </div>
</template>

<script>
import SearchBar from "@/views/Feed/components/SearchBar.vue"
import FeedAll from "@/views/Feed/components/FeedAll.vue"
// import FeedFollower from "@/views/Feed/components/FeedFollower.vue"
export default {
  name: 'Feed',
  components: { SearchBar, FeedAll },
  directives: {  },
  data() {
    return {
      all: true,
      follower: false,
    };
  },
  mounted() {
  },
  methods: {
    // openAll: function () {
    //   this.all = true,
    //   this.follower = false
    // },
    // openFollowers: function () {
    //   this.all = false,
    //   this.follower = true,
    // }
  },                     
};
</script>

<style lang="scss" scoped>
#feed {
  margin-top: 120px;
}
.v-item-group {
  display: flex;
  justify-content: end;
}
#feed-component {
  display: flex;
  justify-content: center;
  height: 98px;
}
#feed-component-result {
  display: block;
  justify-content: center;
  margin-top: 40px;
  margin-bottom: 800px;
}
.searchTerm {
  width: 100%;
  border: 3px solid #04915b;
  border-right: none;
  padding: 20px;
  height: 100%;
  left: 40%;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #9DBFAF;
  font-size: 22px;
}
</style>