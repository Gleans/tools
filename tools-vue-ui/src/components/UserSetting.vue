<template>
  <div id="app">
    <el-table ref="singleTable" :data="tableData" highlight-current-row @current-change="handleCurrentChange" style="width: 100%">
      <el-table-column type="index" label="ID" width="50">
      </el-table-column>
      <el-table-column property="username" label="用户名" width="190%">
      </el-table-column>
      <el-table-column property="email" label="邮箱" width="180%">
      </el-table-column>
      <el-table-column property="enable" label="是否启用" width="100%">
      </el-table-column>
      <el-table-column property="locked" label="是否锁定" width="100%">
      </el-table-column>
      <el-table-column property="createTime" label="创建时间" width="180%">
      </el-table-column>
      <el-table-column property="lastTime" label="最后登录时间" width="180%">
      </el-table-column>
      <el-table-column label="操作" width="160%">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px">
      <el-button type="primary" @click="addUser()">添加用户</el-button>
      <el-button type="danger">注销当前用户</el-button>
    </div>
    <template>
      <el-dialog title="添加用户" :visible.sync="dialogVisible" width="30%" center :close-on-click-modal="false">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="活动名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>

      </el-dialog>
    </template>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [],
        currentRow: null,
        params: {
          pageNum: 0,
          pageSize: 10
        },
        dialogVisible: false,
        form: {
          name: ''
        }
      }
    },
    created() {
      this.initTable()
    },
    methods: {
      setCurrent(row) {
        this.$refs.singleTable.setCurrentRow(row)
      },
      handleCurrentChange(val) {
        this.currentRow = val
      },
      initTable() {
        this.getRequest("/user", this.params).then(res => {
          console.log(res)
          this.tableData = res.data.data.list
        }).catch(error => {
          console.log(error)
        })
      },
      addUser() {
        this.dialogVisible = true
      }
    }
  }

</script>
