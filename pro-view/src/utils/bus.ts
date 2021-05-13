/*
 * @name:
 * @Date: 2020-11-27 17:39:31
 * @LastEditTime: 2020-12-22 15:31:21
 * @FilePath: \vue3-typescript-element-admin\src\utils\bus.ts
 * @permission:
 */
// 为保持和vue2版本中使用bus一致，emit,on,off前面都加了$
class Bus {
  list: { [key: string]: Function[] };
  constructor() {
    // 收集订阅信息,调度中心
    this.list = {};
  }

  // 订阅
  $on(name: string, fn: Function) {
    this.list[name] = this.list[name] || [];
    this.list[name].push(fn);
  }

  // 发布
  $emit(
    name: string,
    data?: string | symbol | null | undefined | object | any[]
  ) {
    if (this.list[name]) {
      this.list[name].forEach((fn: Function) => {
        fn(data);
      });
    }
  }

  // 取消订阅
  $off(name: string) {
    if (this.list[name]) {
      delete this.list[name];
    }
  }
}
export default Bus;
