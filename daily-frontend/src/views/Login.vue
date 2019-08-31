<template>
  <div>
    <el-form :model="loginForm" ref="loginForm" :rules="rules" v-loading="loading" class="login-container"
    >
      <h3 class="login-title">用户登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" placeholder="帐号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('loginForm')" class="login-button">登录</el-button>
        <el-button @click="goToRegister" class="login-button">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { login } from "@/api/api";
import store from "@/store";
import { constants } from 'crypto';

export default {
  data() {
    return {
      loginForm: {
        username: "daily",
        password: "daily"
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      loading: false
    };
  },
  methods: {
    submit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          var _this = this;
          this.loading = true;
          login(this.loginForm.username, this.loginForm.password).then(
            response => {
              _this.loading = false;
              if (response && response.status == 200) {
                var user = response.data;
                if (user.username) {
                  _this.$message.success("登录成功");
                  store.login(user,response.headers.authorization);
                  _this.$router.replace({ name: "home" });
                } else {
                  _this.$message.error("登录失败（用户名或密码错误）");
                }
              }
            }
          );
        } else {
          return false;
        }
      });
    },
    goToRegister() {}
  }
};
</script>

<style>
.login-container {
  margin: 160px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  border-radius: 15px;
  box-shadow: 0 0 25px #cac6c6;
}
.login-title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
.login-button {
  width: 170px;
}
</style>