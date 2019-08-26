<template>
  <div v-loading="loading">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>代办事项</el-breadcrumb-item>
      <el-breadcrumb-item>我的一天</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider></el-divider>
    <TaskItem v-for="task in tasks" :key="task.id" :task="task"></TaskItem>
    <el-button type="primary" circle icon="el-icon-plus" id="add-task-button" @click="addTask"></el-button>
    <el-drawer title="增加任务" :visible.sync="isDrawerOpen" :before-close="handleDrawerClose">
      <el-form :model="addTaskForm" label-width="80px">
        <el-form-item label="任务内容">
          <el-input v-model="addTaskForm.content" placeholder="输入任务内容"></el-input>
        </el-form-item>
        <el-form-item label="完成时间">
          <el-date-picker
            v-model="addTaskForm.deadlineTime"
            type="datetime"
            placeholder="选择日期时间"
            :picker-options="pickerOptions"
            suffix-icon="el-icon-date"
          ></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="doAddTask">确认</el-button>
          <el-button @click="resetAddTask">重置</el-button>
          <el-button @click="cancleAddTask">取消</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
import { getTodayTaskList } from "@/api/api";
import { addTask } from "@/api/api";
import TaskItem from "./TaskItem";
export default {
  name: "TodayTask",
  data: function() {
    return {
      tasks: [],
      loading: true,
      isDrawerOpen: false,
      addTaskForm: {
        content: "",
        deadlineTime: ""
      },
      pickerOptions: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "明天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周后",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      }
    };
  },
  components: { TaskItem },
  methods: {
    addTask() {
      this.isDrawerOpen = true;
    },
    handleDrawerClose() {
      this.isDrawerOpen = false;
    },
    doAddTask() {
      var task = {};
      task.content = this.addTaskForm.content;
      var time = this.addTaskForm.deadlineTime;
      var year = time.getFullYear();
      var month = time.getMonth() + 1;
      var date = time.getDate();
      var hour = time.getHours();
      var minute = time.getMinutes();
      var second = time.getSeconds();
      task.deadlineTime = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" +second;
      addTask(task).then(response => {
        tasks.put(task);
      });
    },
    resetAddTask() {
      this.addTaskForm.content = "";
      this.addTaskForm.deadlineTime = "";
    },
    cancleAddTask() {
      this.isDrawerOpen = false;
    }
  },
  created() {
    getTodayTaskList().then(response => {
      this.tasks = response.data;
      this.loading = false;
    });
  }
};
</script>
<style>
#add-task-button {
  font-size: 24px;
}
</style>