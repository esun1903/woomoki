// import axios from "axios";
// import router from "@/router";

// // 나중에 env 파일로 바꾸기
// // const SERVER_URL = "http://i4a303.p.ssafy.io/";
// const SERVER_URL = "http://localhost:8080";

// const CertStore = {
//   state: {
//    certForm:{
//        title:"",
//        content:"",
//        photo:"",
//        hashtag:"",
//    }
//   },
//   getters: {
  
//   }, 
//   mutations: {
   

//   },
//   actions: {
//     writeCert(context,certForm){
//         return axios
//             .post(`${SERVER_URL}/insertCertification`, certForm)
//             .then((response) => {
//                 context.commit("WRITE_CERT", response.data);
                
//             })
//     }
   
//   }
// }

// export default CertStore;