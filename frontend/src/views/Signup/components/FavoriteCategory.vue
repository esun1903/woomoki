<template>
  <div>
    <v-app>
      <v-card id="fav-card">
        <v-toolbar
          flat
          color="transparent"
        >
          <v-toolbar-title>관심있는 카테고리를 담아주세요!</v-toolbar-title>
        </v-toolbar>
    
        <v-container class="py-0">
          <v-row align="center" justify="start">
            <v-col
              v-for="(selection, i) in getSelections"
              :key="selection.text"
              class="shrink"
            >
              <v-chip outlined
                :disabled="loading"
                close
                @click:close="selected.splice(i, 1)"
              >
                <v-icon
                  left
                  v-text="selection.icon"
                ></v-icon>
                {{ selection.text }}
              </v-chip>
            </v-col>
          </v-row>
        </v-container>
    
        <v-divider v-if="!isAllSelected"></v-divider>
    
        <v-list>
          <template v-for="item in items">
            <v-list-item
              v-if="!selected.includes(item)"
              :key="item.text"
              :disabled="loading"
              @click="selected.push(item)"
            >
              <v-list-item-avatar>
                <v-icon
                  :disabled="loading"
                  v-text="item.icon"
                ></v-icon>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title v-text="item.text"></v-list-item-title>
                <v-list-item-subtitle v-text="item.content"></v-list-item-subtitle>
              </v-list-item-content>

            </v-list-item>
          </template>
        </v-list>
    
        <v-divider></v-divider>
    
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            :disabled="!selected.length"
            :loading="loading"
            color="green"
            text
            @click="GO_MAINPAGE"
          >GRITREE 시작하기!</v-btn>
        </v-card-actions>
      </v-card>
    </v-app>
  </div>
</template>

<script>
import { mapState, mapGetters, mapMutations } from 'vuex'
export default {
  name: "FavoriteCategory",
  components: {},
  directives: {},
  data: function() {
    return {

    };
  },
  computed: {
    ...mapState(['items', 'loading', 'selected']),
    ...mapGetters({
      isAllSelected: 'isAllSelected',
      getSelections: 'getSelections',
      getFavoriteCategories: 'getFavoriteCategories'
    }),
  },
  mounted() {},
  methods: {
    ...mapMutations(['GO_MAINPAGE']),
    // goMainPage: function () {
    //   this.GO_MAINPAGE
    // }
  }
};
</script>

<style lang="scss" scoped>
#fav-card {
  width: 50%;
  top:15%;
  left: 25%;
  padding: 0px 50px;
}
// .v-toolbar-title {
//   font-weight: bold;
//   text: center;
// }
.v-divider {
  margin-top: 20px;
}
.v-chip{
  color: green !important;
  border-color: green !important;
}
</style>
