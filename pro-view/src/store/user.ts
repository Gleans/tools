/*
 * @name:
 * @Date: 2020-11-27 09:49:28
 * @LastEditTime: 2021-01-23 14:27:10
 * @FilePath: \vue3-typescript-element-admin\src\store\user.ts
 * @permission:
 */
// import { createStore } from "vuex";

// export default createStore({
//   state: {
//     user: {
//       token: "",
//       username: "",
//       id: 0,
//       role: ""
//     }
//   },
//   mutations: {
//     SET_USER(state, val) {
//       state.user = val;
//     }
//   },
//   actions: {
//     setUser({ commit }, val) {
//       commit("SET_USER", val);
//     }
//   }
// });
interface User {
  username: string;
  userId: number;
  roles: string;
}
import { reactive } from "vue";

export const user = reactive({
  username: "",
  userId: 0,
  roles: ""
});

export function setUser(val: User) {
  user.userId = val.userId;
  user.roles = val.roles;
  user.username = val.username;
}
