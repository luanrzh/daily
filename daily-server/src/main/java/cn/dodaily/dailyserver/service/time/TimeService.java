package cn.dodaily.dailyserver.service.time;

import cn.dodaily.dailyserver.bean.time.Task;
import cn.dodaily.dailyserver.controller.RS;
import cn.dodaily.dailyserver.controller.time.rs.FindAllTaskRS;
import cn.dodaily.dailyserver.mapper.time.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {
    @Autowired
    private TaskMapper taskMapper;

    public FindAllTaskRS findAllTask() {
        FindAllTaskRS rs = new FindAllTaskRS();
        List<Task> tasks = taskMapper.selectAll();
        rs.setSuccess(true);
        rs.setTasks(tasks);
        return rs;
    }

    public void addTask(Task task){

    }
}
