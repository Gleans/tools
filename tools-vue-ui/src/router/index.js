import Vue from 'vue'
import Router from 'vue-router'
import {postRequest} from '../utils/api'
import MainMenu from '../components/public/' //Layout 是架构组件，不在后台返回，在文件里单独引入

const _import = require('./_import_' + process.env.NODE_ENV)//获取组件的方法

Vue.use(Router)

const Login = resolve => require(['@/components/login/Login'], resolve)

const NotFound = resolve => require(['@/components/errorPage/404'], resolve)

const NoPersession = resolve => require(['@/components/errorPage/401'], resolve)

/**
 * 静态路由
 */
export const staticRouters = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: '登录',
    component: Login,
    meta: {
      keepAlive: true
    }
  },
  {
    path: '/404',
    name: '404',
    component: NotFound,
    meta: {
      keepAlive: true
    }
  },
  {
    path: '/401',
    name: '401',
    component: NoPersession,
    meta: {
      keepAlive: true
    }
  }/*,
  {
    path: '*',
    redirect: '/401'
  }*/
]

const router = new Router({
  routes: staticRouters
})

/*
 * 权限拦截
 * */
let getRouter  //用来获取后台拿到的路由

const whiteList = ['/login'] //白名单,不需要登录的路由


router.beforeEach((to, from, next) => {
  if (whiteList.indexOf(to.path) !== -1) {
    next()
  } else {
    if (!getRouter) {//不加这个判断，路由会陷入死循环
      // if (!getRouters("routes")) {
      postRequest('/router').then(res => {
        let routerjson = res.data.data//后台拿到路由
        let getRouterArray = []
        if (!routerjson) return next()
        for (let i = 0; i < routerjson.length; i++) {
          getRouterArray.push({
            'path': routerjson[i].path,
            'component': routerjson[i].key ? 'main' : routerjson[i].component,
            'meta': {
              'keepAlive': routerjson[i].keepAlive
            },
            'name': routerjson[i].name,
            'children': routerjson[i].children,
            'iconcls': routerjson[i].iconcls,
            'key': routerjson[i].key
          })
        }
        //sessionStorage 存储数组对象的方法
        saveRouters("routes", getRouterArray)
        getRouter = getRouterArray
        routerGo(to, next)//执行路由跳转方法
      })
      /* } else {//从sessionStorage拿到了路由
         getRouter = getRouters("routes")
         routerGo(to, next)
       }*/
    } else {
      next()
    }
  }
})

function saveRouters(name, data) { //sessionStorage 存储数组对象的方法
  sessionStorage.setItem(name, JSON.stringify(data))
}

function getRouters(name) { //sessionStorage 获取数组对象的方法
  return JSON.parse(sessionStorage.getItem(name))
}

function routerGo(to, next) {
  getRouter = filterAsyncRouter(getRouter) //过滤路由
  router.addRoutes(getRouter) //动态添加路由
  global.antRouter = getRouter //将路由数据传递给全局变量，做侧边栏菜单渲染工作
  router.addRoutes([{path: '*', redirect: '/401'}])
  next({...to, replace: true})
}

function filterAsyncRouter(asyncRouterMap) { //遍历后台传来的路由字符串，转换为组件对象
  return asyncRouterMap.filter(route => {
    if (route.component) {
      if (route.component === 'main') {//二级菜单标题特殊处理
        route.component = MainMenu
      } else {
        route.component = _import(route.component)
      }
    }
    if (route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children)
    }
    return true
  })
}

export default router
