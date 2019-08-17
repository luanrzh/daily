<template>
  <div v-loading="loading">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>代办事项</el-breadcrumb-item>
      <el-breadcrumb-item>我的一天</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider></el-divider>
    <TaskItem v-for="task in tasks" :key="task.id" :task="task"></TaskItem>
  </div>
</template>

<script>
import { getTodayTaskList } from "@/api/api";
import TaskItem from "./TaskItem";
export default {
  name: "TodayTask",
  data: function() {
    return {
      tasks: [],
      loading: true
    };
  },
  components: { TaskItem },
  created() {
    getTodayTaskList().then(response => {
      this.tasks = response.data;
      this.loading = false;
    });
  }
};
</script>
