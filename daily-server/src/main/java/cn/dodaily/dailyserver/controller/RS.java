package cn.dodaily.dailyserver.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RS {
    private boolean isSuccess;
    private String errorCode;
    private String errorMsg;
}
