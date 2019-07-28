package cn.dodaily.dailyserver.bean.time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {
    private Integer id;
    private String content;
    private Date createTime;
    private Integer status;
}
