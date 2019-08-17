import { get, post } from './axios'

const BASE_URL = 'http://api.dodaily.cn';
const USER_LOGIN_URL = BASE_URL + "/user/login";
const TIME_TASK_URL = BASE_URL + "/time/tasks";
const TIME_TASK_ALL_URL = TIME_TASK_URL + "/all";
const TIME_TASL_TODAY_URL = TIME_TASK_URL + "/today";

//用户登录
export const login = (username, password) => {
    return get(USER_LOGIN_URL, { username: username, password: password });
}

//获取所有任务列表
export const getAllTaskList = () => {
    return get(TIME_TASK_ALL_URL);
}

//获取今日任务列表
export const getTodayTaskList = () => {
    return get(TIME_TASL_TODAY_URL);
}

//增加一个任务
export const addTask = (task) => {
    return post(TIME_TASK_URL, task);
}

