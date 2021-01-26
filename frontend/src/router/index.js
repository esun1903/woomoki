import Vue from "vue";
import VueRouter from "vue-router";
// import Signup from "@/views/Signup/Signup.vue";
import Login from "@/views/Login/Login.vue";
import FindPassword from "@/views/Login/FindPassword.vue";
import FindId from "@/views/Login/FindId.vue";

// import Navbar from "@/components/Navbar/Navbar.vue";

Vue.use(VueRouter);


const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes:[
    // {
    //   path: '/signup',
    //   name: 'Signup',
    //   component: Signup,        
    // },
    {
      path: '/login',
      name: 'Login',
      component: Login,        
    },
    {
      path: '/findPassword',
      name: 'FindPassword',
      component: FindPassword,
    },
    {
      path: '/findId',
      name: 'FindId',
      component: FindId,
    },
  ]
});

export default router;
