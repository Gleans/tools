import axios from 'axios'
// import {Baseurl} from "../../config/dev.env"
// import {Baseurl} from "../../config/prod.env"
axios.defaults.withCredentials = true //让ajax携带cookie

let base = 'ssm-demo'
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      "Content-Type": "application/json;charset=UTF-8"
    }
  })
}
/**/
/*export const uploadFileRequest = (url, params) => {
  return this.$axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}*/
export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
  /*  transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],*/
    headers: {
      "Content-Type": "application/json;charset=UTF-8"
      // 'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}
export const deleteRequest = (url) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`
  })
}
export const getRequest = (url, params) => {
  return axios({
    method: 'get',
    url: `${base}${url}`,
    params: params
  })
}
