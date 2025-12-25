package com.example.devgroveback.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonParseError(HttpMessageNotReadableException ex, HttpServletRequest request) {
        log.error("====================[前端数据]====================");
        log.error("参数解析失败: {}", ex.getMessage());
        try {
            String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);
            log.error("原始请求体: {}", body);
        } catch (IOException e) {
            log.error("读取原始请求体失败: {}", e.getMessage());
        } finally {
            log.error("===============================================");
        }
        return ResponseEntity.badRequest().body("参数格式错误");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherError(Exception ex, HttpServletRequest request) {
        log.error("====================[接口异常]====================");
        log.error("类型: {}", ex.getClass().getSimpleName());
        log.error("信息: {}", ex.getMessage());
        log.error("===============================================");
        return ResponseEntity.status(500).body("服务器内部错误");
    }
}
