<template>
    <v-container>
        <div class="inline-info user-info">
            <BasicUserInfo class="margin-bottom"></BasicUserInfo>
            <!-- <MyPageFunction></MyPageFunction> -->
            <!-- <FollowButtons class="margin-bottom"></FollowButtons> -->
        </div>
        <v-banner class="margin-bottom"></v-banner>
        <v-tabs
            v-model="tab"
            background-color="transparent"
            color="success"
            grow
            >
            <v-tab
                class="tab-font-size"
                v-for="item in items"
                :key="item"
                >
                {{ item }}
            </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
            <v-tab-item
                v-for="item in items"
                :key="item"
                >
                <v-card
                    color="basil"
                    flat
                    >
                    <v-card-text v-if="item === '피드'"><UserCertifications class="margin-bottom"></UserCertifications></v-card-text>
                    <v-card-text v-if="item === '챌린지'"><ChallengeResults class="margin-bottom"></ChallengeResults></v-card-text>
                    <v-card-text v-if="item === '통계'"><UserStat class="margin-bottom"></UserStat></v-card-text>
                </v-card>
            </v-tab-item>
        </v-tabs-items>
    </v-container>
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
        return {
            tab: null,
        items: ['피드', '챌린지', '통계'],
        };
    },
    methods: {
        }
};
</script>

<style scoped>

.container {
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
  margin-bottom: 50px;
}

.tab-font-size {
  font-size: 20px; 
}

</style>
