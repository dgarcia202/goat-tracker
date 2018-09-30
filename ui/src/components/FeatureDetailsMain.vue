<template>
    <v-form v-model="valid" class="mx-4">
        <v-container grid-list-md text-xs-center fluid>
            <v-layout row wrap>

                <template v-if="mode == 'edit'">
                    <v-flex xs1>
                        <v-text-field
                        v-model="value.code"
                        label="code"
                        required></v-text-field>
                    </v-flex>
                    <v-flex xs11>
                        <v-text-field
                        v-model="value.name"
                        label="Name"
                        required />
                    </v-flex>
                </template>
                <template v-else>
                    <v-flex xs12>
                        <v-text-field
                        v-model="value.name"
                        label="Name"
                        required />
                    </v-flex>
                </template>

                <v-flex xs12>
                    <v-textarea
                    v-model="value.description"
                    name="input-7-1"
                    label="Description"
                    ></v-textarea>
                </v-flex>

                <v-flex xs2>
                    <v-select
                        v-model="value.statusId"
                        :items="statuses"
                        item-text="description"
                        item-value="id"     
                        label="Status"
                        @input="$emit('input', value)"
                    ></v-select>
                </v-flex>
                <v-spacer></v-spacer>
                <v-flex xs2>
                    <v-select
                        v-model="value.releaseId"
                        :items="releases"
                        item-text="version"
                        item-value="id"     
                        label="Release"
                        @input="$emit('input', value)"
                    ></v-select>
                </v-flex>              
                     
            </v-layout>
        </v-container>
    </v-form>
</template>
<script>
export default {
    name: 'FeatureDetailsMain',
    props: [ 'value', 'releases', 'statuses', 'mode' ],
    data() {
        return {
            valid: true
        };
    }
}
</script>