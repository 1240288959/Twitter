package com.tanyang.twitter.control;

import com.tanyang.twitter.dao.UserDao;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.EmailService;
import com.tanyang.twitter.service.UserServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

@Controller

public class UserControl {
    private static final Logger logger=LoggerFactory.getLogger(UserControl.class);
    @Autowired
    private UserServiceimpl userServiceimpl;
    @Autowired
    private EmailService emailService;

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public boolean login(String email, String password, HttpSession session){
        logger.debug("UserControl层:login方法:传入参数:email"+email+" password:"+password);
        System.out.println("UserControl层:login方法:传入参数:email"+email+" password:"+password);
        return userServiceimpl.login(email,password,session);
    }

    @RequestMapping("/toregister")
    public String toregister(){
        return "register";
    }

    @RequestMapping("/register")
    @ResponseBody
    public boolean register(String name, String password,String realname,String gender, String email, String mobile, Date birthday){
        logger.info(name+" "+password+" "+realname+" "+gender+" "+email+" "+mobile+" "+birthday);
        boolean flag= userServiceimpl.register(name,password,realname,gender,email,mobile,birthday);
        if(flag==true){
            emailService.sendSimpleMail(email);
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("/vertify")
    public String vertify(String email){
        logger.info(email);
        userServiceimpl.vertify(email);
        return "vertify";
    }

    @RequestMapping("/setImage")
    @ResponseBody
    public boolean setImage(@RequestParam("image") MultipartFile image,HttpSession session){
        String img_name=null;
        String file_path="E:\\Twitter\\twitter\\src\\main\\resources\\static\\img\\";
        logger.info("文件路径为： "+file_path);
        if(image==null){
            logger.info("文件为空");
        }
        img_name=image.getOriginalFilename();
        logger.info("文件名为："+img_name);
        File file=new File(file_path+img_name);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try{
            image.transferTo(file);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        User user=(User)session.getAttribute("user");
        userServiceimpl.setImage(img_name,user.getId());
        user.setImage(img_name);
        session.setAttribute("user",user);
        return true;
    }

}
