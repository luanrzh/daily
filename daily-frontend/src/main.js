import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  if (to.name == "login") {
    next();
    return;
  }
  //用户登录验证
  var user = store.state.user;
  if (user) {
    next();
  } else {
    next({ name: "login" });
  }
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
