package com.tanyang.twitter.control;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Api(value = "发送异常",tags={"异常接受测试"})
@RestController
@RequestMapping("/advices")
public class AdviceControl {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        throw new RuntimeException("advices——exception1");
    }

    @CrossOrigin(origins = "http://localhost:8088") //允许来自8088端口的访问
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
        System.out.println(1/0);
        return null;
    }

/*    @ExceptionHandler(Exception.class)
    public Map<String, Object> defaultExceptionHandler(Exception e){
        Map<String, Object> map = new HashMap<>();
        map.put("code",500);
        map.put("msg",e.getMessage());
        return map;
    }*/
}
