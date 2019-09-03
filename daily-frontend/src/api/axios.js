import axios from 'axios';
import store from "@/store";
import router from "@/router";
import { Message } from 'element-ui'

//设置默认的请求超时时间
axios.defaults.timeout = 10000;

//请求拦截
axios.interceptors.request.use(
    config => {
        config.headers.Authorization = store.getAuthorization();
        console.log('请求拦截', config);
        return config;
    },
    error => {
        console.log('请求拦截出错', error)
        // 做一些错误处理的操作
        return Promise.error(error);
    })

//响应拦截
axios.interceptors.response.use(
    res => {
        console.log('响应拦截', res)
        var status = res.status;
        if (status === 200 || status === 201 || status === 204) {
            return Promise.resolve(res)
        }
    },
    err => {
        console.log('响应拦截出错', err.response);
        var status = err.response.status;
        if (status === 401) {
            router.replace({ name: "login" })
            Message("Token失效,请重新登录")
        }
        return Promise.reject(err.response);
    })

//get方法
export const get = (url, params) => {
    return axios({
        method: 'get',
        url: url,
        params: params
    });
}

//post方法
export const post = (url, params) => {
    return axios({
        method: 'post',
        url: url,
        data: params
    })
}

//put方法
export const put = (url, params) => {
    return axios({
        method: 'put',
        url: url,
        data: params
    })
}

//delete方法
export const del = (url, params) => {
    return axios({
        method: 'delete',
        url: url,
        data: params
    })
}