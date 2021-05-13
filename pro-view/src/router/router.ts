import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import Layout from "../Layout/Layout.vue";
import Login from "@/views/login/login.vue";
import Home from "@/views/home/home.vue";

const routes: RouteRecordRaw[] = [
  {
    path: "/login",
    name: "login",
    component: Login,
    meta: { title: "登录", icon: "logo", hidden: true }
  },
  {
    path: "/",
    name: "home",
    component: Layout,
    redirect: "home",
    children: [
      {
        path: "/home",
        component: Home,
        name: "home",
        meta: { title: "首页", icon: "index", affix: true, noCache: true }
      },
      {
        path: "/rent",
        name: "rent",
        component: () => import("./Empty.vue"),
        meta: { title: "租赁管理", icon: "index" }
      },
      {
        path: "/rent/house",
        name: "house",
        component: () => import("@/views/rent/house/house.vue"),
        meta: { title: "房源管理", icon: "index", parent: "rent" }
      }, {
        path: "/rent/add_house",
        name: "add_house",
        component: () => import("@/views/rent/house/add_house.vue"),
        meta: { title: "新增房源", icon: "index", parent: "rent" }
      },
      {
        path: "/rent/contract",
        name: "contract",
        component: () => import("@/views/rent/contract/contract.vue"),
        meta: { title: "合同", icon: "index", parent: "rent" }
      },
      {
        path: "/setting",
        name: "setting",
        component: () => import("./Empty.vue"),
        meta: { title: "系统设置" }
      },
      {
        path: "/setting/webApi",
        name: "webApi",
        component: () => import("@/views/setting/webApi/webApi.vue"),
        meta: { title: "接口文档", parent: "setting" }
      },
      {
        path: "/setting/permission",
        name: "permission",
        component: () => import("./Empty.vue"),
        meta: { title: "权限管理", parent: "setting", noCache: true }
      },
      {
        path: "/setting/permission/role",
        name: "role",
        component: () => import("@/views/setting/permission/role/role.vue"),
        meta: { title: "角色管理", parent: "permission" }
      },
      {
        path: "/setting/permission/user",
        name: "user",
        component: () => import("@/views/setting/permission/user/user.vue"),
        meta: { title: "用户管理", parent: "permission" }
      }
    ]
  },
  {
    path: "/404",
    name: "404",
    component: () => import("@/views/404/404.vue"),
    meta: { hidden: true, title: "404" }
  },
  { path: "/:pathMatch(.*)*", name: "not-found", redirect: "/404" }
];

const router = createRouter({
  // createWebHistory(process.env.BASE_URL)
  history: createWebHashHistory(""),
  routes
});

export default router;
