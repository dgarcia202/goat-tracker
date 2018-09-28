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
            <v-btn color="success" fab small dark class="mt-2">
                <v-icon>add</v-icon>
            </v-btn>
            <v-btn color="primary" fab small dark class="mt-2">
                <v-icon>filter_list</v-icon>
            </v-btn>
            <v-btn color="error" fab small dark class="mr-4 mt-2">
                <v-icon>delete</v-icon>
            </v-btn>

            <v-flex xs3>            
                <v-text-field 
                    label="Add inline" 
                    placeholder="Enter name and press enter" 
                    class="mt-4" />
            </v-flex>
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
                <td class="text-xs-left"><a href="#">{{ props.item.code }}</a></td>
                <td class="text-xs-left">{{ props.item.name }}</td>
                <td class="text-xs-right">{{ props.item.estimation }}</td>
                <td class="text-xs-right">{{ props.item.pctCompleted }}</td>
                <td class="text-xs-left">{{ props.item.status.description }}</td>
                <td class="justify-center layout px-0">
                    <v-icon small class="mr-2 ml-3">edit</v-icon>
                    <v-icon small>delete</v-icon>
                </td>
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
          { text: 'Status', value: 'status', width: '10%' },
          { text: 'Actions', value: 'actions', align: 'center', width: '10%' }
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
