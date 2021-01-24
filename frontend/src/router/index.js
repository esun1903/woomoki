import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/views/Login/Login.vue";
import ResetPassword from "@/views/Login/ResetPassword.vue";

// import Navbar from "@/components/Navbar/Navbar.vue";

Vue.use(VueRouter);


const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes:[
    {
      path: '/login',
      name: 'Login',
      component: Login,        
    },
    {
      path: '/resetPassword',
      name: 'ResetPassword',
      component: ResetPassword,
    }
  ]
});

export default router;
