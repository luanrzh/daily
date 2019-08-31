import { get, post, del, put } from './axios'

const BASE_URL = 'http://api.dodaily.cn';
// const BASE_URL = 'http://localhost:8000';
const USER_LOGIN_URL = BASE_URL + "/user/login";
const TIME_TASK_URL = BASE_URL + "/time/tasks";
const TIME_TASK_STEP_URL = TIME_TASK_URL + "/steps";
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
    var params = new URLSearchParams();
    params.append('content', task.content);
    params.append('deadlineTime', task.deadlineTime);
    params.append('userId', task.userId);
    return post(TIME_TASK_URL, params);
}

//删除一个任务
export const deleteTask = (task) => {
    var params = new URLSearchParams();
    params.append('id', task.id);
    return del(TIME_TASK_URL, params);
}

//更新一个任务
export const updateTask = (task) => {
    var params = new URLSearchParams();
    if (task.content) {
        params.append('content', task.content);
    }
    if (task.status) {
        params.append('status', task.status);
    }
    if (task.deadlineTime) {
        params.append('deadlineTime', task.deadlineTime);
    }
    return put(TIME_TASK_URL, params);
}

//增加一个任务步骤
export const addStep = (taskStep) => {
    var params = new URLSearchParams();
    params.append('taskId', taskStep.taskId);
    params.append('content', taskStep.content);
    return post(TIME_TASK_STEP_URL, params);
}

//删除一个任务步骤
export const deleteStep = (step) => {
    var params = new URLSearchParams();
    params.append('id',step.id);
    return del(TIME_TASK_STEP_URL, params);
}

//修改一个任务步骤
export const updateStep = (step) => {
    var params = new URLSearchParams();
    return put(TIME_TASK_STEP_URL, params);
}

