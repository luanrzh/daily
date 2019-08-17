<template>
  <el-card class="box-card">
    <div slot="header">
      <span>{{task.content}}</span>
      <el-tooltip class="item" effect="light" content="删除任务" placement="top">
        <el-button
          icon="el-icon-delete"
          class="box-card-title-button icon-size-18"
          circle
          @click="deleteTask()"
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
    <el-divider>创建于{{formatedCreatedDate}}</el-divider>
  </el-card>
</template>

<script>
export default {
  props: ["task"],
  computed: {
    formatedCreatedDate: function() {
      return this.task.createTime.substring(11);
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
    deleteTask: function() {
      this.$message("删除此任务");
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