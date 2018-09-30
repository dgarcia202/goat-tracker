<template>
    <div class="ma-0 pa-0">
        <v-btn v-if="showAdd" color="success" fab small dark class="mt-2" @click="$emit('click-add')" >
            <v-icon>add</v-icon>
        </v-btn>
        <v-btn v-if="showFilter" color="primary" fab small dark class="mt-2" @click="$emit('click-filter')">
            <v-icon>filter_list</v-icon>
        </v-btn>
        <v-btn v-if="showDelete" color="error" fab small dark class="mr-4 mt-2" @click="$emit('click-delete')">
            <v-icon>delete</v-icon>
        </v-btn>
    </div>
</template>
<script>
export default {
    name: 'GridToolBar',
    props: { 
        showAdd: true, 
        showFilter: true, 
        showDelete: true 
    },
    data() {
        return {
        };
    }
}
</script>