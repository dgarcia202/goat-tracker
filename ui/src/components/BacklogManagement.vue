<template>
    <div>
        <v-toolbar flat color="white">
            <v-toolbar-title>{{ title }}</v-toolbar-title>
            <v-divider
                class="mx-2"
                inset
                vertical
            ></v-divider>
            <v-spacer></v-spacer>
        </v-toolbar>
                
        <v-data-table
            :headers="headers"
            :items="rows"
            :search="search"
            v-model="selected"
            item-key="code"
            select-all
            :rows-per-page-items="rowsPerPageItems"    
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
                <td class="text-xs-left">{{ props.item.code }}</td>
                <td class="text-xs-left">{{ props.item.name }}</td>
                <td class="text-xs-right">{{ props.item.estimation }}</td>
                <td class="text-xs-right">{{ props.item.pctCompleted }}</td>
                <td class="text-xs-left">{{ props.item.status.description }}</td>
            </template>
        </v-data-table>
    </div>
</template>
<script>
import axios from '../../node_modules/axios/dist/axios.min'
import config from '../config/Configuration'

export default {
    name: 'BacklogManagement',
    props: ['projectId'],
    data () {
      return {
        title: 'Release Backlog',
        search: '',
        selected: [],        
        rowsPerPageItems: config.rowsPerPageItems,
        headers: [
          {
            text: 'Feature',
            align: 'left',
            sortable: true,
            value: 'feature',
            width: '10%'
          },
          { text: 'Name', align: 'left', sortable: true, value: 'name', width: '50%' },
          { text: 'Estimation', align: 'right', value: 'estimation', width: '10%' },
          { text: '% Completed', align: 'right', value: 'completed', width: '10%' },
          { text: 'Status', value: 'status', width: '15%' }
        ],
        rows: []
      };
    },
    methods: {
        fetchData() {
            axios
                .get(`${config.apiBaseUrl}projects/${this.projectId}/features`)
                .then(response => (this.rows = response.data));
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
