<!--
 * @name: 
 * @Date: 2020-12-03 16:38:46
 * @LastEditTime: 2020-12-22 10:13:34
 * @FilePath: \vue3-typescript-element-admin\src\views\setting\permission\role\role.vue
 * @permission: 
-->
<template>
  <div>
    role
    <el-input v-model="test"></el-input>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";

export default defineComponent({
  name: "role",
  setup() {
    const test = ref("");
    return { test };
  }
});
</script>
