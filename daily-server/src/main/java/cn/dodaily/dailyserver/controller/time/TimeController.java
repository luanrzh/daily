package cn.dodaily.dailyserver.controller.time;

import cn.dodaily.dailyserver.bean.time.Task;
import cn.dodaily.dailyserver.controller.RS;
import cn.dodaily.dailyserver.service.time.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TimeController {
    @Autowired
    private TimeService timeService;

    @ResponseBody
    @PostMapping("/time/findAll")
    public RS findAllTask(){
        return timeService.findAllTask();
    }

    @PostMapping("/time/add")
    public String addTask(Task task){
        timeService.addTask(task);
        return "增加代办事项成功";
    }
}
