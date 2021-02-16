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
            <v-list-item-title><b>작성자:</b> {{nickname}} <span style = "font-size:5px;"><b>시간:</b> {{comment.create_date}}</span></v-list-item-title>
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
    <v-list-item-title id="user-id-list"><b>작성자:</b> {{nickname}}<div class="time"><b>시간:</b> {{comment.create_date}}</div></v-list-item-title>
    <validation-observer ref="observer">
      <v-form @submit.prevent="updateComment">
        <validation-provider v-slot="{ errors }" rules="required">
          <v-textarea outlined v-model="comment.content" :error-messages="errors" ></v-textarea>
        </validation-provider>
      </v-form>
    </validation-observer>
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
    message: '{_field_}(은)는 필수 항목입니다'
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
          axios.put("http://localhost:8080/updateComment", this.comment)
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

  .back-ok-btn {
    float: right;
  }

  .edit-div {
    margin-bottom: 5%;
  }
  .time{
    margin-left:4%;
  }
</style>