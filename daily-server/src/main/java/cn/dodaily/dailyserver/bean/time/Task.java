package cn.dodaily.dailyserver.bean.time;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class Task implements Serializable {
    private String content;
    private Date createTime;
    private String status;
}
