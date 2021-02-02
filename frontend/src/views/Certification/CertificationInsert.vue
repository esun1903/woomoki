<template>
<v-app id="temp">
     <v-container fluid>
            <v-layout wrap>
                 <v-flex xs12 sm4>
    <v-file-input label="인증사진" outlined multiple dense :rules="rules"
        accept="image/png, image/jpeg, image/bmp, image/jpg" prepend-icon="mdi-camera"></v-file-input>

                    <v-combobox multiple v-model="select" label="Tags" small-chips deletable-chips
                        class="tag-input" :search-input.sync="search">
                    </v-combobox>
                </v-flex>

            </v-layout>
        </v-container>
    </v-app>

</template>

<script>
    export default {
        name: 'CertificationInsert',
        components: {},
        directives: {},
        data() {
    return {
      select: [],
      items: [],
      search: "" //sync search
    };
  },
  mounted() {

        },
  methods: {

  }
    };
</script>

<style lang="scss" scoped>

#temp{
    margin-top:10%;
}


.tag-input span.v-chip::before {
    content: "label";
    font-weight: normal;
    font-style: normal;
    font-size: 20px;
    line-height: 1;
    letter-spacing: normal;
    text-transform: none;
    display: inline-block;
    white-space: nowrap;
    word-wrap: normal;
    direction: ltr;
    -webkit-font-feature-settings: 'liga';
    -webkit-font-smoothing: antialiased;
}
</style>
