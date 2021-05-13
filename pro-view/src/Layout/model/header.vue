<!--
 * @name: 
 * @Date: 2020-11-27 17:14:22
 * @LastEditTime: 2021-01-23 16:09:23
 * @FilePath: \vue3-typescript-element-admin\src\Layout\model\header.vue
 * @permission: 
-->
<template>
  <div class="header">
    <div class="left">
      <!-- 展开收起菜单的图标 -->
      <div @click="changeMenu">
        <svg
          :class="{ 'is-active': isCollapse }"
          class="hamburger"
          viewBox="0 0 1024 1024"
          xmlns="http://www.w3.org/2000/svg"
          width="64"
          height="64"
        >
          <path
            d="M408 442h480c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8H408c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8zm-8 204c0 4.4 3.6 8 8 8h480c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8H408c-4.4 0-8 3.6-8 8v56zm504-486H120c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 632H120c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zM142.4 642.1L298.7 519a8.84 8.84 0 0 0 0-13.9L142.4 381.9c-5.8-4.6-14.4-.5-14.4 6.9v246.3a8.9 8.9 0 0 0 14.4 7z"
          />
        </svg>
      </div>

      <!-- 当前路由路径 -->
      <el-breadcrumb v-if="data.routeList.length > 0" separator="/">
        <el-breadcrumb-item v-for="item in data.routeList" :key="item.path">{{
          item.meta.title
        }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="right flex-center">
      <h3 style="margin-right:20px;">{{ username }}</h3>
      <el-dropdown trigger="hover">
        <div class="avatar-wrapper">
          <img
            src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"
            class="user-avatar"
          />
          <i class="el-icon-caret-bottom" />
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <a
              target="_blank"
              href="https://github.com/jddk/vue3-typescript-element-admin/"
            >
              <el-dropdown-item>Github</el-dropdown-item>
            </a>
            <!-- <a
              target="_blank"
              href="https://panjiachen.github.io/vue-element-admin-site/#/"
            >
              <el-dropdown-item>Docs</el-dropdown-item>
            </a> -->
            <el-dropdown-item divided @click="toLogout">
              退出登录
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script lang="ts">
import {
  defineComponent,
  ref,
  reactive,
  onMounted,
  onBeforeUnmount,
  computed
} from "vue";
import {
  useRouter,
  useRoute,
  onBeforeRouteUpdate,
  RouteRecordRaw,
  RouteLocationNormalized
} from "vue-router";
import { user } from "@/store/user";
import Bus from "./bus";
// 当前路由层级计算
function getRouteList() {
  interface Data {
    routeList: RouteRecordRaw[];
  }
  const data = reactive<Data>({
    routeList: []
  });
  // 根据路由path计算
  function pathToArr(path: string, routes: RouteRecordRaw[]) {
    const pathArr: string[] = path.split("/").splice(1);
    const arr = [];
    for (const i of pathArr) {
      for (const j of routes) {
        if (i === j.name) {
          arr.push(j);
          break;
        }
      }
    }
    data.routeList = arr;
  }

  // 初始化
  onMounted(() => {
    const route = useRoute();
    pathToArr(route.path, route.matched[0].children);
  });
  // 路由更新
  onBeforeRouteUpdate((to: RouteLocationNormalized) => {
    pathToArr(to.path, to.matched[0].children);
  });

  return data;
}
export default defineComponent({
  name: "app-header",
  setup() {
    // 1、展开收起侧边栏逻辑
    const isCollapse = ref(true);
    function changeMenu() {
      isCollapse.value = !isCollapse.value;
      Bus.$emit("change-menu");
    }
    onBeforeUnmount(() => {
      Bus.$off("change-menu");
    });

    // 2、退出登录
    const router = useRouter();
    function toLogout() {
      localStorage.removeItem("TOKEN-VUE3-TS-EL-ADMIN");
      router.push({ path: "/login" });
      location.reload();
    }

    // 3、当前路由层级计算
    const data = getRouteList();

    // 4、用户信息
    const username = computed(() => user.username);

    return { isCollapse, changeMenu, toLogout, data, username };
  }
});
</script>

<style lang="scss" scoped>
.header {
  width: 100%;
  height: 50px;
  border-bottom: 2px solid #f0f0f0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px;
  box-sizing: border-box;
}
.header-btn {
  margin-right: 10px;
  font-size: 20px;
  cursor: pointer;
}
.left {
  display: flex;
  align-items: center;
}

// 切换菜单按钮
.hamburger {
  display: inline-block;
  vertical-align: middle;
  width: 20px;
  height: 20px;
  margin-right: 12px;
  cursor: pointer;
}

.hamburger.is-active {
  transform: rotate(180deg);
}

// 头像
.user-avatar {
  cursor: pointer;
  width: 40px;
  height: 40px;
  border-radius: 10px;
}
</style>
