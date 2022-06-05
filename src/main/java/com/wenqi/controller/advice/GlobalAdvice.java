package com.wenqi.controller.advice;

import com.wenqi.entity.BaseResponse;
import com.wenqi.entity.StatusEnum;
import com.wenqi.controller.exception.ClientException;
import com.wenqi.controller.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 *  捕获全局异常
 *
 * @author wuwenqi04
 * @classname：ControllerAdvice
 * @date 2022/01/14
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalAdvice {

    @ExceptionHandler(value = ClientException.class)
    public BaseResponse handleClientException(ClientException ex) {

        log.error("ClientException", ex);
        return BaseResponse.failure(StatusEnum.PARAM_ERROR.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = ServerException.class)
    public BaseResponse handleServerException(ServerException ex) {
        log.error("ServerException", ex);
        return BaseResponse.failure(StatusEnum.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage());
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorMsg = new ArrayList<>();
        if (ex.getBindingResult().hasErrors()) {
            for (ObjectError objectError: ex.getBindingResult().getAllErrors()) {
                errorMsg.add(objectError.getDefaultMessage());
            }
        }
        return BaseResponse.failure(StatusEnum.PARAM_ERROR.getCode(),  String.join(";", errorMsg));
    }

    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    public BaseResponse<String> BindExceptionHandler(BindException e) {
        List<String> errorMsg = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(item -> errorMsg.add(item.getDefaultMessage()));
        return BaseResponse.failure(StatusEnum.PARAM_ERROR.getCode(), String.join(";", errorMsg));
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public BaseResponse<String> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        List<String> errorMsg = new ArrayList<>();
        e.getConstraintViolations().forEach(item -> errorMsg.add(item.getMessage()));
        return BaseResponse.failure(StatusEnum.PARAM_ERROR.getCode(), String.join(";", errorMsg));
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse<String> handleException(Exception ex) {
        log.error("{}", ex.getClass(), ex);
        return BaseResponse.failure(StatusEnum.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage());
    }
}
