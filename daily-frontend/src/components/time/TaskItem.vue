<template>
  <el-card class="box-card">
    <div slot="header">
      <span>{{task.content}}</span>
      <el-tooltip class="item" effect="light" content="删除任务" placement="top">
        <el-button @click="deleteTask()" icon="el-icon-delete" class="box-card-title-button icon-size-18" circle ></el-button>
      </el-tooltip>
      <el-popover placement="bottom" v-model="addStepVisible">
        <el-form :inline="true">
          <el-form-item>
            <el-input placeholder="请输入任务步骤" v-model="addStepContent"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-check" circle  @click="addStep()"></el-button>
          </el-form-item>
        </el-form>
        <el-button
          slot="reference"
          icon="el-icon-plus"
          class="box-card-title-button icon-size-18"
          circle
        ></el-button>
      </el-popover>
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
export default {
  props: ["task"],
  data: function() {
    return {
      addStepVisible: false,
      addStepContent: ""
    };
  },
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
    deleteTask: function(){
      this.$emit('delete-task',this.task.id);
    },
    addStep: function() {
      var step = {};
      step.taskId = this.task.id;
      step.content = this.addStepContent;
      this.$emit('add-step',step);
      this.addStepVisible = false;
    },
    finishStep: function(step) {
      this.$message("完成步骤" + step.content);
    },
    deleteStep: function(step) {
      this.$message("删除步骤" + step.content);
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