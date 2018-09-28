import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: HelloWorld
    },
    {
      path: '/project/:projectId/backlog',
      name: 'backlog',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ '../components/BacklogManagement.vue'),
      props: true
    },
    {
      path: '/feature/:featureId',
      name: 'feature_details',
      component: () => import('../components/FeatureDetails.vue'),
      props: true
    }
  ]
})
