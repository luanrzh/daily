package cn.dodaily.dailyserver.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class GlobalException extends Exception {
    private int code;

    GlobalException(String msg) {
        super(msg);
    }

    GlobalException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
