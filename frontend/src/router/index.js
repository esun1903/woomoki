import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/views/Login/Login.vue";
import FindPassword from "@/views/Login/FindPassword.vue";

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
      path: '/findPassword',
      name: 'FindPassword',
      component: FindPassword,
    },
  ]
});

export default router;
