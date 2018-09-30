<template>
    <div class="full-width">
        <v-alert v-model="alert.show" :type="alert.type" transition="slide-y-transition" dismissible
            class="custom-alert">
            {{alert.message}}
        </v-alert>
        <v-toolbar flat color="white">
            <v-toolbar-title>{{ title }}</v-toolbar-title>
            <v-divider
                class="mx-2"
                inset
                vertical
            ></v-divider>
            
            <v-spacer></v-spacer>

            <grid-tool-bar show-add="true" show-filter="true" show-delete="true"
                @click-add="$router.push(`/project/${projectId}/feature`)" />

            <v-flex xs3>            
                <v-text-field 
                    label="Add feature inline" 
                    placeholder="Enter name and press enter" 
                    class="mt-4" />
            </v-flex>
        </v-toolbar>
                
        <v-data-table
            v-model="selected"
            :headers="headers"
            :items="rows"
            :search="search"
            :rows-per-page-items="rowsPerPageItems"    
            :loading="loading"
            item-key="code"
            select-all
            
            class="elevation-1"
        >
            <template slot="items" slot-scope="props">
                <td>
                    <v-checkbox
                    v-model="props.selected"
                    primary
                    hide-details
                    ></v-checkbox>
                </td>
                <td class="text-xs-left"><a href="#" @click="$router.push(`/project/${props.item.projectId}/feature/${props.item.id}`)">{{ props.item.code }}</a></td>
                <td class="text-xs-left">{{ props.item.name }}</td>
                <td class="text-xs-left">{{ props.item.release.version }}</td>
                <td class="text-xs-left">{{ props.item.status.description }}</td>
                <td class="justify-center layout px-0">
                    <v-icon small class="mr-2 ml-3" @click="$router.push(`/project/${props.item.projectId}/feature/${props.item.id}`)">
                        edit
                    </v-icon>
                    <v-icon small>delete</v-icon>
                </td>
            </template>
        </v-data-table>
    </div>
</template>
<script>
import axios from '../../node_modules/axios/dist/axios.min'
import config from '../config/Configuration'
import GridToolBar from './GridToolBar'

export default {
    name: 'BacklogManagement',
    props: [ 'projectId' ],
    components: { GridToolBar },
    data() {
      return {
        title: 'Product Backlog',
        search: '',
        selected: [],        
        rowsPerPageItems: config.rowsPerPageItems,
        loading: false,
        alert: {
            show: false,
            type: 'error',
            message: null
        },
        headers: [
          { text: 'Feature', align: 'left', sortable: true, value: 'feature', width: '10%' },
          { text: 'Name', align: 'left', sortable: true, value: 'name', width: '50%' },
          { text: 'Release', align: 'left', value: 'completed', width: '20%' },
          { text: 'Status', value: 'status', width: '10%' },
          { text: 'Actions', value: 'actions', align: 'center', width: '10%' }
        ],
        rows: []
      };
    },
    methods: {
        fetchData() {
            this.loading = true;
            axios
                .get(`${config.apiBaseUrl}projects/${this.projectId}/features`)
                .then(response => {
                    this.rows = response.data;
                    this.loading = false;
                })
                .catch(() => {
                    this.loading = false;
                    this.alert.show = true;
                    this.alert.message = config.apiDownMessage;
                    let that = this;
                    setTimeout(() => {
                        that.alert.show = false;
                    }, config.alertDismissDelayMillis);                    
                });
        }
    },
    watch: {
        projectId() {
            this.fetchData();
        }
    },
    mounted () {
        this.fetchData();
    } 
}
</script>
