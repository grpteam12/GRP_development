/* eslint-disable */
import Vue from 'vue'
import Router from 'vue-router'

import login from '../src/components/login'
import admin from '../src/components/admin/admin'

Vue.use(Router)

//获取原型对象上的push函数
const originalPush = Router.prototype.push
//修改原型对象中的push方法
Router.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}

const router =  new Router({
  mode: "history",
  routes: [
    {
      //主页
      path: '/',
      redirect:'/login'
    },
    {
      //登陆
      path: '/login',
      name: 'login',
      component: login
    },
    {
      //管理员界面
      path:'/admin',
      name:'admin',
      component:admin
    }
    
  ]
})

//配置路由拦截
router.beforeEach((to, from, next) => {
  const isLogin = sessionStorage.principal ? true : false;
  if (to.path == "/login") {
    next();
  } else {
    isLogin ? next() : next("/login");
  }
  
});

export default router;

