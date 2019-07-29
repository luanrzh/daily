package cn.dodaily.dailyserver.service.time;

import cn.dodaily.dailyserver.bean.time.Task;
import cn.dodaily.dailyserver.bean.time.TaskStep;
import cn.dodaily.dailyserver.exception.DatabaseException;
import cn.dodaily.dailyserver.exception.ErrorResultEnum;
import cn.dodaily.dailyserver.exception.NotFoundException;
import cn.dodaily.dailyserver.mapper.time.TaskMapper;
import cn.dodaily.dailyserver.mapper.time.TaskStepMapper;
import cn.dodaily.dailyserver.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static cn.dodaily.dailyserver.utils.UpdateUtil.fillNullToNew;

@Service
public class TimeService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskStepMapper taskStepMapper;

    /**
     * 查询所有任务
     *
     * @return 任务集合
     */
    public List<Task> getTaskList() {
        return taskMapper.selectAll();
    }

    /**
     * 增加任务
     *
     * @param task 待增加的任务对象
     * @return 增加过后的任务对象
     */
    public Task addTask(Task task) {
        task.setCreateTime(new Date());
        taskMapper.insert(task);//task中的id会自动更新
        task.setStatus(Constant.STATUS_TASK_CREATED);
        return task;
    }

    /**
     * 更新任务
     *
     * @param task 待更新的任务对象
     * @return 更新过后的任务对象
     * @throws NotFoundException 资源未找到异常(试图更新不存在的任务)
     */
    public Task updateTask(Task task) throws NotFoundException {
        Task oldTask = taskMapper.select(task.getId());
        if (oldTask != null) {
            fillNullToNew(task, oldTask);
            taskMapper.update(task);
        } else {
            throw new NotFoundException(ErrorResultEnum.NoFound);
        }
        return task;
    }

    /**
     * 删除任务
     *
     * @param task 待删除的任务对象
     * @throws NotFoundException 资源未找到异常（试图删除不存在的任务）
     */
    public void deleteTask(Task task) throws NotFoundException {
        int matchedLine = taskMapper.delete(task.getId());
        if (matchedLine != 1) {
            throw new NotFoundException(ErrorResultEnum.NoFound);
        }
    }

    /**
     * 增加任务步骤
     *
     * @param taskStep 待增加的任务步骤对象
     * @return 增加过后的任务步骤对象
     * @throws DatabaseException 数据库操作异常(外键约束：任务id不存在时不允许插入)
     */
    public TaskStep addTaskStep(TaskStep taskStep) throws DatabaseException {
        try {
            taskStepMapper.insert(taskStep);
            taskStep.setStatus(Constant.STATUS_TASK_CREATED);
        } catch (Exception e) {
            throw new DatabaseException(ErrorResultEnum.DatabaseOperateError);
        }
        return taskStep;
    }

    /**
     * 更新任务步骤
     *
     * @param taskStep 待更新的任务步骤对象
     * @return 更新过后的任务步骤对象
     * @throws NotFoundException 资源未找到异常（试图更新不存在的任务步骤）
     */
    public TaskStep updateTaskStep(TaskStep taskStep) throws NotFoundException {
        TaskStep oldTaskStep = taskStepMapper.select(taskStep.getId());
        if (oldTaskStep != null) {
            fillNullToNew(taskStep, oldTaskStep);
            taskStepMapper.update(taskStep);
        } else {
            throw new NotFoundException(ErrorResultEnum.NoFound);
        }
        return taskStep;
    }

    /**
     * 删除任务步骤
     *
     * @param taskStep 待删除的任务步骤对象
     * @throws NotFoundException 资源未找到异常（试图删除不存在的任务步骤）
     */
    public void deleteTaskStep(TaskStep taskStep) throws NotFoundException {
        int matchedLine = taskStepMapper.delete(taskStep.getId());
        if (matchedLine != 1) {
            throw new NotFoundException(ErrorResultEnum.NoFound);
        }
    }

}
