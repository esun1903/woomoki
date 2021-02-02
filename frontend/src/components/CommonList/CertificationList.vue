<template>
<v-container>
    <div class="v-main__wrap">
            <div class="row" v-for="n in 3" :key="n" no-gutters>
                
                <div class="col col-4" @click="CertificationDetail">
                    <div class="v-card v-sheet theme--light" style="height: 100%;">  <v-img :src="require('@/assets/images/kakao.png')"/></div>
                </div>
                <div class="col col-4">
                    <div class="v-card v-sheet theme--light" style="height: 100%;"></div>
                </div>
                <div class="col col-4">
                    <div class="v-card v-sheet theme--light" style="height: 100%;"></div>
                </div>        
            </div>
            </div>
</v-container>
</template>

<script>
    export default {
        name: 'CertificationList',
        components: {
            
        },
        directives: {
            
        },
        data() {
            return {

            };
        },
        mounted() {

        },
        methods: {

        },
    };
</script>

<style lang="scss" scoped>
.v-main__wrap {
    padding-top:10%;
    flex: 1 1 auto;
    max-width: 100%;
    position: relative;
}
</style>