package cn.dodaily.api.controller.time;

import cn.dodaily.api.bean.time.Task;
import cn.dodaily.api.bean.time.TaskStep;
import cn.dodaily.api.exception.bean.ErrorResultEnum;
import cn.dodaily.api.exception.impl.DatabaseException;
import cn.dodaily.api.exception.impl.NotFoundException;
import cn.dodaily.api.exception.impl.UnauthorizedException;
import cn.dodaily.api.service.time.TimeService;
import cn.dodaily.api.token.JwtHelper;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/time")
public class TimeController {
    @Autowired
    private TimeService timeService;
    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "获取代办事项列表")
    @GetMapping("/tasks/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskList() throws UnauthorizedException {
        int userId = getUserId();
        return timeService.getTaskList(userId);
    }

    @ApiOperation(value = "获取今日代办事项列表")
    @GetMapping("/tasks/today")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTodayTaskList() throws UnauthorizedException {
        int userId = getUserId();
        return timeService.getTodayTaskList(userId);
    }

    @ApiOperation(value = "增加代办事项")
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(Task task) throws UnauthorizedException {
        int userId = getUserId();
        task.setUserId(userId);
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

    /**
     * 验证Token合法性并从从JWS字符串中获取用户ID
     * @return 用户ID
     * @throws UnauthorizedException 未授权异常
     */
    private int getUserId() throws UnauthorizedException {
        String authorization = request.getHeader("Authorization");
        try{
            Claims claims = JwtHelper.parseJws(authorization);
            return (int) claims.get("userId");
        }catch (Exception e){
            throw new UnauthorizedException(ErrorResultEnum.Unauthorized);
        }
    }
}
