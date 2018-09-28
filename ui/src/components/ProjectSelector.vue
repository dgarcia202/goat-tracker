<template>
    <v-flex xs3>
        <v-select
            :value="value"
            :items="projects"
            item-text="name"
            item-value="id"     
            class="mr-5"   
            @input="selectValue"
        ></v-select>
    </v-flex>
</template>
<script>
import axios from '../../node_modules/axios/dist/axios.min'
import config from '../config/Configuration'

export default {
    name: 'ProjectSelector',
    props: [ 'value' ],
    data() {
        return {
            projects: []
        }
    },
    methods: {
        selectValue(e) {
            this.$emit('input', e)
        }
    },
    mounted() {
        axios
        .get(config.apiBaseUrl + 'projects/')
        .then(response => {
            this.projects = response.data;
            this.$emit('input', this.projects[0].id);
        })
    }      
}
</script>