package com.tanyang.twitter.control;

import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.EmailService;
import com.tanyang.twitter.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EmailControl {

    @Autowired
    private EmailService emailServiceImpl;

    @RequestMapping("/sendMail")
    public void sendMail(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        emailServiceImpl.sendSimpleMail(user.getEmail());
    }

}
