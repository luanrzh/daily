package cn.dodaily.dailyserver.exception;

import lombok.Getter;

@Getter
public enum ErrorResultEnum {
    TaskNoFound(40401, "数据库中没有此任务的记录");
    private Integer code;
    private String msg;

    ErrorResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
