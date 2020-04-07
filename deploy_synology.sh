CODE_DIR="/var/services/homes/www/CodeFree/luanrzh/daily"
CODE_DIR_FE=${CODE_DIR}/daily-frontend
CODE_DIR_API=${CODE_DIR}/daily-api
DEPOLY_FRONTEND_DIR="/volume1/web/daily"

# Maven和NPM的前置处理
which mvn > /dev/null
if [ $? -eq 1 ];then
    MAVEN_HOME="/var/services/homes/www/Program/apache-maven-3.6.2"
    export PATH=${MAVEN_HOME}/bin:${PATH}
fi
which npm > /dev/null
if [ $? -eq 1 ];then
    NPM_HOME="/var/services/homes/www/Program/node-v12.14.0-linux-x64"
    export PATH=${NPM_HOME}/bin:${PATH}
fi


echo -e "\n----------更新github----------"
cd ${CODE_DIR}
git checkout -f # 丢弃所有更改，避免产生冲突
git pull origin master

echo -e "\n----------构建部署daily-front----------"
cd ${CODE_DIR_FE}
sed -i "s/api.dodaily.cn/192.168.1.105:8000/" src/api/api.js 
npm run build
rm -rf ${DEPOLY_FRONTEND_DIR}
mv ${CODE_DIR_FE}/dist $DEPOLY_FRONTEND_DIR

echo -e "\n----------构建部署daily-api----------"
cd ${CODE_DIR_API}
# sed -i "s/www.dodaily.cn/192.168.1.105/" src/main/resources/application.yml
mvn clean package -DskipTests
mv target/daily-api-0.0.1.jar ${DEPOLY_FRONTEND_DIR}

echo -e "\n----------重启daily-api----------"
#关闭服务进程
JAR_PID=$(ps -ef | grep -v grep | grep 'java -jar' | awk '{print $2}')
if [ -n "$JAR_PID" ]; then
    kill -9 $JAR_PID
fi
#启动服务
nohup java -jar ${DEPOLY_FRONTEND_DIR}/daily-api-0.0.1.jar >>${DEPOLY_FRONTEND_DIR}/daily_api_nohup.log &

echo -e "\n----------ALL DONE!----------"
