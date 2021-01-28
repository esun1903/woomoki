<template>
  <div id="category-buttons">
    <v-tooltip bottom v-for="(item, index) in items" :key="index"> 
      <template v-slot:activator="{ on }">
        <v-avatar v-on="on" @click="categorySearch(item)">
          <v-icon dark v-text="item.icon"></v-icon>
        </v-avatar>
      </template>
      <!-- 건강 대신 item.text가 나와야 하는데;;;; -->
      <span>건강</span>
    </v-tooltip>
  </div>
</template>

<script>
export default {
  name: 'CategoryButtons',
  components: {  },
  directives: {  },
  data() {
    return {
      categorySearchedResult: "",
      items: [
        {
          text: '건강',
          icon: 'mdi-dumbbell',
          color: 'indigo'
        },
        {
          text: '생활습관',
          icon: 'mdi-calendar-check',
          color: 'red'
        },
        {
          text: '독서',
          icon: 'mdi-bookshelf',
          color: 'green'
        },
        {
          text: '자산',
          icon: 'mdi-cash-usd-outline',
          color: 'yellow'
        },
        {
          text: '자기계발',
          icon: 'mdi-school',
          color: 'pink'
        },
        {
          text: '취미',
          icon: 'mdi-piano',
          color: 'orange'
        },
      ],
    };
  },
  mounted() {
    
  },
  methods: {
    categorySearch: function(category) {
      // category에 해당하는 것 갖고와서 categorySearchResult에 넣어주기
      // axios.get()
      console.log(category);
      this.$emit('category-searched', this.categorySearchedResult)
    }
  },
};
</script>

<style lang="scss" scoped>
#category-buttons {
  display: flex;
  justify-content: center;
  align-items: center;
}
.v-avatar {
  margin: 0 20px;
  height: 70px !important;
  width: 70px !important;
  margin-top: 14px !important;
  margin-bottom: 14px !important;
  background-color:  #04915b;
}
.mdi:before {
  font-size: xx-large;
}
.v-tooltip__content {
  width: 70px !important;
  text-align: center;
}
</style>