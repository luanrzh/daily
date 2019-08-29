import axios from 'axios';

//设置默认的请求超时时间
axios.defaults.timeout = 10000;

//请求拦截
axios.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        // 做一些错误处理的操作
        return Promise.error(error);
    })

//响应拦截
axios.interceptors.response.use(
    res => res.status === 200 || res.status === 201 || res.status === 204 ? Promise.resolve(res) : Promise.reject(res),
    error => {
        return Promise.reject(error.response);
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