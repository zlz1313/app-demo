package com.opensource.demo.common.exception;

import com.opensource.demo.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class WebExceptionHandler {

    @ExceptionHandler
    public Result businessException(BusinessException e) {
        return Result.failed(e);
    }

    @ExceptionHandler
    public Result unknownException(Exception e) {
        log.error("ERROR: {}", e.getMessage());
        return Result.failed(null, e.getMessage());
    }
}
