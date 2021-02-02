<template>
    <v-container class="container-size">
        <v-row column>
            <v-col>
                <BasicUserInfo></BasicUserInfo>
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-banner></v-banner>
                <v-tabs
                    v-model="tab"
                    background-color="transparent"
                    color="success"
                    grow
                    >
                    <v-tab
                        style="font-size: 1vw;"
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
                            <v-card-text v-if="item === '피드'"><UserCertifications></UserCertifications></v-card-text>
                            <v-card-text v-if="item === '챌린지'"><ChallengeResults></ChallengeResults></v-card-text>
                            <v-card-text v-if="item === '통계'"><UserStat></UserStat></v-card-text>
                        </v-card>
                    </v-tab-item>
                </v-tabs-items>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import BasicUserInfo from "./components/BasicUserInfo"
import ChallengeResults from "./components/ChallengeResults"
import UserCertifications from "./components/UserCertifications"
import UserStat from "./components/UserStat"

export default {
    name: "UserPage",
    components: {
        BasicUserInfo,
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

.container-size {
    width: 50%;
}

</style>
