<template>
  <div class="Login">
    <h1>登录页面</h1>
  </div>
</template>

<script>
import { login } from "@/api/api";

export default {
  created() {
    login("张三", "1").then(result => {
      console.log(result.data);
    });
  }
};
</script>