package cn.dodaily.api.bean.time;

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
    private Integer id;
    //任务id
    private Integer taskId;
    //状态（0:已创建 1:已完成）
    private Integer status;
    //内容
    private String content;
}
