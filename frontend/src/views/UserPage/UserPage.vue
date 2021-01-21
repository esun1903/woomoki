<template>
<v-app class="container">
    <h1 class="text-center">유저페이지!!!!</h1>
    <div class="inline-info user-info">
        <BasicUserInfo class="margin-bottom"></BasicUserInfo>
        <!-- <MyPageFunction></MyPageFunction> -->
        <!-- <FollowButtons class="margin-bottom"></FollowButtons> -->
    </div>
    <ChallengeResults class="margin-bottom"></ChallengeResults>
    <UserStat class="margin-bottom"></UserStat>
    <UserCertifications class="margin-bottom"></UserCertifications>
    
</v-app>
</template>

<script>
import BasicUserInfo from "./components/BasicUserInfo"
// import MyPageFunction from "./components/MyPageFunction"
// import FollowButtons from "./components/FollowButtons"
import ChallengeResults from "./components/ChallengeResults"
import UserCertifications from "./components/UserCertifications"
import UserStat from "./components/UserStat"

export default {
    name: "UserPage",
    components: {
        BasicUserInfo,
        // MyPageFunction,
        // FollowButtons,
        ChallengeResults,
        UserCertifications,
        UserStat,
    },
    data: function () {
        return {};
    },
    methods: {},
};
</script>

<style>

.container {
  position: absolute;
  left: 30%;
  margin-left: -100px;
  width: 1000px;
}

.user-info {
    margin-left: 130px;
    margin-bottom: -200px;
}

.inline-info {
  display: inline-block;
}

.margin-bottom {
    margin-bottom: 100px;
}
</style>