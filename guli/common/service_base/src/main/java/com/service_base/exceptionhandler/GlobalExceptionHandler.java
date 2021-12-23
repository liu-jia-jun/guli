package com.service_base.exceptionhandler;

import com.commonutils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 刘佳俊
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 所有异常
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("执行了全局异常处理");
    }
    // 特定异常
    @ExceptionHandler(NullPointerException.class)
    public Result error(NullPointerException e){
        e.printStackTrace();
        return Result.error().message("空指针了兄弟");
    }
    // 特定异常
    @ExceptionHandler(GuliException.class)
    public Result error(GuliException e){
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }

}
