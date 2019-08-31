package cn.dodaily.api.exception;

import cn.dodaily.api.exception.bean.ErrorResult;
import cn.dodaily.api.exception.impl.DatabaseException;
import cn.dodaily.api.exception.impl.NotFoundException;
import cn.dodaily.api.exception.impl.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handleResourceNotFoundException(NotFoundException e) {
        return new ErrorResult(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = DatabaseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handleDatabaseOperationException(DatabaseException e) {
        return new ErrorResult(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResult handleUnauthorizedException(UnauthorizedException e) {
        return new ErrorResult(e.getCode(), e.getMessage());
    }

}
