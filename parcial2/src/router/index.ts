import { createRouter, createWebHistory } from '@ionic/vue-router'
import Home from '@/views/HomePage.vue'
import Invoices from '@/views/InvoicesPage.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/facturas',
    name: 'Facturas',
    component: Invoices
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
