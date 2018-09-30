<template>
  <v-app>
    <v-navigation-drawer
      persistent
      :mini-variant="miniVariant"
      :clipped="clipped"
      v-model="drawer"
      enable-resize-watcher
      fixed
      app
    >
      <v-list>
        <v-list-tile
          value="true"
          v-for="(item, i) in items"
          :key="i"
          @click="navigateToRoute(item.route)"
        >
          <v-list-tile-action>
            <v-icon v-html="item.icon"></v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title v-text="item.title"></v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar
      app
      :clipped-left="clipped"
    >
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-btn icon @click.stop="miniVariant = !miniVariant">
        <v-icon v-html="miniVariant ? 'chevron_right' : 'chevron_left'"></v-icon>
      </v-btn>
      <v-btn icon @click.stop="clipped = !clipped">
        <v-icon>web</v-icon>
      </v-btn>
      <v-toolbar-title v-text="title"></v-toolbar-title>
      <v-spacer></v-spacer>
      <project-selector v-model="selectedProject" />
      <v-btn icon @click.stop="rightDrawer = !rightDrawer">
        <v-icon>menu</v-icon>
      </v-btn>
    </v-toolbar>
    <v-content>
      <router-view/>
    </v-content>
    <v-navigation-drawer
      temporary
      :right="right"
      v-model="rightDrawer"
      fixed
      app
    >
      <v-list>
        <v-list-tile @click="right = !right">
          <v-list-tile-action>
            <v-icon>compare_arrows</v-icon>
          </v-list-tile-action>
          <v-list-tile-title>Switch drawer (click me)</v-list-tile-title>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-footer :fixed="fixed" app>
      <span>&copy; 2018 {{ copyRightHolder }}</span>
    </v-footer>
  </v-app>
</template>

<script>
import mainMenuItems from './config/MainMenu'
import ProjectSelector from './components/ProjectSelector'

export default {
  name: 'App',
  components: {
    ProjectSelector
  },
  data() {
    return {
      selectedProject: null,
      currentRoute: null,
      clipped: false,
      drawer: true,
      fixed: false,
      items: mainMenuItems(),
      miniVariant: false,
      right: true,
      rightDrawer: false,
      title: 'Goat Tracker',
      copyRightHolder: 'GoatSoft S.P.'
    }
  },
  watch: {
    selectedProject(newProjectId) {
      if (this.currentRoute != null) {
        this.$router.push(`/project/${newProjectId}/${this.currentRoute}`);
      }
    }
  },
  methods: {
    navigateToRoute: function (route) {
      this.$router.push(`/project/${this.selectedProject}/${route}`);
      this.currentRoute = route;
    }
  } 
}
</script>
