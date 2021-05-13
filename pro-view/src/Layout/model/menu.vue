<template>
  <div style="width: calc(38vh)">
    <el-menu
        :default-active="currentRoute"
        :router="true"
        :collapse="isCollapse"
        :collapse-transition="true"
        @select="toSelect"
    >
      <md-menu-item :menus="treeMenus" />
    </el-menu>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, reactive, onMounted, computed } from "vue";
import { useRouter, useRoute, RouteRecordRaw } from "vue-router";
import mdMenuItem from "./menu-item.vue";
import Bus from "./bus";

// 将路由转成树
function routesToTree() {
  interface Menus {
    path: string;
    name: string | symbol | undefined;
    meta?: {
      [x: string]: string | symbol | boolean;
      [x: number]: string | symbol | boolean;
    };
    comments?: string;
    children?: Menus[];
  }

  const router = useRouter();
  const routes: RouteRecordRaw[] = router.options.routes;
  let menus: Menus[] = [];
  let treeMenus: Menus[] = [];
  for (let i = 0; i < routes.length; i++) {
    if (routes[i].path === "/") {
      const children: RouteRecordRaw[] = routes[i].children || [];
      menus = children.map((item: RouteRecordRaw) => {
        return {
          path: item.path,
          name: item.name,
          meta: { ...item.meta }
        };
      });
      break;
    }
  }

  menus.forEach((item: Menus) => {
    menus.forEach((cell: Menus) => {
      if (item.meta && item.meta.parent === cell.name) {
        cell.children = cell.children || [];
        cell.children.push(item);
      }
    });
  });
  treeMenus = menus.filter((item: Menus) => {
    if (item.meta) {
      return !item.meta.parent;
    }
  });
  return treeMenus;
}

export default defineComponent({
  name: "app-menu",
  components: { mdMenuItem },
  setup() {
    // 1、选择路由
    const router = useRouter();

    function toSelect(path: string) {
      router.push({ path: path });
    }

    // 2、左侧菜单展开收起
    const isCollapse = ref(false);
    onMounted(() => {
      Bus.$on("change-menu", () => {
        isCollapse.value = !isCollapse.value;
      });
    });

    // 3、高亮当前路由
    const route = useRoute();
    const currentRoute = computed(() => route.path);

    // 4、路由转树
    const treeMenus = reactive(routesToTree());
    return { isCollapse, currentRoute, toSelect, treeMenus };
  }
});
</script>

<style lang="scss" scoped>
.menu {
  box-shadow: 2px 2px 2px #f0f0f0;
}

.el-menu {
  height: 100%;
  min-height: calc(100vh);
  overflow-y: auto;
}

.menu {
  // 整个滚动条
  ::-webkit-scrollbar {
    width: 10px;
    background: #304156;
  }

  /*滚动条中间滑动部分*/
  ::-webkit-scrollbar-thumb {
    border-radius: 10px;
    background: #304156;
  }

  ::-webkit-scrollbar-thumb:hover {
    background: rgba(144, 147, 153, 0.3);
  }
}
</style>
