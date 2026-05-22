package com.laoz.trading.project.common.exception;

import com.laoz.trading.project.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception e) {
        log.error("System exception: {}", e.getMessage(), e);
        return Response.error("系统繁忙，请稍后重试");
    }

}
