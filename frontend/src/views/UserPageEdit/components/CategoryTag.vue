<template>
    <v-combobox
      v-model="model"
      :items="items"
      :search-input.sync="search"
      hide-selected
      hint="최대 3개 선택할 수 있습니다"
      label="카테고리 추가"
      multiple
      progress
     
      persistent-hint
      small-chips
    >
      <template v-slot:no-data>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title>
              No results matching "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to create a new one
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-combobox>
</template>

<script>
export default {
  data: () => ({
    items: ['운동', '공부', '게임', '자기계발', '다이어트'],
      model: [],
      search: null,
    }),

    watch: {
      model (val) {
        if (val.length > 3) {
          this.$nextTick(() => this.model.pop())
        }
      },
    },
  }
</script>

<style>

</style>