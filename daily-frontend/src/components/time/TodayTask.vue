<template>
  <div v-loading="loading">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>代办事项</el-breadcrumb-item>
      <el-breadcrumb-item>我的一天</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider></el-divider>
    <TaskItem
      v-for="task in tasks"
      :key="task.id"
      :task="task"
      @delete-task="doDeleteTask"
      @update-task="doUpdateTask"
      @add-step="doAddStep"
      @delete-step="doDeleteStep"
    ></TaskItem>
    <el-button
      type="primary"
      circle
      icon="el-icon-plus"
      id="add-task-button"
      @click="handleAddTaskClick"
    ></el-button>
    <el-dialog title="增加任务" :visible.sync="isDialogOpen" :before-close="handleDialogClose">
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
            value-format="yyyy-MM-dd HH:mm:ss"
          ></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="doAddTask">确认</el-button>
          <el-button @click="resetAddTask">重置</el-button>
          <el-button @click="cancleAddTask">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getTodayTaskList } from "@/api/api";
import { addTask } from "@/api/api";
import { deleteTask } from "@/api/api";
import { addStep } from "@/api/api";
import { deleteStep } from "@/api/api";

import TaskItem from "./TaskItem";
import store from "@/store";

export default {
  name: "TodayTask",
  data: function() {
    return {
      tasks: [],
      loading: true,
      isDialogOpen: false,
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
    handleAddTaskClick() {
      this.isDialogOpen = true;
    },
    handleDialogClose() {
      this.isDialogOpen = false;
    },
    doAddTask() {
      var task = {};
      task.content = this.addTaskForm.content;
      task.deadlineTime = this.addTaskForm.deadlineTime;
      task.userId = store.state.user.id;
      addTask(task).then(response => {
        this.isDialogOpen = false;
        this.tasks.push(response.data);
      });
    },
    resetAddTask() {
      this.addTaskForm.content = "";
      this.addTaskForm.deadlineTime = "";
    },
    cancleAddTask() {
      this.isDialogOpen = false;
    },
    doDeleteTask(id) {
      var task = {};
      task.id = id;
      deleteTask(task).then(response => {
        for (var i = 0; i < this.tasks.length; i++) {
          if (id == this.tasks[i].id) {
            this.tasks.splice(i, 1);
          }
        }
      });
    },
    doUpdateTask() {
      this.$message("doUpdateTask");
    },
    doAddStep(step) {
      addStep(step).then(response => {
        for (var i = 0; i < this.tasks.length; i++) {
          if (step.taskId == this.tasks[i].id) {
            if (!this.tasks[i].taskSteps) {
              this.tasks[i].taskSteps = [];
            }
            this.tasks[i].taskSteps.push(response.data);
          }
        }
      });
    },
    doDeleteStep(id) {
      var step = {};
      step.id = id;
      deleteStep(step).then(response => {
        var tasks = this.tasks;
        for (var i = 0; i < tasks.length; i++) {
          for (var j = 0; j < tasks[i].taskSteps.length; j++) {
            if (step.id == tasks[i].taskSteps[j].id) {
              this.tasks[i].taskSteps.splice(j, 1);
            }
          }
        }
      });
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