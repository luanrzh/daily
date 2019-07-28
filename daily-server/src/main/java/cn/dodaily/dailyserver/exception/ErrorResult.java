package cn.dodaily.dailyserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class ErrorResult {
    private Integer code;
    private String msg;
}
