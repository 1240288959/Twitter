package com.tanyang.twitter.handler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> defaultExceptionHandler(Exception e){
        Map<String, Object> map = new HashMap<>();
        map.put("code",500);
        map.put("msg",e.getMessage());
        return map;
    }
}
