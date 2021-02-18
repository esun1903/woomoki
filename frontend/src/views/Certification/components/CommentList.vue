<template>
  <div v-if="update">
    <v-list three-line>
      <template>
        <v-divider></v-divider>
        <v-list-item :key="comment.index">
          <router-link :to="{ name: 'UserPage', params: { userNickname: comment.nickname }}">
          <v-list-item-avatar size="55">
            <v-img :src="comment.img"></v-img>
          </v-list-item-avatar>
          </router-link>

          <v-list-item-content>
            <v-list-item-title>
              <router-link :to="{ name: 'UserPage', params: { userNickname: comment.nickname }}">
                <span style="font-size:15px; color:black;">{{comment.nickname}}</span>
              </router-link>
              <span class="date" style="">{{comment.create_date}}</span>
            </v-list-item-title>
            <v-list-item-subtitle v-text="comment.content"></v-list-item-subtitle>
          </v-list-item-content>
          <v-btn icon color="#AED864" @click="updateConfirm" v-if="checkUser">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-dialog v-model="dialog" persistent max-width="290" v-if="checkUser">
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon color="#EF5350" v-bind="attrs" v-on="on">
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
    <v-list-item-title id="user-id-list" class="d-flex align-center justify-space-between">
      <router-link :to="{ name: 'UserPage', params: { userNickname: comment.nickname }}">
        <span class="nickname-bold" style="font-size:15px; color:black;">
          {{comment.nickname}} 
        </span>
      </router-link>
      <span class="date">
        {{comment.create_date}}
      </span>
    </v-list-item-title>
    <validation-observer ref="observer">
      <v-form @submit.prevent="updateComment">
        <validation-provider v-slot="{ errors }" rules="required">
          <v-text-field color="#AED864" outlined dense v-model="comment.content" :error-messages="errors"></v-text-field>
        </validation-provider>
      </v-form>
    </validation-observer>
    <div class="back-ok-btn">
      <v-btn icon color="#EF5350" v-on:click="back()">
        <v-icon>fas fa-arrow-left</v-icon>
      </v-btn>
      <v-btn icon color="#AED864" @click="updateComment()">
        <v-icon>fas fa-check</v-icon>
      </v-btn>
    </div>
  </div>
</template>

<script>
  import {
    required
  } from 'vee-validate/dist/rules'
  import {
    extend,
    ValidationProvider,
    setInteractionMode,
    ValidationObserver
  } from 'vee-validate'
  import {
    mapState
  } from "vuex";
  import axios from "axios";

  setInteractionMode('eager')

  extend('required', {
    ...required,
    message: '댓글은 필수 항목입니다'
  })

  export default {
    name: "CommentList",
    data() {
      return {
        update: true,
        dialog: false,
        checkUser: false,
      };
    },
    components: {
      ValidationObserver,
      ValidationProvider
    },
    props: {
      comment: Object,
      nickname: String,
      profileImg: String,
    },
    computed: {
      ...mapState('UserStore', ['user']),
    },
    created() {
      const userId = this.$store.state.UserStore.user.user_id;
      // console.log("userid: "+userId);
      const cmtUserId = this.comment.user_id;
      // console.log("cmtUserId: "+cmtUserId);
      if (userId === cmtUserId) {
        this.checkUser = true;
      }

    },
    methods: {
      updateConfirm() {
        this.update = false;
      },
      async updateComment() {
        const valid = await this.$refs.observer.validate()
        if (valid) {
          axios.put("http://i4a303.p.ssafy.io/api/updateComment", this.comment)
            .then(res => {
              console.log(res);
              this.$router.go(this.$router.currentRoute);
            })
            .catch(err => {
              console.log(err);
            });
        }
      },
      back() {
        this.$router.go(this.$router.currentRoute);
      },
      deleteComment() {
        const comId = this.comment.id;
        axios.delete(`http://i4a303.p.ssafy.io/api/deleteComment/${comId}`)
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
  a {
    text-decoration: none;
  }

  #user-id-list {
    margin-top: 2%;
    margin-bottom: 2%;
  }

  .back-ok-btn {
    float: right;
  }

  .edit-div {
    margin-bottom: 5%;
  }

  .time {
    // margin-left: 4%;
  }

  .date{
    margin-left:1%;
    font-size:5px;
    color:grey;
  }

  .nickname-bold {
        color: black;
        font-size: 17px;
        font-weight: bold;
    }
</style>