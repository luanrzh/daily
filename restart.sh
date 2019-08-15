#!/bin/bash
#关闭服务进程
JAR_PID=`ps -ef|grep -v grep | grep 'java -jar'|awk '{print $2}'`
if [ -n "$JAR_PID" ]; then
kill -9 $JAR_PID
echo "服务已终止(进程号:$JAR_PID)" 
fi
#启动服务
DAILY_API_DIR="/www/wwwroot/api.dodaily.cn/"
nohup java -jar ${DAILY_API_DIR}daily-api-0.0.1.jar >>${DAILY_API_DIR}daily_api_nohup.log &



