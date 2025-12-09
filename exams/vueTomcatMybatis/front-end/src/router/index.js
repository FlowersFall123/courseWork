import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: () => import('../views/Home.vue'),
    },
      {
          path: '/',
          name: 'login',
          component: () => import('../views/Login.vue'),
      },
  ],
})

export default router
