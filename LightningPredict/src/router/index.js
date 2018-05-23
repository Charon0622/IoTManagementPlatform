import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '@/components/Dashboard'
import Login from '@/components/Login'
import DetectPage from '@/components/DetectPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/Dashboard',
      name: 'Dashboard',
      component: Dashboard
    },
    {
      path:'/Login',
      name:'Login',
      component: Login
    },
    {
      path:'/DetectPage',
      name:'DetectPage',
      component:DetectPage
    },
    {
      path:'/',
      redirect:'/DetectPage'
    }
  ]
})
