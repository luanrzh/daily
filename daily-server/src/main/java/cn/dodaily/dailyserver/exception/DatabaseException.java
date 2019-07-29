package cn.dodaily.dailyserver.exception;

public class DatabaseException extends GlobalException {
    public DatabaseException(ErrorResultEnum errorResultEnum) {
        super(errorResultEnum.getCode(), errorResultEnum.getMsg());
    }
}
