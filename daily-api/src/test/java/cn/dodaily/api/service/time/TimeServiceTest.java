package cn.dodaily.api.service.time;

import cn.dodaily.api.bean.time.Task;
import cn.dodaily.api.bean.time.TaskStep;
import cn.dodaily.api.exception.impl.DatabaseException;
import cn.dodaily.api.exception.impl.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeServiceTest {
    @Autowired
    private TimeService service;

    @Test
    public void getTaskList() {
        Object rs = service.getTaskList(1);
    }

    @Test
    public void getTodayTaskList() {
        Object rs = service.getTodayTaskList(1);
    }

    @Test
    public void addTask() {
        Task addTask = new Task(null, 1, null, "task", new Date(), null, null);
//        Object rs = service.addTask(addTask);
    }

    @Test
    public void updateTask() throws NotFoundException {
        TaskStep taskStep1 = new TaskStep(145,null,null,"update taskStep");
        TaskStep taskStep2 = new TaskStep(146,null,1,null);
        List<TaskStep> taskSteps = new ArrayList<>();
        taskSteps.add(taskStep1);
        taskSteps.add(taskStep2);
        Task updateTask = new Task(143, null, null, "update task", null, null, taskSteps);
//        Object rs = service.updateTask(updateTask);
    }

    @Test
    public void deleteTask() throws NotFoundException {
        Task deleteTask = new Task(3, 1, null, null, null, null, null);
//        service.deleteTask(deleteTask);
    }

    @Test
    public void addTaskStep() throws DatabaseException {
        TaskStep taskStep = new TaskStep(null, 3, null, "子任务1");
//        Object rs = service.addTaskStep(taskStep);
    }

    @Test
    public void updateTaskStep() throws NotFoundException {
        TaskStep taskStep = new TaskStep(21, 3, 1, "子任务3");
//        Object rs = service.updateTaskStep(taskStep);
    }

    @Test
    public void deleteTaskStep() throws NotFoundException {
        TaskStep taskStep = new TaskStep(21, null, null, null);
//        service.deleteTaskStep(taskStep);
    }
}