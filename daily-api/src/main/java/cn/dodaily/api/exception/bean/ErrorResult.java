package cn.dodaily.api.exception.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResult {
    private Integer code;
    private String msg;
}
