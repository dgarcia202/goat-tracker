<template>
<p>{{ featureId }}</p>
</template>
<script>
import axios from '../../node_modules/axios/dist/axios.min'
import config from '../config/Configuration'

export default {
    name: 'FeatureDetails',
    props: ['featureId'],
    data() {
        return {
        };
    },
    methods: {
        fetchData() {

        }
    }
}
</script>