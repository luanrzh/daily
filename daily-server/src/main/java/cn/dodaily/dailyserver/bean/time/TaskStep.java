package cn.dodaily.dailyserver.bean.time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskStep {
    //id
    private int id;
    //任务id
    private int taskId;
    //状态（0:已创建 1:已完成）
    private int status;
    //内容
    private String content;
}
