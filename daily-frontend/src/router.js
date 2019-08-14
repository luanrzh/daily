import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      children: [
        {
          path: 'time/todayTask',
          component: () => import(/* webpackChunkName: "todayTask" */ '@/components/time/TodayTask.vue')
        },
        {
          path: 'time/allTask',
          component: () => import(/* webpackChunkName: "allTask" */ '@/components/time/AllTask.vue')
        },
        {
          path: '/money/*',
          component: () => import(/* webpackChunkName: "Unfinished" */ '@/components/Unfinished.vue')
        },
        {
          path: '/knowledge/*',
          component: () => import(/* webpackChunkName: "Unfinished" */ '@/components/Unfinished.vue')
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import(/* webpackChunkName: "login" */ './views/Login.vue')
    }
    , {
      path: '*',
      redirect: '/'
    }
  ]
})
