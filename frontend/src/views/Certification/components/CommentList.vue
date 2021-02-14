<template>
  <div v-if="update">
    <v-list three-line>
      <template>
        <v-divider></v-divider>
        <v-list-item :key="comment.index">
          <!-- <v-list-item-avatar>
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar> -->

          <v-list-item-content>
            <v-list-item-info><b>작성자:</b> {{comment.user_id}} <b>시간:</b> {{comment.create_date}}</v-list-item-info>
            <v-list-item-subtitle v-text="comment.content"></v-list-item-subtitle>
          </v-list-item-content>
          <v-btn icon color="indigo" @click="updateConfirm" v-if="checkUser">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-dialog v-model="dialog" persistent max-width="290" v-if="checkUser">
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon color="red" v-bind="attrs" v-on="on">
                <v-icon>mdi-trash-can</v-icon>
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="headline">
                댓글을 삭제하시겠습니까?
              </v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="dialog = false">
                  취소
                </v-btn>
                <v-btn color="red darken-1" text @click="dialog = false; deleteComment()">
                  삭제
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-list-item>
      </template>
    </v-list>
  </div>
  <div class="edit-div" v-else>
    <v-divider></v-divider>
    <v-list-item-title id="user-id-list">{{comment.user_id}}</v-list-item-title>
    <v-textarea outlined v-model="comment.content"></v-textarea>
    <div class="back-ok-btn">
    <v-btn icon color="red" v-on:click="back()">
      <v-icon>mdi-arrow-left</v-icon>
    </v-btn>
    <v-btn icon color="green" @click="updateComment()">
      <v-icon>mdi-check-outline</v-icon>
    </v-btn>
    </div>
  </div>
</template>

<script>
  import {
    mapState
  } from "vuex";
  import axios from "axios";

  export default {
    name: "CommentList",
    data() {
      return {
        update: true,
        dialog: false,
        checkUser: false,
      };
    },
    props: {
      comment: Object
    },
    computed: {
      ...mapState('UserStore', ['user']),
    },
    created() {
      const userId = this.$store.state.UserStore.user.user_id;
      // console.log("userid: "+userId);
      const cmtUserId = this.comment.user_id;
      // console.log("cmtUserId: "+cmtUserId);
      if(userId === cmtUserId){
        this.checkUser = true;
      }

    },
    methods: {
      updateConfirm() {
        this.update = false;
      },
      updateComment() {
        axios.put("http://localhost:8080/updateComment", this.comment)
          .then(res => {
            console.log(res);
            this.$router.go(this.$router.currentRoute);
          })
          .catch(err => {
            console.log(err);
          });
      },
      back() {
        this.$router.go(this.$router.currentRoute);
      },
      deleteComment() {
        const comId = this.comment.id;
        axios.delete(`http://localhost:8080/deleteComment/${comId}`)
          .then((response) => {
            console.log(response.data)
            this.$router.go(this.$router.currentRoute);
          })
          .catch((err) => {
            console.log(err)
          })
      }
    },
  };
</script>

<style lang="scss" scoped>
  #user-id-list { 
    margin-top: 2%;
    margin-bottom: 2%;
  }
  .back-ok-btn{
    float: right;
  }
  .edit-div{
    margin-bottom:5%;
  }
</style>