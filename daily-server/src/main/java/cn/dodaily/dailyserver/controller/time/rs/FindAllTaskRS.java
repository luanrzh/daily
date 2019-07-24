package cn.dodaily.dailyserver.controller.time.rs;

import cn.dodaily.dailyserver.bean.time.Task;
import cn.dodaily.dailyserver.controller.RS;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindAllTaskRS extends RS {
    private List<Task> tasks;
}
