package cn.dodaily.api.service.time;

import cn.dodaily.api.bean.time.Task;
import cn.dodaily.api.bean.time.TaskStep;
import cn.dodaily.api.exception.DatabaseException;
import cn.dodaily.api.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeServiceTest {
    @Autowired
    private TimeService service;

    @Test
    public void getTaskList() {
        Object rs = service.getTaskList();
    }

    @Test
    public void addTask() {
        Task addTask = new Task(null, null, "task", new Date(), null, null);
        Object rs = service.addTask(addTask);
    }

    @Test
    public void updateTask() throws NotFoundException {
        Task updateTask = new Task(3, null, "update task", null, null, null);
        Object rs = service.updateTask(updateTask);
    }

    @Test
    public void deleteTask() throws NotFoundException {
        Task deleteTask = new Task(3, null, null, null, null, null);
        service.deleteTask(deleteTask);
    }

    @Test
    public void addTaskStep() throws DatabaseException {
        TaskStep taskStep = new TaskStep(null, 3, null, "子任务1");
        Object rs = service.addTaskStep(taskStep);
    }

    @Test
    public void updateTaskStep() throws NotFoundException {
        TaskStep taskStep = new TaskStep(21, 3, 1, "子任务3");
        Object rs = service.updateTaskStep(taskStep);
    }

    @Test
    public void deleteTaskStep() throws NotFoundException {
        TaskStep taskStep = new TaskStep(21, null, null, null);
        service.deleteTaskStep(taskStep);
    }
}