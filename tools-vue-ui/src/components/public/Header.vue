<template>
  <div id="header">
    <div class="home_title">
      <i class="iconfont icon-youhuiquan"></i>
      <span>后台管理系统</span>
    </div>
    <div class="home_userinfoContainer">
      <el-dropdown @command="handleCommand">
      <span class="el-dropdown-link home_userinfo">
        <i class="author"><img :src="srcuser" width="20" height="20"></i>
        <span>{{currentUserName}}</span>
        <i class="el-icon-arrow-down el-icon--right"></i>
      </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>个人中心</el-dropdown-item>
          <el-dropdown-item>设置</el-dropdown-item>
          <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
  import {postRequest} from '../../utils/api'
  import user from '@/assets/images/user.png'

  export default {
    name: "Header",
    data() {
      return {
        currentUserName: '游客',
        srcuser: user
      }
    },
    created() {
      this.currentUserName = sessionStorage.getItem('username')
    },
    methods: {
      handleCommand(command) {
        if (command === 'logout') {
          this.$confirm('注销登录吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            postRequest("/logout")
            this.currentUserName = '游客'
            sessionStorage.clear()
            this.$router.replace({path: '/login'})
            this.$message({
              type: 'success',
              message: '注销成功!'
            })
          }).catch(() => {
            this.$message({
              type: 'warning',
              message: '注销失败'
            })
          })
        }
      }
    }
  }
</script>

<style scoped>
  #header {
    width: 100%;
    height: 60px;
    overflow: hidden;
    position: absolute;
    z-index: 9999;
    left: 0;
    top: 0;
  }

  .home_userinfo {
    cursor: pointer;
    margin-right: 30px;
    color: cornsilk;
    height: 20px;
    display: inline-block;
    line-height: 20px;
  }

  .home_title {
    float: left;
    color: #fff;
    line-height: 60px;
    font-size: 22px;
    margin-left: 18px;
  }

  .home_userinfoContainer {
    /*display: inline;*/
    float: right;
    line-height: 60px;
  }

  .author {
    display: inline-block;
    height: 20px;
    vertical-align: middle;
    margin-right: 10px;
  }
</style>
