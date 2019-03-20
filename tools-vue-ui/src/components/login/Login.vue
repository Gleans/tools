<template>
  <div id="login">
    <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left" ref="loginForm"
             label-width="0px"
             v-loading="loading" v-show="isLogin" status-icon>
      <h3 class="login_title">{{title}}</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码" @keyup.enter.native="submitClick('loginForm')"></el-input>
      </el-form-item>
      <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary" @click="submitClick('loginForm')" style="width: 40%">登录</el-button>
        <el-button type="danger" @click="reg()" style="width: 40%">注册</el-button>
      </el-form-item>
    </el-form>
    <el-form :model="ruleForm2" :rules="rules2" class="login-container" label-position="left" ref="ruleForm2"
             label-width="0px"
             v-loading="loading" v-show="!isLogin" status-icon>
      <h3 class="login_title">{{title}}</h3>
      <el-form-item prop="name">
        <el-input v-model="ruleForm2.name" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="pass">
        <el-input type="password" v-model="ruleForm2.pass" auto-complete="off" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item prop="checkPass">
        <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="请确认密码"></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" @click="reguser('ruleForm2')" style="width: 40%">提交注册</el-button>
        <el-button type="danger" @click="goback()" style="width: 40%">去登陆</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  /*import {
    postRequest
  } from '../../utils/api'
  import {getRequest} from '../../utils/api'
  import {staticRouters} from '../../router/index'*/

  export default {
    data() {
      var checkAge = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('用户名不能为空'))
        } else {
          callback()
        }
      }
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          if (this.ruleForm2.checkPass !== '') {
            this.$refs.ruleForm2.validateField('checkPass')
          }
          callback()
        }
      }
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.ruleForm2.pass) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      }
      return {
        loginForm: {
          username: '',
          password: ''
        },
        rules: {
          username: [{
            required: true,
            message: '请输入用户名',
            trigger: 'blur'
          }],
          password: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }]
        },
        checked: false,
        loading: false,
        title: '系统登录',
        isLogin: true,
        ruleForm2: {
          pass: '',
          checkPass: '',
          name: ''
        },
        rules2: {
          pass: [{
            validator: validatePass,
            trigger: 'blur'
          }],
          checkPass: [{
            validator: validatePass2,
            trigger: 'blur'
          }],
          name: [{
            validator: checkAge,
            trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      submitClick(formName) {
        const vm = this
        this.$refs[formName].validate((valid) => {
          if (!valid) return
          this.loading = true
          vm.postRequest('/login', this.loginForm).then(resp => {
            if (resp.data.code === 1) {
              sessionStorage.setItem("username", resp.data.data)
              this.$message({message: '登陆成功！', type: 'success'})
              this.$router.replace({path: '/home'})
            } else {
              this.loading = false
              this.$message({
                message: '用户名密码错误或者连不上服务器！',
                type: 'warning'
              })
            }
          }).catch(error => {
            console.log(error)
            this.loading = false
            this.$message({
              message: '服务器出错！',
              type: 'error'
            })
          })
        })
      },
      reg() {
        this.$alert('暂不提供注册，演示账号：admin\r密码：123456', '提示', {
          confirmButtonText: '确定'
          /*callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }*/
        });
        // return
        // this.isLogin = false
        // this.title = '注册'
      },
      goback() {
        this.isLogin = true
        this.title = '后台管理系统登陆'
      },
      reguser(formName) {
        this.$refs[formName].validate((valid) => {
          if (!valid) return
          this.postRequest('reguser', this.ruleForm2).then(resp => {
            let json = resp.data
            if (json.data === -909) {
              this.$message({
                message: '用户名已被注册！',
                type: 'warning'
              })
            } else if (json.data >= 1) {
              this.loading = false
              this.$message({
                message: '注册成功,请登录...',
                type: 'success'
              })
              this.goback()
            } else {
              this.loading = false
              this.$message({
                message: '注册失败',
                type: 'warning'
              })
            }
          }).catch(error => {
            this.loading = false
            this.$message({
              message: '服务器出错！',
              type: 'error'
            })
          })
        })
      }
    }
  }
</script>
<style>
  #login {
    width: 100%;
    height: 100%;
    position: absolute;
    background: url("./bd.png") no-repeat;
    -webkit-background-size: cover;
    background-size: 100% 100%;
    text-align: center;
    overflow: hidden;
  }

  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    box-shadow: 0 0 25px #cac6c6;
    box-sizing: border-box;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #303133;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
    color: #303133;
  }
</style>
