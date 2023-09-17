import Vue from 'vue'
import VueRouter from 'vue-router'

// 解决导航栏或者底部导航tabBar中的vue-router在3.0版本以上频繁点击菜单报错的问题。
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/home',
    children:[
      {
        path: '403',
        name: 'Auth',
        meta: {name: '无权限'},
        component: () => import('../views/manager/Auth')
      },
      {
        path: 'home',
        name: 'Home',
        meta: {name: '系统首页'},
        component: () => import('../views/manager/Home')
      },
      {
        path: 'user',
        name: 'User',
        meta: {name: '用户信息'},
        component: () => import('../views/manager/User')
      },
      {
        path: 'person',
        name: 'Person',
        meta: {name: '个人信息'},
        component: () => import('../views/manager/Person')
      },
      {
        path: 'password',
        name: 'Password',
        meta: {name:'修改密码'},
        component: () => import('../views/manager/Password')
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    meta: {name: '登录'},
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    meta: {name: '注册'},
    component: () => import('../views/Register')
  },
  {
    path: '*',
    name: '404',
    meta: {name: '无法访问'},
    component: () => import('../views/404')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach(((to, from, next) => {
  let adminPaths = ['/user']
  let user = JSON.parse(localStorage.getItem('honey-user') || '{}')

  if(user.role !== '管理员' && adminPaths.includes(to.path)){
    next('/403')
  }else{
    next()
  }
}))

export default router
