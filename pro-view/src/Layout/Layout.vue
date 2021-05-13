<!--
 * @name: 
 * @Date: 2020-11-27 10:19:30
 * @LastEditTime: 2020-12-21 16:36:51
 * @FilePath: \vue3-typescript-element-admin\src\Layout\Layout.vue
 * @permission: 
-->
<template>
  <div class="flex">
    <!-- 左侧菜单 -->
    <md-menu />
    <div style="width:100%;">
      <!-- 头部信息 -->
      <md-header />
      <!-- 导航标签 -->
      <md-nav />
      <!-- 主体 -->
      <md-main />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

import mdMenu from "./model/menu.vue";
import mdHeader from "./model/header.vue";
import mdNav from "./model/nav.vue";
import mdMain from "./model/main.vue";

export default defineComponent({
  components: {
    mdMenu,
    mdHeader,
    mdNav,
    mdMain
  }
});
</script>
