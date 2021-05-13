<!--
 * @name: 
 * @Date: 2020-12-02 14:12:56
 * @LastEditTime: 2021-01-23 14:32:28
 * @FilePath: \vue3-typescript-element-admin\src\views\login\login.vue
 * @permission: 
-->
<template>
  <div class="login-container">
    <el-form
      ref="elForm"
      :model="data.form"
      :rules="data.rules"
      class="login-form"
      label-position="right"
    >
      <div class="title-container">
        <h3 class="title">Login</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          v-model="data.form.username"
          placeholder="请输入用户名"
          name="username"
          type="text"
          tabindex="1"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="data.passwordType"
          v-model="data.form.password"
          :type="data.passwordType"
          placeholder="请输入密码"
          name="password"
          tabindex="2"
          @keyup.enter="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon
            :icon-class="data.passwordType === 'password' ? 'eye' : 'eye-open'"
          />
        </span>
      </el-form-item>
      <el-form-item class="el-form-item-none" label="记住密码" align="right">
        <el-checkbox
          v-model="data.form.remember"
          @change="changeRemember"
        ></el-checkbox>
      </el-form-item>

      <el-button
        :loading="data.loading"
        type="primary"
        style="width: 100%; margin-bottom: 30px"
        @click.prevent="handleLogin"
      >登录
      </el-button>
      <p style="color:#fff;">账号：admin 密码：123456</p>
    </el-form>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { login } from "@/api/login";
import { ElNotification } from "element-plus";
import { setUser } from "@/store/user";

export default defineComponent({
  name: "login",
  setup() {
    const elForm = ref(null);
    const data = reactive({
      form: {
        username: "admin",
        password: "admin",
        remember: false
      },
      rules: {
        username: [{ required: true, trigger: "blur", message: "不能为空" }],
        password: [{ required: true, trigger: "blur", message: "不能为空" }]
      },
      loading: false,
      passwordType: "password"
    });

    // 初始化如果有记住密码，密码赋值
    onMounted(() => {
      if (localStorage.getItem("remember")) {
        data.form = {
          username: localStorage.getItem("username") || "",
          password: localStorage.getItem("password") || "",
          remember: true
        };
      }
    });

    // 显示和加密密码
    function showPwd() {
      if (data.passwordType === "password") {
        data.passwordType = "";
      } else {
        data.passwordType = "password";
      }
    }

    // 勾选记住密码
    function changeRemember(val: boolean) {
      if (val) {
        localStorage.setItem("remember", "true");
        localStorage.setItem("username", data.form.username);
        localStorage.setItem("password", data.form.password);
      } else {
        localStorage.removeItem("remember");
        localStorage.removeItem("username");
        localStorage.removeItem("password");
      }
    }

    // 点击登录
    const router = useRouter();

    function handleLogin() {
      if (data.form.remember) {
        localStorage.setItem("remember", "true");
        localStorage.setItem("username", data.form.username);
        localStorage.setItem("password", data.form.password);
      } else {
        localStorage.removeItem("remember");
        localStorage.removeItem("username");
        localStorage.removeItem("password");
      }

      (elForm.value as any).validate((valid: boolean) => {
        if (valid) {
          data.loading = true;
          login(data.form).then((res: any) => {
            data.loading = false;
            if (res.code !== 0) {
              ElNotification({ type: "error", title: res.msg });
            } else {
              ElNotification({ type: "success", title: "登录成功" });
              setUser({
                userId: res.data.userId,
                roles: res.data.roles,
                username: res.data.username
              });
              localStorage.setItem("TOKEN-VUE3-TS-EL-ADMIN", res.data.token);
              router.push({ path: "/" });
            }
          }).catch(e => {
            console.log(e);
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    }

    return {
      data,
      elForm,
      showPwd,
      handleLogin
    };
  }
});
</script>
<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }

  .el-form-item-none {
    border: none;
    background: none;
    border-radius: 0;
  }

  .el-form-item-none .el-form-item__label {
    width: 94%;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100vh;
  width: 100vw;
  box-sizing: border-box;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
