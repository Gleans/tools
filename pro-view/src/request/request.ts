/*
 * @name:
 * @Date: 2020-12-24 12:06:10
 * @LastEditTime: 2021-02-20 17:05:39
 * @FilePath: \vue3-typescript-element-admin\src\request\request.ts
 * @permission:
 */
import axios from "axios";
import router from "@/router/router";
import { ElNotification, ElMessageBox } from "element-plus";
// import store from "../store/store";

// 创建axios实例
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // api 的 base_url
  timeout: 60000 // 请求超时时间
});

// request拦截器
service.interceptors.request.use(
  config => {
    // login接口不传token
    if (
      localStorage.getItem("TOKEN-VUE3-TS-EL-ADMIN") &&
      config.url != "auth/login"
    ) {
      config.headers["Authorization"] =
        "Bearer " + localStorage.getItem("TOKEN-VUE3-TS-EL-ADMIN"); // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    config.headers["Content-Type"] = "application/json";
    return config;
  },
  error => {
    // Do something with request error
    console.log(error); // for debug
    Promise.reject(error);
  }
);

// response 拦截器
service.interceptors.response.use(
  response => {
    const skipPath: boolean = router.currentRoute.value.fullPath === "/login";
    const statusCode = response.status;

    const networkPass: boolean = statusCode === 200;

    if (networkPass && response.data.code === 0) {
      return response.data;
    }
    // 未登录处理
    if (response.data.code === 401 && !skipPath) {
      ElMessageBox.confirm(
        "登录状态已过期，您可以继续留在该页面，或者重新登录",
        "系统提示",
        {
          confirmButtonText: "重新登录",
          cancelButtonText: "取消",
          type: "warning"
        }
      ).then(() => {
        localStorage.removeItem("TOKEN-VUE3-TS-EL-ADMIN");
        router.push({ path: "/login" }).then(res => console.log(res));
      });
    } else if (response.data.code === 401) {
      return response.data;
    } else {
      ElNotification({
        title: "异常提示",
        type: "error",
        message: !networkPass ? "网络异常!" : response.data.msg
      });
      return Promise.reject("error");
    }
  },
  error => {
    let code = 0;
    try {
      code = error.response.data.status;
    } catch (e) {
      if (error.toString().indexOf("Error: timeout") !== -1) {
        ElNotification({
          title: "异常提示",
          type: "error",
          message: "网络请求超时"
        });
        return Promise.reject(error);
      }
      if (error.toString().indexOf("Error: Network Error") !== -1) {
        ElNotification({
          title: "异常提示",
          type: "error",
          message: "网络请求超时"
        });
        return Promise.reject(error);
      }
    }
    if (code === 401) {
      ElMessageBox.confirm(
        "登录状态已过期，您可以继续留在该页面，或者重新登录",
        "系统提示",
        {
          confirmButtonText: "重新登录",
          cancelButtonText: "取消",
          type: "warning"
        }
      ).then(() => {
        localStorage.removeItem("TOKEN-VUE3-TS-EL-ADMIN");
        router.push({ path: "/login" });
        location.reload();
      });
    } else if (code === 403) {
      router.push({ path: "/401" });
    } else {
      const errorMsg = error.response.data.message;
      if (errorMsg !== undefined) {
        ElNotification({
          title: "异常提示",
          type: "error",
          message: errorMsg
        });
      }
    }
    return Promise.reject(error);
  }
);
export default service;
