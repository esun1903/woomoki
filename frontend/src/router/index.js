import Vue from "vue";
import VueRouter from "vue-router";
// import Signup from "@/views/Signup/Signup.vue";
import Login from "@/views/Login/Login.vue";
// import FindPassword from "@/views/Login/FindPassword.vue";
// import FindId from "@/views/Login/FindId.vue";


// import Home from "../views/Home.vue";
import Signup from "@/views/Signup/Signup.vue";
import FavoriteCategory from "@/views/Signup/components/FavoriteCategory.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/signup",
    name: "Signup",
    component: Signup
  },
  {
    path: "/signup/favCategory",
    name: "FavoriteCategory",
    component: FavoriteCategory
  }, {
    path: '/login',
    name: 'Login',
    component: Login,        
  },
  // {
  //   path: '/findPassword',
  //   name: 'FindPassword',
  //   component: FindPassword,
  // },
  // {
  //   path: '/findId',
  //   name: 'FindId',
  //   component: FindId,
  // },
  // {
  //   path: "/about",
  //   name: "About",
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () =>
  //     import(/* webpackChunkName: "about" */ "../views/About.vue")
  // }
];
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
