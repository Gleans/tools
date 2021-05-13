<!--
 * @name: 
 * @Date: 2020-11-27 10:44:06
 * @LastEditTime: 2020-12-21 14:08:23
 * @FilePath: \vue3-typescript-element-admin\src\views\rent\contract\contract.vue
 * @permission: 
-->

<template>
  <div>
    合同
    <el-input v-model="test"></el-input>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";

export default defineComponent({
  name: "contract",
  setup() {
    const test = ref("");
    return { test };
  }
});
</script>

<style scoped></style>
