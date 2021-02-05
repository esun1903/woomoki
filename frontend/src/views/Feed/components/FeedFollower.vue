<template>
  <carousel-3d @after-slide-change="onAfterSlideChange" @before-slide-change="onBeforeSlideChange" @last-slide="onLastSlide">
    <slide v-for="(certification, i) in certifications" :index="i" :key="certification">
      <figure>
        <img :src="certification['src']">
        <figcaption>
          {{ certification.title }}	
          <br>
          {{ certification.user }}
        </figcaption>
      </figure>
    </slide>
  </carousel-3d>
</template>

<script>
export default {
  name: "FeedFollower",
  components: {},
  directives: {},
  data() {
    return {
      certifications: [
        {
          title: "새벽 5시 기상",
          user: "긍정왕 루피",
          src:
            "https://scontent-sin6-1.cdninstagram.com/v/t51.2885-15/e35/139467207_453277379135963_4639520592442612980_n.jpg?_nc_ht=scontent-sin6-1.cdninstagram.com&_nc_cat=111&_nc_ohc=a5igoRf8yegAX-lp4Q6&tp=1&oh=87d14eaaaaef11d27c4771f2d22aa430&oe=60335784"
        },
        {
          title: "아침 조깅 인증",
          user: "모모모",
          src:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToFHqIGzcHzdOn1iWUW1m3ZIqihbu7wQwM1g&usqp=CAU"
        },
        {
          title: "코스모스 읽기",
          user: "지히히",
          src: "https://pbs.twimg.com/media/DcuP1voVMAAx_eK.jpg"
        },
        {
          title: "일본어 공부하기",
          user: "아이언표",
          src:
            "https://lh3.googleusercontent.com/proxy/amqN1fCiCkW8jRaImeSS6Zp-_IeR7eLRYpLMIsV10m5Tcw1TvPPqDxoCJ23m2eZAPYWF7CVTfLyNd_VqKZmhMRqUhPzq-WOwb0_ykY9j5F2Ml96aAw9xLBsSgKUetJCM88nrXw2vNTWscrcalo9aIFsqN2TrEckI-B3lzfMYJFJjy1hBEco32YLN3vMBOMx-gAqOIwPda93kE7kd4RJ7_WjdRnNqFYaaw4IsLpeTRL4nYKpQXaJyULd9WJm_u75un9Aqc6sZNXfGphJudhpTdjjDBTBkX8X6335tD22L"
        },
        {
          title: "영상 편집하기",
          user: "리액션머신",
          src: "https://t1.daumcdn.net/cfile/tistory/223D2A49583F16100E"
        },
        {
          title: "기타 초급 떼기",
          user: "앤드류",
          src: "https://i.ytimg.com/vi/tTtoDyfC0DM/maxresdefault.jpg"
        },
        {
          title: "프랑스자수 기본 스티치",
          user: "스네이프",
          src:
            "https://lh3.googleusercontent.com/proxy/Y1HPaNgMe6KV92UDDVNlizGIJnwtQ7bk4SPk5yh30LQ1TjettAZ4w1Ji6UBbisAPQolkmGSZSb7VkQbgGN4iIx8xCPpYe5bc-4meDPUyryQYWNV-VG3gZEzHIA0"
        },
        {
          title: "주식 매매일지 쓰기",
          user: "지우39",
          src:
            "https://t1.daumcdn.net/liveboard/gilbut/59f2d0fb3ce34f81af17da57d69fccd8.jpg"
        },
        {
          title: "새벽 5시 기상",
          user: "긍정왕 루피",
          src:
            "https://scontent-sin6-1.cdninstagram.com/v/t51.2885-15/e35/139467207_453277379135963_4639520592442612980_n.jpg?_nc_ht=scontent-sin6-1.cdninstagram.com&_nc_cat=111&_nc_ohc=a5igoRf8yegAX-lp4Q6&tp=1&oh=87d14eaaaaef11d27c4771f2d22aa430&oe=60335784"
        },
        {
          title: "아침 조깅 인증",
          user: "모모모",
          src:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToFHqIGzcHzdOn1iWUW1m3ZIqihbu7wQwM1g&usqp=CAU"
        },
        {
          title: "코스모스 읽기",
          user: "지히히",
          src: "https://pbs.twimg.com/media/DcuP1voVMAAx_eK.jpg"
        },
        {
          title: "일본어 공부하기",
          user: "아이언표",
          src:
            "https://lh3.googleusercontent.com/proxy/amqN1fCiCkW8jRaImeSS6Zp-_IeR7eLRYpLMIsV10m5Tcw1TvPPqDxoCJ23m2eZAPYWF7CVTfLyNd_VqKZmhMRqUhPzq-WOwb0_ykY9j5F2Ml96aAw9xLBsSgKUetJCM88nrXw2vNTWscrcalo9aIFsqN2TrEckI-B3lzfMYJFJjy1hBEco32YLN3vMBOMx-gAqOIwPda93kE7kd4RJ7_WjdRnNqFYaaw4IsLpeTRL4nYKpQXaJyULd9WJm_u75un9Aqc6sZNXfGphJudhpTdjjDBTBkX8X6335tD22L"
        },
        {
          title: "영상 편집하기",
          user: "리액션머신",
          src: "https://t1.daumcdn.net/cfile/tistory/223D2A49583F16100E"
        },
        {
          title: "기타 초급 떼기",
          user: "앤드류",
          src: "https://i.ytimg.com/vi/tTtoDyfC0DM/maxresdefault.jpg"
        },
        {
          title: "프랑스자수 기본 스티치",
          user: "스네이프",
          src:
            "https://lh3.googleusercontent.com/proxy/Y1HPaNgMe6KV92UDDVNlizGIJnwtQ7bk4SPk5yh30LQ1TjettAZ4w1Ji6UBbisAPQolkmGSZSb7VkQbgGN4iIx8xCPpYe5bc-4meDPUyryQYWNV-VG3gZEzHIA0"
        },
        {
          title: "주식 매매일지 쓰기",
          user: "지우39",
          src:
            "https://t1.daumcdn.net/liveboard/gilbut/59f2d0fb3ce34f81af17da57d69fccd8.jpg"
        }
      ]
    };
  },
  mounted() {},
  methods: {
    onAfterSlideChange(index) {
      console.log(
        "@onAfterSlideChange Callback Triggered",
        "Slide Index " + index
      );
    },
    onBeforeSlideChange(index) {
      console.log(
        "@onBeforeSlideChange Callback Triggered",
        "Slide Index " + index
      );
    },
    onLastSlide(index) {
      console.log("@onLastSlide Callback Triggered", "Slide Index " + index);
    }
  },
  computed: {}
};
</script>

<style lang="scss" scoped>
.img {
  max-width: 100%;
  height: auto;
}
.carousel-3d-container figure {
  margin:0;
}

.carousel-3d-container figcaption {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.5);
  color: #fff;
  bottom: 0;
  position: absolute;
  bottom: 0;
  padding: 15px;
  font-size: 12px;
  min-width: 100%;
  box-sizing: border-box;
  text-align: center;
  height: 30%;
}
</style>