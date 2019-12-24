DAILY_FRONTEND_DIR="/www/wwwroot/www.dodaily.cn/"
DAILY_API_DIR="/www/wwwroot/api.dodaily.cn/"
time=$(date "+%Y%m%d%H%M%S")
BAK_DIR="/www/wwwroot/bak/$time"

echo -e "\n----------更新github----------"
cd /root/daily
git pull origin master
echo -e "\n----------构建daily-front----------"
cd /root/daily/daily-frontend
# npm install
npm run build
echo -e "\n----------构建daily-api----------"
cd /root/daily/daily-api
mvn clean package -DskipTests
echo -e "\n----------备份daily-front----------"
mkdir -p $BAK_DIR
mv $DAILY_FRONTEND_DIR* $BAK_DIR
echo -e "\n----------备份daily-api----------"
mv $DAILY_API_DIR* $BAK_DIR
echo -e "\n----------部署www.daily.cn----------"
mv /root/daily/daily-frontend/dist/* $DAILY_FRONTEND_DIR
echo -e "\n----------部署api.daily.cn----------"
mv target/daily-api-0.0.1.jar $DAILY_API_DIR
#关闭服务进程
JAR_PID=$(ps -ef | grep -v grep | grep 'java -jar' | awk '{print $2}')
if [ -n "$JAR_PID" ]; then
    kill -9 $JAR_PID
fi
#启动服务
DAILY_API_DIR="/www/wwwroot/api.dodaily.cn/"
nohup java -jar ${DAILY_API_DIR}daily-api-0.0.1.jar >>${DAILY_API_DIR}daily_api_nohup.log &
echo -e "\n----------ALL DONE!----------"
