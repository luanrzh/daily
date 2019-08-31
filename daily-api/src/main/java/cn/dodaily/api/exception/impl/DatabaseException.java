package cn.dodaily.api.exception.impl;

import cn.dodaily.api.exception.GlobalException;
import cn.dodaily.api.exception.bean.ErrorResultEnum;

public class DatabaseException extends GlobalException {
    public DatabaseException(ErrorResultEnum errorResultEnum) {
        super(errorResultEnum);
    }
}
