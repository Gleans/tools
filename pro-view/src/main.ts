import { createApp } from "vue";
import "normalize.css/normalize.css";
import "./registerServiceWorker";
import router from "./router/router";
import "./router/routerIntercept";
import ElementPlus from "element-plus";
import "@/assets/scss/index.scss"; // global css
import App from "./App.vue";
import SvgIcon from "@/components/SvgIcon/SvgIcon.vue";

// utils
import DateUtil from "@/utils/DateUtil";
//将 DateUtil 挂载到 vue 的原型上
// createApp.prototype.DateUtil = DateUtil;
createApp(App)
  .use(router)
  .use(ElementPlus)
  .provide("DateUtil", DateUtil)
  .component("svg-icon", SvgIcon)
  .mount("#app");

// 自动导入svg
const requireAll = (requireContext: __WebpackModuleApi.RequireContext) =>
  requireContext.keys().map(requireContext);
const req = require.context("./icons/svg", false, /\.svg$/);
requireAll(req);
