import { get, post } from './axios'

const BASE_URL = 'http://api.dodaily.cn';
const USER_LOGIN_URL = BASE_URL+"/user/login";

//用户登录
export const login = (username, password) => {
    return get(USER_LOGIN_URL, { username: username, password: password });
}
