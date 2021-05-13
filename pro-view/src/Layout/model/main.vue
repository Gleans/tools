<!--
 * @name: 
 * @Date: 2020-12-21 16:09:16
 * @LastEditTime: 2020-12-22 16:56:38
 * @FilePath: \vue3-typescript-element-admin\src\Layout\model\main.vue
 * @permission: 
-->
<template>
  <div
    style="padding: 10px;
  box-sizing: border-box;"
  >
    <router-view v-slot="{ Component, route }">
      <transition name="fade" mode="out-in">
        <keep-alive :include="data.cachedViews">
          <component :is="Component" :key="route.fullPath" />
        </keep-alive>
      </transition>
    </router-view>
  </div>
</template>

<script lang="ts">
import Bus from "./bus";
import { defineComponent, computed, onMounted, reactive } from "vue";
import { useRoute, RouteRecordRaw } from "vue-router";

interface Data {
  cachedViews: Array<string | undefined | symbol>;
}

export default defineComponent({
  name: "app-main",
  setup() {
    const route = useRoute();
    const data: Data = reactive({ cachedViews: [] });
    const fullPath = computed(() => route.fullPath);

    onMounted(() => {
      const childrens = route.matched[0].children;
      childrens.forEach((item: RouteRecordRaw) => {
        if (item.meta && !item.meta.noCache) {
          data.cachedViews.push(item.name);
        }
      });

      // 如果标签被删除，则删除缓存
      Bus.$on("closeTag", (name: string | undefined | symbol) => {
        data.cachedViews.forEach(
          (item: string | undefined | symbol, index: number) => {
            if (item === name) {
              data.cachedViews.splice(index, 1);
            }
          }
        );
      });

      // 添加缓存
      Bus.$on("addCachedViews", (name: string | undefined | symbol) => {
        // nav.vue中已判断meta中是否有noCache
        if (!data.cachedViews.includes(name)) {
          data.cachedViews.push(name);
        }
      });
    });
    return { data, fullPath };
  }
});
</script>
