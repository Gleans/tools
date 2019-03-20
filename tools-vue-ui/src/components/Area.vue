<template>
  <div class="app" align="center">
    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        prop="id"
        label="ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="postalcode"
        label="邮编"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
      <el-table-column
        prop="type"
        label="级别">
      </el-table-column>
    </el-table>
    <br>
    <el-pagination @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page.sync="currentPage"
                   :page-sizes="pageSizes"
                   :page-size="pageSizex"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="tableCount">
    </el-pagination>
  </div>
</template>

<script>

  export default {
    data() {
      return {
        tableData: [],
        currentPage: 1,
        pageSizes: [10, 20, 30, 40],
        pageSizex: 10,
        tableCount: 0,
        params: {
          pageNo:'',
          pageSize:''
        }
      }
    },
    created() {
      this.params.pageNo = this.currentPage
      this.params.pageSize = this.pageSizex
      this.getTable(this.params)
    },
    methods: {
      getTable(params) {
        this.postRequest('/getArea', params).then(res => {
          let resp=res.data.data
          this.tableData = resp.list
          this.tableCount = resp.total
          this.currentPage = resp.pageNum
          this.pageSizex = resp.pageSize
        }).catch(error => {
          console.log(error)
        })
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`)
        this.params.pageNo = this.currentPage
        this.params.pageSize = val
        this.getTable(this.params)
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`)
        console.log(this.pageSizex)
        this.params.pageSize = this.pageSizex
        this.params.pageNo = val
        this.getTable(this.params)
      }
    }
  }
</script>

<style scoped>

</style>
