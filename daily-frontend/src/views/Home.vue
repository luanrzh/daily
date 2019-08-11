<template>
  <div class="home">
    <el-container class="home-container">
      <el-header class="home-header">
        <div class="home_header_title">日常DAILY</div>
        <div class="home_header_user">
          <el-dropdown>
            <span style="color: white;">栾睿智</span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-aside class="home-aside" width="200px">
          <el-menu router :default-openeds="menu.default_openeds">
            <el-submenu v-for="(submenu,index) in menu.submenus" :key="index" :index="index+''">
              <template slot="title">
                <i :class="submenu.title_ico_class"></i>
                <span>{{submenu.name}}</span>
              </template>
              <el-menu-item
                v-for="menuitem in submenu.menuitems"
                :key="menuitem.path"
                :index="menuitem.path"
              >{{menuitem.name}}</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main class="home-main">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      menu: {
        default_openeds: ["0", "1", "2"],
        submenus: [
          {
            title_ico_class: "el-icon-s-order",
            name: "待办事项",
            menuitems: [
              {
                name: "我的一天",
                path: "/time/todayTask"
              },
              {
                name: "所有任务",
                path: "/time/allTask"
              }
            ]
          },
          {
            title_ico_class: "el-icon-s-finance",
            name: "实时记账",
            menuitems: [
              {
                name: "资金流动",
                path: "/unfinished/1"
              },
              {
                name: "账户资产",
                path: "/unfinished/2"
              },
              {
                name: "统计报表",
                path: "/unfinished/3"
              }
            ]
          },
          {
            title_ico_class: "el-icon-notebook-1",
            name: "博客日记",
            menuitems: [
              {
                name: "博客列表",
                path: "/unfinished/4"
              },
              {
                name: "日记列表",
                path: "/unfinished/5"
              }
            ]
          }
        ]
      }
    };
  },
  created: function() {
    //页面首次加载或刷新时,跳转到‘我的一天’
    if (!window.location.href.match("/time/todayTask")) {
      this.$router.push("/time/todayTask");
    }
  }
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
}

.home-container {
  position: absolute;
  width: 100%;
  height: 100%;
  background: whitesmoke;
}

.home-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: black;
  text-align: center;
  color: white;
  font-size: 20px;
}

.home-aside {
  background-color: white;
}

.home-main {
  margin: 8px;
  padding: 8px;
  background: white;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
</style>