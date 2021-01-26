import Vue from "vue";
import VueRouter from "vue-router";
import Main from "@/views/MainPage/MainPage.vue";
import Signup from "@/views/Signup/Signup.vue";
import FavoriteCategory from "@/views/Signup/components/FavoriteCategory.vue";
import Login from "@/views/Login/Login.vue";
import FindId from "@/views/Login/FindId.vue";
import FindPassword from "@/views/Login/FindPassword.vue";
import UserPage from "@/views/UserPage/UserPage.vue";
import UserPageEdit from "@/views/UserPageEdit/UserPageEdit.vue";
import CompareOriginPassword from "@/views/UserPageEdit/CompareOriginPassword.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/main",
    name: "Main",
    component: Main
  },
  {
    path: "/signup",
    name: "Signup",
    component: Signup
  },
  {
    path: "/signup/favCategory",
    name: "FavoriteCategory",
    component: FavoriteCategory
  }, 
  {
    path: '/login',
    name: 'Login',
    component: Login,        
  },
  {
    path: 'login/findId',
    name: 'FindId',
    component: FindId,        
  },
  {
    path: 'login/findPassword',
    name: 'FindPassword',
    component: FindPassword,        
  },
  {
    path: '/userPage',
    name: 'UserPage',
    component: UserPage,        
  },
  {
    path: 'userPage/userPageEdit',
    name: 'UserPageEdit',
    component: UserPageEdit,        
  },
  {
    path: 'userPage/userPageEdit/password',
    name: 'CompareOriginPassword',
    component: CompareOriginPassword,        
  },
  
];
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
