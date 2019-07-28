package cn.dodaily.dailyserver.exception;

public class NotFoundException extends GlobalException {

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(int code, String msg) {
        super(code, msg);
    }

    public NotFoundException(ErrorResultEnum errorResultEnum) {
        super(errorResultEnum.getCode(), errorResultEnum.getMsg());
    }
}
