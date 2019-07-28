package cn.dodaily.dailyserver.service.time;

import cn.dodaily.dailyserver.bean.time.Task;
import cn.dodaily.dailyserver.exception.ErrorResultEnum;
import cn.dodaily.dailyserver.exception.NotFoundException;
import cn.dodaily.dailyserver.mapper.time.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static cn.dodaily.dailyserver.utils.UpdateUtil.fillNullToNew;

@Service
public class TimeService {
    @Autowired
    private TaskMapper taskMapper;

    public List<Task> getTaskList() {
        return taskMapper.selectAll();
    }

    public Task addTask(Task task) {
        task.setCreateTime(new Date());
        task.setStatus(0);
        taskMapper.insert(task);//task中的id会自动更新
        return task;
    }

    public Task updateTask(Task task) throws NotFoundException {
        Task oldTask = taskMapper.select(task.getId());
        if (oldTask != null) {
            fillNullToNew(task, oldTask);
            taskMapper.update(task);
        } else {
            throw new NotFoundException(ErrorResultEnum.TaskNoFound);
        }
        return task;
    }

    public void deleteTask(Task task) throws NotFoundException {
        int matchedLine = taskMapper.delete(task.getId());
        if (matchedLine != 1) {
            throw new NotFoundException(ErrorResultEnum.TaskNoFound);
        }
    }
}
