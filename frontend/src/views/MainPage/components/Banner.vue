<template>
  <agile :nav-buttons="false" :autoplay-speed="5000" :speed="2500" fade pause-on-hover pause-on-dots-hover autoplay>
      <div class="slide" v-for="(bannerImg, index) in bannerImgs" :key="index">
        <img :src=bannerImg alt="">
      </div>
  </agile>
</template>

<script>
import { VueAgile } from 'vue-agile'
export default {
  name: 'Banner',
  components: { agile: VueAgile, },
  directives: {  },
  data() {
    return {
      bannerImgs : [
        'https://images.unsplash.com/photo-1506260408121-e353d10b87c7?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&w=1600&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ',
		    'https://images.unsplash.com/photo-1523712999610-f77fbcfc3843?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&w=1600&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ',
		    'https://images.unsplash.com/photo-1524260855046-f743b3cdad07?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&w=1600&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ',
		    'https://images.unsplash.com/photo-1526080676457-4544bf0ebba9?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&w=1600&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ',
		    'https://images.unsplash.com/photo-1519681393784-d120267933ba?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&w=1600&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ',
		    'https://images.unsplash.com/photo-1426170042593-200f250dfdaf?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&w=1600&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ',
		    'https://images.unsplash.com/photo-1529815481058-55e5b656f6d6?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&w=1600&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ',
      ]
    };
  },
  mounted() {
    
  },
  methods: {
    
  },
};
</script>

<style lang="scss" scoped>
.agile{
  .agile__list{
    .agile__track{
      .agile__slides{
          .slide{
            display: block;
            height: 65vh;
            object-fit: cover;
            img{
              width: 100%;
            }
        }
      }
    }
    // .agile__actions{
    //   .agile__dots{
    //     bottom: 10px;
    //     flex-direction: column;
    //     right: 30px;
    //     position: absolute;
    //     .agile__dot--current, &:hover{
    //       button{
    //         background-color: #fff;
    //       }
    //     }
    //     .agile__dot{
    //       margin: 5px 0;
    //       button{
    //         background-color: transparent;
    //         border: 1px solid #fff;
    //         cursor: pointer;
    //         display: block;
    //         height: 10px;
    //         font-size: 0;
    //         line-height: 0;
    //         margin: 0;
    //         padding: 0;
    //         transition-duration: .3s;
    //         width: 10px;
    //       }
    //     }
    //   }
    // }
  }
}
</style>