package cn.dodaily.api.bean.time;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {
    //id
    private Integer id;
    //用户id
    private Integer userId;
    //状态（0:已创建 1:已完成）
    private Integer status;
    //内容
    private String content;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;
    //截至时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date deadlineTime;
    //任务步骤列表
    private List<TaskStep> taskSteps;
}
