package cn.dodaily.api.controller.time;

import cn.dodaily.api.bean.time.Task;
import cn.dodaily.api.bean.time.TaskStep;
import cn.dodaily.api.exception.impl.DatabaseException;
import cn.dodaily.api.exception.impl.NotFoundException;
import cn.dodaily.api.service.time.TimeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/time")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @ApiOperation(value = "获取代办事项列表")
    @GetMapping("/tasks/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskList() {
        return timeService.getTaskList();
    }

    @ApiOperation(value = "获取今日代办事项列表")
    @GetMapping("/tasks/today")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTodayTaskList() {
        return timeService.getTodayTaskList();
    }

    @ApiOperation(value = "增加代办事项")
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(Task task) {
        return timeService.addTask(task);
    }

    @ApiOperation(value = "修改代办事项")
    @PutMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task updateTask(Task task) throws NotFoundException {
        return timeService.updateTask(task);
    }

    @ApiOperation(value = "删除代办事项")
    @DeleteMapping("/tasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(Task task) throws NotFoundException {
        timeService.deleteTask(task);
    }

    @ApiOperation(value = "增加任务步骤")
    @PostMapping("/tasks/steps")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskStep addTaskStep(TaskStep taskStep) throws DatabaseException {
        return timeService.addTaskStep(taskStep);
    }

    @ApiOperation(value = "修改任务步骤")
    @PutMapping("/tasks/steps")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskStep updateTaskStep(TaskStep taskStep) throws NotFoundException {
        return timeService.updateTaskStep(taskStep);
    }

    @ApiOperation(value = "删除任务步骤")
    @DeleteMapping("/tasks/steps")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskStep(TaskStep taskStep) throws NotFoundException {
        timeService.deleteTaskStep(taskStep);
    }
}
