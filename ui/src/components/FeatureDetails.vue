<template>
    <div>
        <v-progress-linear :indeterminate="true" height="3" class="ma-0 form-top-loader" v-if="loading"></v-progress-linear>
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

            <v-tab-item key="Feature">
                <feature-details-main 
                    v-model="item" 
                    :project-id="projectId"
                    :releases="releases"
                    :statuses="statuses"
                    :mode="currentMode" />

            </v-tab-item>

            <v-tab-item key="User Stories">
                <v-card flat>
                    <v-card-text>US</v-card-text>
                </v-card>
            </v-tab-item>

            <v-tab-item key="Relationships">
                <v-card flat>
                    <v-card-text>rel</v-card-text>
                </v-card>
            </v-tab-item>

            <v-tab-item key="Discussion">
                <v-card flat>
                    <v-card-text>forumn</v-card-text>
                </v-card>
            </v-tab-item>

            <v-tab-item key="Audit">
                <v-card flat>
                    <v-card-text>aud</v-card-text>
                </v-card>
            </v-tab-item>

        </v-tabs-items>

    </div>
</template>
<script>
import axios from '../../node_modules/axios/dist/axios.min'
import config from '../config/Configuration'
import FeatureDetailsMain from './FeatureDetailsMain'

export default {
    name: 'FeatureDetails',
    props: {
        projectId: null,
        featureId: null
    },
    components: { FeatureDetailsMain },
    data() {
        return {
            loading: true,
            selectedTab: null,
            tabTitles: [ 'Feature', 'Users Stories', 'Relationships', 'Discussion', 'Audit' ],
            item: { code: null, name: null, description: null },
            releases: [],
            statuses: []
        };
    },
    methods: {
        fetchData() {

            let requests = [
                () => axios.get(`${config.apiBaseUrl}feature-statuses`),
                () => axios.get(`${config.apiBaseUrl}projects/${this.projectId}`)
            ]

            if (this.isEditMode) {
                requests.push(() => axios.get(`${config.apiBaseUrl}projects/${this.projectId}/features/${this.featureId}`));
            }

            axios.all(requests)
            .then(axios.spread((statuses, project, feature) => {
                statuses().then(response => this.statuses = response.data);
                project().then(response => this.releases = response.data.releases);
                
                if (typeof feature !== 'undefined') {
                    feature().then(response => { 
                        this.item = response.data;
                        this.item.releaseId = this.item.release.id;
                        this.item.statusId = this.item.status.id;
                        delete this.item.release;
                        delete this.item.status;
                    });
                }

                this.loading = false;
            }))
            .catch(() => {
                this.loading = false;
            });
        }
    },
    computed: {
        isCreateMode() {
            return this.featureId == null;
        },
        isEditMode() {
            return !this.isCreateMode;
        },
        currentMode() {
            return this.isCreateMode ? 'create' : 'edit';
        }
    },
    mounted () {
        this.fetchData();
    }     
}
</script>