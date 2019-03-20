import axios from 'axios'
import {Message} from 'element-ui'
import store from '@/store'

axios.defaults.withCredentials = true //让ajax携带cookie
axios.defaults.timeout = 60000
axios.defaults.baseURL = 'http://localhost:8888/ssm-demo'


// create an axios instance
/*const service = axios.create({
  baseURL: "http://localhost:8888/ssm-demo", // api的base_url
  timeout: 5000, // request timeout
  withCredentials: true
})*/

// request interceptor
axios.interceptors.request.use(config => {
  // Do something before request is sent
 /* config.headers = {
    'Content-Type': 'application/json'
  }*/

  /*if (store.getters.token) {
    // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
    config.headers['X-Token'] = getToken()
  }*/
  // config.headers['Content-Type'] = 'application/json'
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// respone interceptor
axios.interceptors.response.use(
  response => response,
  error => {
    console.log('err' + error) // for debug
  /*  Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })*/
    return Promise.reject(error)
  })

axios.post = function  (url, data) {
  return axios({
    method: 'post',
    baseURL: axios.defaults.baseURL,
    url,
    data: data
  })
}

export default axios

