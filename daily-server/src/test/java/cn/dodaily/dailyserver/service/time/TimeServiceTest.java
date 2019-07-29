package cn.dodaily.dailyserver.service.time;

import cn.dodaily.dailyserver.bean.time.Task;
import cn.dodaily.dailyserver.exception.NotFoundException;
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
        Task updateTask = new Task(1, null, "update task", null, null, null);
        Object rs = service.updateTask(updateTask);
    }

    @Test
    public void deleteTask() throws NotFoundException {
        Task deleteTask = new Task(3, null, null, null, null, null);
        service.deleteTask(deleteTask);
    }
}