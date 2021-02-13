<template>
  <div>
    <v-list three-line>
      <template v-for="(item, index) in comment">
        <!-- <v-subheader v-if="item.header" :key="item.header" v-text="item.header"></v-subheader> -->

        <!-- <v-divider v-if="item.divider" :key="index" :inset="item.inset"></v-divider> -->

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

    // data: () => ({
    //   items: [{
    //       header: '댓글 리스트'
    //     },
    //     {
    //       avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
    //       title: 'ssafy',
    //       subtitle: `멋있다~`,
    //     },
    //     {
    //       divider: true,
    //       inset: true
    //     },
    //     {
    //       avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
    //       title: 'apple',
    //       subtitle: `멋있다`,
    //     },
    //     {
    //       divider: true,
    //       inset: true
    //     },
    //   ],
    // }),

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
        console.log("comment 들어옴 ");
        const certId = this.$route.params.certid;
        console.log(certId);
        axios.get(`http://localhost:8080/commentList/${certId}`)
          .then((response) => {
            console.log(response.data);
            this.comment = response.data;
            // for (const i in comments) {
            //   this.comment=comments[i]
            //   const comment = comments[i];
            //   console.log(comment)
            // }
            
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