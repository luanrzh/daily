package cn.dodaily.api.exception.impl;

import cn.dodaily.api.exception.GlobalException;
import cn.dodaily.api.exception.bean.ErrorResultEnum;

public class UnauthorizedException extends GlobalException {
    public UnauthorizedException(ErrorResultEnum errorResultEnum) {
        super(errorResultEnum);
    }
}
