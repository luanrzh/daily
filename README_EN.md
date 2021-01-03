[中文版](./README.md) | English Version

# Introduction of "DAILY" 
 [Home](http://daily.luanrz.cn "Click to enter the DAILY app home page") | 
 [API back-end](https://api.daily.luanrz.cn/doc.html#/plus/en-US "Click to enter the DAILY-API page") | 
 [Vue front-end](http://vue.daily.luanrz.cn "Click to enter the DAILY-VUE page") | 
 [Wechat mini program ](http://daily.luanrz.cn/daily_wechat_preview.html "Click to preview the mini program code") 

DAILY is a cross-platform to-do gadget which can running in the environment of CLI, PC web page, wechat mini program, android app and more.

# Project structure

DAILY consists of a back end and multiple front ends, and any front end can form a complete application with the back end API.

| project                                                    | platform             | technology stack   | status     |
| ---------------------------------------------------------- | -------------------- | ------------------ | ---------- |
| [`daily-api`](https://github.com/luanrz/daily-api)         | Linux server         | `Spring boot`      | Developing |
| [`daily-cli`](https://github.com/luanrz/daily-cli)         | Linux terminal       | `Python3`          | Developing |
| [`daily-vue`](https://github.com/luanrz/daily-vue)         | PC web browser       | `Vue3`             | Developing |
| [`daily-wechat`](https://github.com/luanrz/daily-wechat)   | WeChat client        | `WXML` `WXSS` `JS` | Developing |
| [`daily-android`](https://github.com/luanrz/daily-android) | Android mobile phone | `Android`          | Planning   |

# Project initialization

1. Clone project

```
git clone https://github.com/luanrz/daily
```

2. Initialize subproject

```
cd daily
sh ./init-daily.sh
```
