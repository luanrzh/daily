中文版  | [English Version](./README_EN.md)

# 简介 
 [首页](http://daily.luanrz.cn "点击进入Daily首页") | 
 [API后端](https://api.daily.luanrz.cn/doc.html#/plus/zh-CN "点击进入API后端页面") | 
 [Vue前端](http://vue.daily.luanrz.cn "点击进入Vue前端页面") | 
 [微信小程序](http://daily.luanrz.cn/daily_wechat_preview.html "点击显示小程序码") 

DAILY是一款跨平台的待办事项小工具，它支持在命令行界面、PC网页、微信小程序、安卓APP等环境中运行。 

# 项目结构

DAILY由一个后端与多个前端组成，任意前端都可以与后端API组成一个完整的应用。

| 项目                                                       | 运行平台    | 技术栈             | 状态   |
| ---------------------------------------------------------- | ----------- | ------------------ | ------ |
| [`daily-api`](https://github.com/luanrz/daily-api)         | Linux服务器 | `Spring Boot`      | 开发中 |
| [`daily-cli`](https://github.com/luanrz/daily-cli)         | Linux终端   | `Python3`          | 开发中 |
| [`daily-vue`](https://github.com/luanrz/daily-vue)         | PC浏览器    | `Vue3`             | 开发中 |
| [`daily-wechat`](https://github.com/luanrz/daily-wechat)   | 微信客户端  | `WXML` `WXSS` `JS` | 开发中 |
| [`daily-android`](https://github.com/luanrz/daily-android) | 安卓手机    | `Android`          | 计划中 |


# 项目初始化

1. 克隆项目

```
git clone https://github.com/luanrz/daily
```

2. 初始化子项目

```
cd daily
sh ./init-daily.sh
```
