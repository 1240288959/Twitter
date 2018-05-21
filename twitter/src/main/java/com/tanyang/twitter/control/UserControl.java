package com.tanyang.twitter.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanyang.twitter.pojo.*;
import com.tanyang.twitter.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller

public class UserControl {
    private static final Logger logger=LoggerFactory.getLogger(UserControl.class);
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @Autowired
    private AttentionServiceImpl attentionServiceimpl;
    @Autowired
    private TwitterServiceImpl twitterServiceImpl;
    @Autowired
    private PraiseServiceImpl praiseServiceImpl;
    @Value("${defaultImg}")
    private String defaultImg;

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public boolean login(String email, String password, HttpSession session){
      /*  logger.debug("UserControl层:login方法:传入参数:email"+email+" password:"+password);
        System.out.println("UserControl层:login方法:传入参数:email"+email+" password:"+password);*/
        return userServiceImpl.login(email,password,session);
    }

    @RequestMapping("/toregister")
    public String toregister(){
        return "register";
    }

    @RequestMapping("/register")
    @ResponseBody
    public boolean register(String name, String password,String realname,String gender, String email, String mobile, Date birthday){
        /*logger.info(name+" "+password+" "+realname+" "+gender+" "+email+" "+mobile+" "+birthday);*/
        boolean flag= userServiceImpl.register(name,password,realname,gender,email,mobile,birthday,defaultImg);
        if(flag==true){
            emailServiceImpl.sendSimpleMail(email);
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("/vertify")
    public String vertify(String email){
       /* logger.info(email);*/
        userServiceImpl.vertify(email);
        return "vertify";
    }

    @RequestMapping("/setImage")
    @ResponseBody
    public boolean setImage(@RequestParam("image") MultipartFile image,HttpSession session){
        String img_name=null;
        if(image==null){
            logger.info("文件为空");
            return false;
        }
        String suffix=image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
        img_name=image.getOriginalFilename().substring(0,image.getOriginalFilename().lastIndexOf('.'));
        logger.info("文件名为："+img_name+suffix);
        if(!".jpg".equalsIgnoreCase(suffix)&&!".png".equalsIgnoreCase(suffix)){
            return false;
        }

/*        File file=new File(file_path+img_name+suffix);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        while(file.exists()){
            img_name=new String(img_name+"_1");
            file=new File(new String(file_path+img_name+suffix));
        }
        try{
            image.transferTo(file);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }*/

        BASE64Encoder encoder=new BASE64Encoder();
        StringBuffer base64Str= new StringBuffer("data:image/"+suffix+";base64,");
        try {
            base64Str =base64Str.append(encoder.encode(image.getBytes())) ;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        User user=(User)session.getAttribute("user");
        userServiceImpl.setImage(base64Str.toString(),user.getId());
        user.setImage(base64Str.toString());
        session.setAttribute("user",user);
        return true;
    }

    @RequestMapping("/tosearched")
    public String searchuser(String name,Model model,HttpSession session){
        /*String attentId=((User)session.getAttribute("user")).getId();
        List<User> userlist=null;
        List<AttentedUser> attentedUserList=new ArrayList<>();
        try{
            userlist= userServiceImpl.searchUser(name,session);
            for(User user:userlist){
                boolean attented=attentionServiceimpl.getAttention(attentId,user.getId());
                AttentedUser attentedUser=new AttentedUser(user,attented);
                logger.info(attentedUser.toString());
                attentedUserList.add(attentedUser);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("list",attentedUserList);*/
        model.addAttribute("name",name);
        return "searched";
    }

    @RequestMapping("/getSearched")
    @ResponseBody
    public String searchuserPage(String name,int page,Model model,HttpSession session){
        ObjectMapper mapper=new ObjectMapper();
        String attentId=((User)session.getAttribute("user")).getId();
        List<User> userlist=null;
        List<AttentedUser> attentedUserList=new ArrayList<>();
        try{
            userlist= userServiceImpl.searchUserPage(name,page,session);
            for(User user:userlist){
                boolean attented=attentionServiceimpl.getAttention(attentId,user.getId());
                AttentedUser attentedUser=new AttentedUser(user,attented);
                logger.info(attentedUser.toString());
                attentedUserList.add(attentedUser);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String jsonStr="";
        try {
            jsonStr=mapper.writeValueAsString(attentedUserList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    @RequestMapping("/tootherspage")
    public String otherspage(String id,Model model,HttpSession session){
        /*logger.info("id:"+id);
        System.out.println(id);*/
        session.setAttribute("toOthersPageTime",new java.util.Date(System.currentTimeMillis()));
        AttentedUser attentedUser=new AttentedUser();
        User user=(User)session.getAttribute("user");
        User otheruser= userServiceImpl.findUser(id);
        /*logger.info("user:"+otheruser);*/
        boolean attented=attentionServiceimpl.getAttention(user.getId(),id);
        attentedUser.setUser(otheruser);
        attentedUser.setAttented(attented);
        model.addAttribute("attenteduser",attentedUser);
        /*List<Twitter> list= twitterServiceImpl.getTwitterByUserId(id);
        List<PraiseTwitter> praiseTwitterList=new ArrayList<PraiseTwitter>();
        for(Twitter twitter:list){
            Praise praise=praiseServiceImpl.getPraiseByUserAndTwitter(user.getId(),twitter.getId());
            PraiseTwitter praiseTwitter=new PraiseTwitter();
            praiseTwitter.setPraise(praise);
            praiseTwitter.setTwitter(twitter);
            praiseTwitterList.add(praiseTwitter);
        }
        model.addAttribute("list",praiseTwitterList);*/
        return "otherspage";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("user",null);
        return "login";
    }

    @RequestMapping("/tomyinform")
    public String toMyInform(){
        return "myinform";
    }

    @RequestMapping("/getcurrentuser")
    @ResponseBody
    public User getCurrentUser(HttpSession session){
        User user= (User) session.getAttribute("user");
        return user;
    }

    @RequestMapping("/updateuserinform")
    @ResponseBody
    public boolean updateUserInform(String name, String password,String realname,String gender, String email, String mobile, Date birthday,HttpSession session){
        User user= (User) session.getAttribute("user");
        user.setName(name);
        user.setPassword(password);
        user.setRealname(realname);
        user.setGender(gender);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setBirthday(birthday);
        boolean isSuccess=userServiceImpl.updateUserInform(user);
        return isSuccess;
    }
}
