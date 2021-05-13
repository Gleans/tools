<template>
  <div>
    <el-table :data="tableDataS" itemprop="child" :header-cell-style="tableHeaderColor">

      <el-table-column>
        <template #header>
          <el-input
            v-model="xuhaosearch"
            size="mini"
            placeholder="输入关键字搜索" />
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
          style="line-height: 24px;"></el-input>
      </slot>

      <el-table-column label="条形码" prop="barCode"></el-table-column>
      <el-table-column label="产品名" prop="name"></el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts">
import { toRefs, defineComponent, reactive, watch, ref, onMounted } from "vue";

export default defineComponent({
  name: "order",
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

    function tableHeaderColor({ row, column, rowIndex, columnIndex }: never) {
      if (columnIndex === 0) {
        return "padding:0";
      }
      if (rowIndex === 0) {
        return "background-color: lightblue;color: #fff;font-weight: 500;";
      }
    }

    return { ...toRefs(data), xuhaosearch, tableHeaderColor };
  }
});
</script>

<style scoped>
.cell {
  padding: 0px !important;
}

.searchStyle {
  padding: 0 !important;
}

</style>
