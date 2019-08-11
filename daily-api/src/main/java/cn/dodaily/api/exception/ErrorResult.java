package cn.dodaily.api.exception;

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