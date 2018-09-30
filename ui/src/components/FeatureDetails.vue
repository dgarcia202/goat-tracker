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
                <v-form v-model="valid" class="mx-4">
                    <v-container grid-list-md text-xs-center fluid>
                        <v-layout row wrap>
                            <v-flex xs2>
                                <v-text-field
                                v-model="item.code"
                                label="code"
                                required></v-text-field>
                            </v-flex>
                            <v-flex xs10>
                                <v-text-field
                                v-model="item.name"
                                label="Name"
                                required />
                            </v-flex>
                            <v-flex xs12>
                                <v-textarea
                                v-model="item.description"
                                name="input-7-1"
                                label="Description"
                                ></v-textarea>
                            </v-flex>
                        </v-layout>
                    </v-container>
                </v-form>
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

export default {
    name: 'FeatureDetails',
    props: [ 'projectId', 'featureId' ],
    data() {
        return {
            selectedTab: null,
            tabTitles: [ 'Feature', 'Users Stories', 'Relationships', 'Discussion', 'Audit' ],
            item: null,
            valid: true,
            loading: true
        };
    },
    methods: {
        fetchData() {
            axios
                .get(`${config.apiBaseUrl}projects/${this.projectId}/features/${this.featureId}`)
                .then(response => {
                    this.item = response.data;
                    this.loading = false;
                })
                .catch(() => {
                    this.loading = false;
                });
        }
    },
    mounted () {
        this.fetchData();
    }     
}
</script>