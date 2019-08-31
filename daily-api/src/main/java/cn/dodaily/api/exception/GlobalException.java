package cn.dodaily.api.exception;

import cn.dodaily.api.exception.bean.ErrorResultEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public
class GlobalException extends Exception {
    private int code;

    public GlobalException(String msg) {
        super(msg);
    }

    public GlobalException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public GlobalException(ErrorResultEnum errorResultEnum) {
        super(errorResultEnum.getMsg());
        this.code = errorResultEnum.getCode();
    }
}
