<template>
  <template v-for="item in menus" :key="item.name">
    <!-- 有children -->
    <el-submenu v-if="item.children" :index="item.path">
      <!-- 层级的标题 -->
      <template #title>
        <i class="el-icon-s-management"></i>
        <span>{{ item.meta.title }}</span>
      </template>
      <!-- 层级的内容 -->
      <template
        v-for="cell in item.children"
        :key="cell.path"
        :index="cell.path"
      >
        <!-- 如果还有子集递归 -->
        <template v-if="cell.children">
          <el-submenu :index="cell.path">
            <template #title>
              <i class="el-icon-s-management"></i>
              <span>{{ cell.meta.title }}</span>
            </template>
            <md-menu-item :menus="cell.children"></md-menu-item>
          </el-submenu>
        </template>

        <!-- 如果没有直接渲染 -->
        <el-menu-item v-else :index="cell.path">
          <i class="el-icon-s-check"></i>
          <span>{{ cell.meta.title }}</span>
        </el-menu-item>
      </template>
    </el-submenu>

    <!-- 没有children -->
    <el-menu-item v-else :index="item.path">
      <i class="el-icon-s-check"></i>
      <span>{{ item.meta.title }}</span>
    </el-menu-item>
  </template>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "md-menu-item",
  props: {
    menus: {
      type: Array,
      require: true
    }
  }
});
</script>
