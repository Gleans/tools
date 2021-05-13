<template>
  <div>
    <el-button type="success" size="small" style="margin-bottom: 20px;">新增</el-button>
    <el-table :data="tableDataS" itemprop="child" :header-cell-style="tableHeaderColor"
              :row-style="cellStyle">

      <el-table-column style="padding: 0">
        <template #header style="padding: 0">
          <el-input
            v-model="xuhaosearch"
            size="mini"
            placeholder="输入序号搜索" style="margin-left: -10px;min-width: calc(17vh)" />
        </template>
        <el-table-column
          prop="xuhao"
          label="序号"
          width="120"
        />
      </el-table-column>

      <template v-slot:headerSearch>
        <el-table-column label="序号" type="index">
        </el-table-column>
      </template>
      <slot name="headerSearch">
        <div style="line-height: 14px;">产品编码</div>
        <el-input
          v-model="search.index"
          size="mini"
          clearable
          prefix-icon="el-icon-search"
          style="line-height: 30px;border: none;width: calc(100vh)"></el-input>
      </slot>

      <el-table-column label="条形码" prop="barCode"></el-table-column>
      <el-table-column label="产品名" prop="name"></el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts">

import { toRefs, defineComponent, reactive, watch, ref, onMounted } from "vue";

export default defineComponent({
  name: "house",
  setup() {
    const xuhaosearch = ref("");
    const data = reactive({
      tableData: [
        { xuhao: "sss111", barCode: "DEBD-124", name: "方圆扣" },
        { xuhao: "sss111", barCode: "DEBD-125", name: "方圆扣" },
        { xuhao: "sss111", barCode: "DEBD-126", name: "方圆扣" },
        { xuhao: "sss111", barCode: "DEBD-127", name: "方圆扣" }
      ],
      address: "header",
      search: {
        index: ""
      },
      tableDataS: [{}]
    });

    onMounted(() => {
      data.tableDataS = data.tableData;
    });

    watch(xuhaosearch, (newValue) => {
      console.log(newValue);
      data.tableDataS = data.tableData.filter(
        s => s && s.xuhao.includes(newValue)
      );
    });

    function cellStyle({ row, column, rowIndex, columnIndex }: never) {
      if (rowIndex === 0) {
        return "padding:0px;";
      }
    }

    function tableHeaderColor({ row, column, rowIndex, columnIndex }: never) {
      if (columnIndex === 0) {
        return "padding:0;background-color: lightblue;color: #fff;font-weight: 500;";
      }
      if (rowIndex === 0) {
        return "padding:0;background-color: lightblue;color: #fff;font-weight: 500;";
      }
    }

    return { ...toRefs(data), xuhaosearch, tableHeaderColor, cellStyle };
  }
});
</script>

<style scoped>
.el-table--border th:first-child .cell {
  padding-left: 0 !important;
}
</style>
