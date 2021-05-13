/*
 * @name:
 * @Date: 2020-11-30 16:59:39
 * @LastEditTime: 2020-12-09 17:28:03
 * @FilePath: \vue3-typescript-element-admin\vue.config.js
 * @permission:
 */
// eslint-disable-next-line @typescript-eslint/no-var-requires
const path = require("path");
function resolve(dir) {
  return path.join(__dirname, ".", dir);
}
module.exports = {
  productionSourceMap: false,
  devServer: {
    port: 8000,
    open: false
  },
  // 使用svg-sprite-loader自动导入svg图标
  chainWebpack: config => {
    config.module
      .rule("svg")
      .exclude.add(resolve("src/icons"))
      .end();

    config.module
      .rule("icons")
      .test(/\.svg$/)
      .include.add(resolve("src/icons"))
      .end()
      .use("svg-sprite-loader")
      .loader("svg-sprite-loader")
      .options({
        symbolId: "icon-[name]"
      });
  }
};
