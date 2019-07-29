package cn.dodaily.dailyserver.exception;

import lombok.Getter;

@Getter
public enum ErrorResultEnum {
    NoFound(40401, "数据库中没有此任务的记录"),
    DatabaseOperateError(40402,"数据库操作失败");
    private Integer code;
    private String msg;

    ErrorResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
