
#################################
##         Define path         ##
#################################
define_path(){
    ## Define release path
    WWW_ROOT="/www/wwwroot"
    WWW_DAILY="${WWW_ROOT}/daily.luanrz.cn"
    WWW_DAILY_API="${WWW_ROOT}/api.daily.luanrz.cn"
    WWW_DAILY_VUE="${WWW_ROOT}/vue.daily.luanrz.cn"

    ## Define project source path
    PROJECT_DAILY="$(pwd)"
    PROJECT_DAILY_API="${PROJECT_DAILY}/daily-api"
    PROJECT_DAILY_VUE="${PROJECT_DAILY}/daily-vue"

    ## Define distribution path
    DIST_DAILY="${PROJECT_DAILY}/docs/html"
    DIST_DAILY_API="${PROJECT_DAILY_API}/target"
    DIST_DAILY_VUE="${PROJECT_DAILY_VUE}/dist"

    ## Echo path
    echo -e "\n==================== Release Path ===================="
    echo "WWW_DAILY : ${WWW_DAILY}"
    echo "WWW_DAILY_API : ${WWW_DAILY_API}"
    echo "WWW_DAILY_VUE : ${WWW_DAILY_VUE}"
    echo -e "\n====================  Project Source Path ===================="
    echo "PROJECT_DAILY : ${PROJECT_DAILY}"
    echo "PROJECT_DAILY_API : ${PROJECT_DAILY_API}"
    echo "PROJECT_DAILY_VUE : ${PROJECT_DAILY_VUE}"
    echo -e "\n====================  Distribution Path ===================="
    echo "DIST_DAILY : ${DIST_DAILY}"
    echo "DIST_DAILY_API : ${DIST_DAILY_API}"
    echo "DIST_DAILY_VUE : ${DIST_DAILY_VUE}"
}

#################################
##      Update repository      ##
#################################
update_repository(){
    ## clone or pull repository
    echo -e "\n> Update repository"

    echo -e "\n# Update repository : daily"
    git pull

    echo -e "\n# Update repository : daily-api"
    if [ -d ${PROJECT_DAILY_API} ]; then
        cd ${PROJECT_DAILY_API}
        git pull
    else
        git clone https://github.com/luanrz/daily-api
    fi

    echo -e "\n# Update repository : daily-vue"
    if [ -d ${PROJECT_DAILY_VUE} ]; then
        cd ${PROJECT_DAILY_VUE}
        git pull
    else
        git clone https://github.com/luanrz/daily-vue
    fi
}

#################################
##            Build            ##
#################################
build(){
    echo -e "\n> Build"

    ## build daily-api
    echo -e "\n# Build : daily-api"
    cd ${PROJECT_DAILY_API}
    mvn clean package

    ## build daily-vue
    echo -e "\n# Build : daily-vue"
    cd ${PROJECT_DAILY_VUE}
    yarn install
    yarn build
}

#################################
##            Deploy           ##
#################################
deploy(){
    ## deploy daily-home
    sudo rm -rf ${WWW_DAILY}/*
    sudo cp -r  ${DIST_DAILY}/* ${WWW_DAILY}

    ## deploy daily-vue
    sudo rm -rf ${WWW_DAILY_VUE}/*
    sudo cp -r ${DIST_DAILY_VUE}/* ${WWW_DAILY_VUE}

    ## deploy daily-api
    jps | grep daily-api |awk '{print "kill -9 " $1}' | sh
    sudo rm ${WWW_DAILY_API}/*.jar
    sudo cp ${DIST_DAILY_API}/*.jar ${WWW_DAILY_API}
    cd ${WWW_DAILY_API}
    JAR_FILE=$(ls -lt *.jar | head -n 1 | awk '{print $NF}')
    nohup java -jar ${JAR_FILE}&
}


##########################
##      Main process    ##
##########################

define_path

update_repository

build

deploy