<template>
  <el-card class="box-card">
    <div slot="header">
      <span>{{task.content}}</span>
      <el-tooltip class="item" effect="light" content="删除任务" placement="top">
        <el-button
          icon="el-icon-delete"
          class="box-card-title-button icon-size-18"
          circle
          @click="doDeleteTask(task.id)"
        ></el-button>
      </el-tooltip>
      <el-tooltip class="item" effect="light" content="增加步骤" placement="top">
        <el-button
          icon="el-icon-plus"
          class="box-card-title-button icon-size-18"
          circle
          @click="addStep()"
        ></el-button>
      </el-tooltip>
    </div>
    <div v-for="step in task.taskSteps" :key="step.id" class="taskStep">
      <el-row>
        <el-col :span="2">
          <i :class="getIconStatusClass(step.status)" @click="finishStep(step)"></i>
        </el-col>
        <el-col :span="20">
          <span :class="getTextStatusClass(step.status)">{{step.content }}</span>
        </el-col>
        <el-col :span="2">
          <i class="el-icon-close icon-size-18" @click="deleteStep(step)"></i>
        </el-col>
      </el-row>
    </div>
    <el-divider>{{formatedDeadlineTime}}</el-divider>
  </el-card>
</template>

<script>
import { deleteTask } from "@/api/api";
export default {
  props: ["task"],
  computed: {
    formatedDeadlineTime: function() {
      var formatedString;
      var deadlineTimeStr = this.task.deadlineTime;
      if (deadlineTimeStr != null) {
        var nowTime = new Date();
        var deadlineTime = new Date(deadlineTimeStr);
        var timeDiff = deadlineTime - nowTime;
        timeDiff = timeDiff / 1000 / 60 / 60 / 24;
        if (timeDiff < 0) {
          formatedString = "已超期" + (-Math.ceil(timeDiff) + 1) + "天";
        } else if (timeDiff < 1) {
          formatedString = "预计今天完成";
        } else if (timeDiff < 2) {
          formatedString = "预计明天完成";
        } else {
          formatedString = "预计" + Math.ceil(timeDiff) + "天后完成";
        }
      }
      return formatedString;
    }
  },
  methods: {
    getIconStatusClass: function(status) {
      return {
        "el-icon-circle-check": status == 0,
        "el-icon-success": status == 1,
        "icon-size-18": true
      };
    },
    getTextStatusClass: function(status) {
      return {
        "finished-status": status == 1
      };
    },
    finishStep: function(step) {
      this.$message("完成步骤" + step.content);
    },
    deleteStep: function(step) {
      this.$message("删除步骤" + step.content);
    },
    addStep: function() {
      this.$message("增加一个步骤");
    },
    doDeleteTask: function(id) {
      var task = {};
      task.id = id;
      var _this = this;
      deleteTask(task).then(response => {
        this.$message("任务删除成功");
        //todo 删除tasks中的指定元素
        //需要在父级组件中执行数组删除操作，因为当前组件没有tasks对象
        //明天重构删除操作
        for (var i = 0; i < this.tasks.length; i++) {
          if (id == this.tasks[i]) {
            this.tasks.splice(i, 1);
          }
        }
      });
    }
  }
};
</script>

<style>
.box-card {
  width: 350px;
  float: left;
  margin-right: 10px;
  margin-bottom: 10px;
}
.box-card-title-button {
  float: right;
  margin: -10px 0 -10px 10px;
}
.taskStep {
  font-size: 14px;
  margin-bottom: 18px;
}
.finished-status {
  color: grey;
  text-decoration: line-through;
}
.icon-size-18 {
  font-size: 18px;
}
</style>