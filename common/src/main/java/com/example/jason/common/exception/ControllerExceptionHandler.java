package com.example.jason.common.exception;

import com.example.jason.common.restResult.RestResult;
import com.example.jason.common.restResult.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller异常处理->返回前端
 * 自定义异常处理来改变默认的客户端访问接口产生的异常信息
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 错误码
     */
    public static final String CODE = "code";

    /**
     * 消息
     */
    public static final String MESSAGE = "message";

    /**
     * 处理异常的类型
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    public void handleCustomException(Exception e, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        RestResult result = null;
        if (e instanceof CustomException){
            // 可设置不同的HTTP状态
            response.setStatus(200);
            CustomException ex = (CustomException) e;
            result = new RestResult(ex.getResultCode());
        }else if(e instanceof RuntimeException){
            response.setStatus(500);
            log.info(e.getMessage());
            result = ResultGenerator.genFailResult(e.getMessage());
        }else {
            response.setStatus(500);
            log.info(e.getMessage());
            result = new RestResult(500,"未知错误，请联系管理员");
        }
        try {
            // 重置response的输出流
            // response.reset();
            writer = response.getWriter();
            writer.print(result.toJson());
            writer.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        e.printStackTrace();
    }
}


