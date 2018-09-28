<template>
    <div>
        <v-tabs
            slot="extension"
            v-model="selectedTab"
            color="transparent"
            grow
        >
            <v-tabs-slider color="primary"></v-tabs-slider>

            <v-tab
            v-for="item in tabTitles"
            :key="item"
            >
            {{ item }}
            </v-tab>
        </v-tabs>

        <v-tabs-items v-model="selectedTab">
            <v-tab-item
                v-for="item in tabTitles"
                :key="item">

                <v-card flat>
                <v-card-text>{{ item }}</v-card-text>
                </v-card>
            </v-tab-item>
        </v-tabs-items>

    </div>
</template>
<script>
import axios from '../../node_modules/axios/dist/axios.min'
import config from '../config/Configuration'

export default {
    name: 'FeatureDetails',
    props: ['featureId'],
    data() {
        return {
            selectedTab: null,
            tabTitles: [ 'Feature', 'Users Stories', 'Relationships', 'Discussion', 'Audit' ]
        };
    },
    methods: {
        fetchData() {

        }
    }
}
</script>