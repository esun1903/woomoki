<template>
  <div>
    <v-list three-line>
      <template v-for="(item, index) in comment">
        <!-- <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader> -->

        <v-divider :key="index" :inset="item.inset"></v-divider>

        <v-list-item :key="item.user_id">
          <!-- <v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar> -->

          <v-list-item-content>
            <v-list-item-title>{{item.user_id}}</v-list-item-title>
            <v-list-item-subtitle v-text="item.content"></v-list-item-subtitle>
          </v-list-item-content>
          <v-btn icon color="indigo" @click="updateComment(index)">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-btn icon color="red" @click="deleteComment(index)">
            <v-icon>mdi-trash-can</v-icon>
          </v-btn>
        </v-list-item>
      </template>
    </v-list>
  </div>
</template>

<script>
  import axios from "axios";
  export default {
    name: 'CommentList',
    components: {},
    directives: {},
    data() {
            return {
                comment: [],
                
            };
        },
    mounted() {

    },
    created() {
      this.detailComment();
    },
    methods: {

      detailComment: function () {
        const certId = this.$route.params.certid;
        console.log(certId);
        axios.get(`http://localhost:8080/commentList/${certId}`)
          .then((response) => {
            console.log(response.data);
            this.comment = response.data;
            const userId = this.comment.user_id;
            console.log("uid: "+userId);
          })
          .catch((err) => {
            console.log(err)
          })
      },
    },
  };
</script>

<style lang="scss" scoped>

</style>