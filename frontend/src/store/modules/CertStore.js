import axios from "axios";
import router from "@/router";

// 나중에 env 파일로 바꾸기
// const SERVER_URL = "http://i4a303.p.ssafy.io/";
const SERVER_URL = "http://i4a303.p.ssafy.io/api";

const CertStore = {
  namespaced: true,
  state: {
   certForm:{
       content:"",
       img:"",
       cng_id:"",
       user_id:"",
       cert_id:"",
    //    hashtag:"",
   }
  },
  getters: {
  
  }, 
  mutations: {
  },
  actions: {
    writeCert(context,certForm){
        return axios
            .post(`${SERVER_URL}/insertCertification`, certForm)
            .then(() => {
                alert('인증 등록 완료!');
                router.push({ name: 'SeedDetail', params: { seedId: certForm.cng_id }})
            }).catch((error) => {
                console.log(error);
                alert("인증 등록 실패!");
            })
    },
  }
}

export default CertStore;