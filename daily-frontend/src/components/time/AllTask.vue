<template>
  <div v-loading="loading">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>代办事项</el-breadcrumb-item>
      <el-breadcrumb-item>所有任务</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider></el-divider>
    <el-table :data="tasks" border :row-class-name="tableRowClassName">
      <el-table-column prop="content" label="内容"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column prop="deadlineTime" label="截至时间"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getAllTaskList } from "@/api/api";
export default {
  name: "AllTask",
  data: function() {
    return {
      tasks: [],
      loading: true
    };
  },
  methods: {
    tableRowClassName({ rowIndex }) {
      var status = this.tasks[rowIndex].status;
      if (status === 0) {
        return "created-task";
      } else if (status === 1) {
        return "finished-task";
      }
      return "";
    }
  },
  created() {
    getAllTaskList().then(response => {
      this.tasks = response.data;
      this.loading = false;
    });
  }
};
</script>

<style>
.el-table .created-task {
  background: #fff;
}
.el-table .finished-task {
  background: #efe;
}
</style>
