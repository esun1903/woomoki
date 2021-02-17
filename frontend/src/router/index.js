import Vue from "vue";
import VueRouter from "vue-router";
import Main from "@/views/MainPage/MainPage.vue";
import Signup from "@/views/Signup/Signup.vue";
import FavoriteCategory from "@/views/Signup/components/FavoriteCategory.vue";
import Login from "@/views/Login/Login.vue";
import FindId from "@/views/Login/components/FindId.vue";
import FindPassword from "@/views/Login/components/FindPassword.vue";
import FindPasswordByEmail from "@/views/Login/components/FindPasswordByEmail.vue";
import FindPasswordByPhone from "@/views/Login/components/FindPasswordByPhone.vue";
import FindPasswordResult from "@/views/Login/components/FindPasswordResult.vue";
import UserPage from "@/views/UserPage/UserPage.vue";
import UserPageEdit from "@/views/UserPageEdit/UserPageEdit.vue";
import CompareOriginPassword from "@/views/UserPageEdit/CompareOriginPassword.vue";
import CertificationInsert from "@/views/Certification/CertificationInsert.vue";
import CertificationDetail from "@/views/Certification/CertificationDetail.vue";
import CertificationUpdate from "@/views/Certification/CertificationUpdate.vue";
import SeedCreate from "@/views/SeedCreate/CreateSeed.vue";
import FeedAllUser from "@/views/Feed/FeedAllUser.vue";
import FeedFollowUser from "@/views/Feed/FeedFollowUser.vue";
import SeedCreated from "@/views/SeedList/SeedCreated.vue"
import SeedFail from "@/views/SeedList/SeedFail.vue"
import SeedJoin from "@/views/SeedList/SeedJoin.vue"
import SeedSuccess from "@/views/SeedList/SeedSuccess.vue"
import SeedDetail from "@/views/SeedDetail/SeedDetail.vue"
import StampCard from "@/views/SeedDetail/StampCard.vue";
import SearchedPage from "@/views/SearchedPage/SearchedPage.vue"
import SeedUpdate from "@/views/SeedUpdate/SeedUpdate.vue"
import Team from "@/views/Team/Team.vue"
// import KakaoTalk from "@/views/Login/callback/KakaoTalk.vue";

Vue.use(VueRouter);

const BlankContainer = () => import('@/containers/BlankContainer')


const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
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
    path: '/login/findId',
    name: 'FindId',
    component: FindId,        
  },
  {
    path: '/login/findPassword',
    name: 'FindPassword',
    component: FindPassword,        
  },
  {
    path: '/login/findPasswordByEmail',
    name: 'FindPasswordByEmail',
    component: FindPasswordByEmail,        
  },
  {
    path: '/login/findPasswordByPhone',
    name: 'FindPasswordByPhone',
    component: FindPasswordByPhone,        
  },
  {
    path: '/login/findPasswordResult',
    name: 'FindPasswordResult',
    component: FindPasswordResult,        
  },
  {
    // query사용
    // path: '/userPage',
    path: '/userPage/:userNickname',
    name: 'UserPage',
    component: UserPage,
    props: true     
  },
  {
    path: '/userPageEdit',
    name: 'UserPageEdit',
    component: UserPageEdit,   
  },
  {
    path: '/comparepwd',
    name: 'CompareOriginPassword',
    component: CompareOriginPassword,
    props: true,        
  },
  {
    path: '/certificationInsert/:cngId?/:currentWeek?/:currentDay?',
    name: 'CertificationInsert',
    component: CertificationInsert,        
  },
  {
    path: '/certificationDetail/:cngUserId?/:cngId?/:certId?',
    name: 'CertificationDetail',
    component: CertificationDetail,    
    props: true        
  },
  {
    path: '/certificationUpdate/:cngUserId?/:cngId?/:certId?',
    name: 'CertificationUpdate',
    component: CertificationUpdate,        
  },
  {
    path: '/callback',
    name: 'callback',
    redirect: '/login',
    component: BlankContainer,
    children: [
      {
        path: 'kakaotalk',
        name: 'callback-kakaotalk',
        component: () => import('@/views/Login/callback/KakaoTalk'),
      },
    ]
  },
  {
    path: '/createSeed',
    name: 'SeedCreate',
    component: SeedCreate,        
  },
  {
    path: '/feedAll',
    name: 'FeedAllUser',
    component: FeedAllUser,        
  },
  {
    path: '/feedFollow',
    name: 'FeedFollowUser',
    component: FeedFollowUser,        
  },
  {
    path: '/seedCreated',
    name: 'SeedCreated',
    component: SeedCreated,        
  },
  {
    path: '/seedFail',
    name: 'SeedFail',
    component: SeedFail,        
  },
  {
    path: '/seedJoin',
    name: 'SeedJoin',
    component: SeedJoin,        
  },
  {
    path: '/seedSuccess',
    name: 'SeedSuccess',
    component: SeedSuccess,        
  },
  {
    path: '/seedDetail/:seedId',
    name: 'SeedDetail',
    component: SeedDetail,        
  },
  {
    path: '/seedDetail/stampCard/:seedId?/:userId?',
    name: 'StampCard',
    component: StampCard,       
  },
  {
    path: '/searchedPage',
    name: 'SearchedPage',
    component: SearchedPage,
    props: true        
  },
  {
    path: '/seedUpdate/:seedId',
    name: 'SeedUpdate',
    component: SeedUpdate,        
  },
  {
    path: '/team',
    name: 'Team',
    component: Team,        
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;