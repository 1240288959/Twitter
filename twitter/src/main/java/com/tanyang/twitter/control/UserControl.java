package com.tanyang.twitter.control;

import com.tanyang.twitter.dao.UserDao;
import com.tanyang.twitter.service.UserServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class UserControl {
    private static final Logger logger=LoggerFactory.getLogger(UserControl.class);
    @Autowired
    private UserServiceimpl userServiceimpl;
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public boolean login(String email,String password){
        logger.debug("UserControl层:login方法:传入参数:email"+email+" password:"+password);
        System.out.println("UserControl层:login方法:传入参数:email"+email+" password:"+password);
        return userServiceimpl.login(email,password);
    }
}
