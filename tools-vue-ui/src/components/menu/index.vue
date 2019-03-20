<template>
  <div class="">
    <h1>一级菜单管理</h1>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="path"
        label="地址"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="名称"
        width="180">
      </el-table-column>
      <el-table-column fixed="right" label="菜单级别">
        <template slot-scope="scope">
          是
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="primary" size="small"><i
            class="el-icon-edit el-icon--left"></i>编辑
          </el-button>
          <el-button type="danger" size="small"><i class="el-icon-delete el-icon--left"></i>删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="编辑菜单" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="地址" :label-width="formLabelWidth">
          <el-input v-model="form.path" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜单名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>
        <!--<el-form-item label="活动区域" :label-width="formLabelWidth">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="edit()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        tableData: [],
        params: {
          id: -1
        },
        RequestURL: '/menu',
        dialogFormVisible: false,
        form: {
          name: '',
          path: ''
        },
        formLabelWidth: '120px'
      }
    },
    created() {
      this.getMenu()
    },
    methods: {
      getMenu() {
        this.getRequest(this.RequestURL, this.params).then(res => {
          this.tableData = res.data.data
        }).catch(error => {
          console.log(error)
        })
      },
      handleClick(row) {
        console.log(row)
        this.form = row
        this.dialogFormVisible = true
      },
      edit() {
        console.log(this.form)
        this.putRequest(this.RequestURL, this.form).then(res => {
          let json=res.data
          if(json.code>=1){
            this.$message({
              message: '修改成功',
              type: 'success'
            });
            this.dialogFormVisible = false
          }else {
            this.$message({
              message: '修改失败',
              type: 'warning'
            });
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
