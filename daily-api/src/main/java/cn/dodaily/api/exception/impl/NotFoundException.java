package cn.dodaily.api.exception.impl;

import cn.dodaily.api.exception.GlobalException;
import cn.dodaily.api.exception.bean.ErrorResultEnum;

public class NotFoundException extends GlobalException {
    public NotFoundException(ErrorResultEnum errorResultEnum) {
        super(errorResultEnum);
    }
}
