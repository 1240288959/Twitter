package com.tanyang.twitter.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanyang.twitter.pojo.*;
import com.tanyang.twitter.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TwitterControl {

    private static final Logger logger=LoggerFactory.getLogger(Twitter.class);
    @Autowired
    private TwitterServiceImpl twitterServiceImpl;
    @Autowired
    private PraiseServiceImpl praiseServiceImpl;
    @Autowired
    private MessageServiceImpl messageServiceImpl;
    @Autowired
    private AttentionServiceImpl attentionServiceImpl;
    @Autowired
    private TimageServiceImpl timageServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/tomain")
    public String getAttentionTwitter(Model model, HttpSession session){
        session.setAttribute("toMainTime",new Date(System.currentTimeMillis()));
        return "index";
    }

    @RequestMapping("/getMainTwitter")
    @ResponseBody
    public String getTwitterInMain(Integer page,HttpSession session){
        ObjectMapper mapper=new ObjectMapper();
        User user=(User)session.getAttribute("user");
        Date time= (Date) session.getAttribute("toMainTime");
        /*logger.info("id :"+user.getId());*/
        List<Twitter> list= twitterServiceImpl.getTwitterPageByAttention(user.getId(),time,page);
        List<PraiseTwitter> praiseTwitterList=new ArrayList<PraiseTwitter>();
        for(Twitter twitter:list){
            Praise praise=praiseServiceImpl.getPraiseByUserAndTwitter(user.getId(),twitter.getId());
            PraiseTwitter praiseTwitter=new PraiseTwitter();
            praiseTwitter.setPraise(praise);
            praiseTwitter.setTwitter(twitter);
            List<Timage> timageList=timageServiceImpl.getTimageByTwitter(twitter);
            praiseTwitter.setTimageList(timageList);
            praiseTwitterList.add(praiseTwitter);

        }
        /*logger.info("list:"+list);*/
        /*System.out.println(praiseTwitterList);*/
        String jsonStr;
        try {
            jsonStr=mapper.writeValueAsString(praiseTwitterList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonStr="";
        }
        return jsonStr;
    }

    @RequestMapping("/tomytwitter")
    public String toMyTwitter(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        session.setAttribute("toMyTwitterTime",new Date(System.currentTimeMillis()));
        List<Twitter> list= twitterServiceImpl.getTwitterByUserId(user.getId());
       /* model.addAttribute("list",list);
        logger.info("list:"+list);*/
        return "mytwitter";
    }

    @RequestMapping("/getMyTwitter")
    @ResponseBody
    public String getTwitterInMyTwitter(int page,HttpSession session){
        ObjectMapper mapper=new ObjectMapper();
        User user=(User)session.getAttribute("user");
        Date time= (Date) session.getAttribute("toMyTwitterTime");
        List<Twitter> list= twitterServiceImpl.getTwitterPageByUserId(user.getId(),time,page);
        List<PraiseTwitter> praiseTwitterList=new ArrayList<>();
        for(Twitter twitter:list){
            PraiseTwitter praiseTwitter=new PraiseTwitter();
            Praise praise=praiseServiceImpl.getPraiseByUserAndTwitter(user.getId(),twitter.getId());
            List<Timage> timageList=timageServiceImpl.getTimageByTwitter(twitter);
            praiseTwitter.setTwitter(twitter);
            praiseTwitter.setPraise(praise);
            praiseTwitter.setTimageList(timageList);
            praiseTwitterList.add(praiseTwitter);
        }
        String jsonStr;
        try {
            jsonStr=mapper.writeValueAsString(praiseTwitterList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonStr="";
        }
        return jsonStr;
    }

    @RequestMapping("/todeliverytwitter")
    public String todeliverytwitter(){
        return "deliverytwitter";
    }

    @RequestMapping("/deliverytwitter")
    @ResponseBody
    public boolean deliverytwitter(@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("imageArray[]") List<String> imageList,HttpSession session){
        logger.info("title:"+title+" content:"+content);
        logger.info("imageArray的长度为"+imageList.size());
        Twitter twitter=new Twitter();
        User user=(User)session.getAttribute("user");
        twitter.setUser(user);
        twitter.setTitle(title);
        twitter.setContent(content);
        boolean flag=twitterServiceImpl.deliveryTwitter(twitter);
        if(flag==true){
            //添加图片
            for(String base64Img : imageList){
                Timage timage=new Timage();
                timage.setImage(base64Img.replace("&comma;",","));
                /*logger.info(base64Img.length()+"  "+base64Img.replace("&comma;",",").substring(0,50));*/
                timage.setTwitter(twitter);
                timageServiceImpl.addTwitterImage(timage);
            }
            //添加消息
            List<User> attentUsers=attentionServiceImpl.getAttent(user.getId());
            for(User attentuser:attentUsers){
                Message message=new Message();
                message.setType(2);
                message.setTwitter(twitter);
                message.setReceiver(attentuser);
                String messageContent="您关注的'"+user.getName()+"'的推主发布了主题为'"+twitter.getTitle()+"'推特：";
                message.setContent(messageContent);
                messageServiceImpl.save(message);
                //logger.info("为关注"+user.getName()+"的"+ attentuser.getName()+"添加信息");
            }
        }
        return flag;
    }

    @RequestMapping("/convertBase64")
    @ResponseBody
    public String convertBase64Encoder(@RequestParam("image") MultipartFile image){

//        logger.info("已访问到control层");
        String img_name=null;

        if(image==null){
            logger.info("文件为空");
            return null;
        }
        String suffix=image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
        img_name=image.getOriginalFilename().substring(0,image.getOriginalFilename().lastIndexOf('.'));
       logger.info("convertBase64Encoder 方法 上传的文件名为："+img_name+suffix);
        if(!".jpg".equalsIgnoreCase(suffix)&&!".png".equalsIgnoreCase(suffix)){
            return null;
        }

        BASE64Encoder encoder=new BASE64Encoder();
        StringBuffer base64Str= new StringBuffer("data:image/"+suffix+";base64,");
        try {
            base64Str =base64Str.append(encoder.encode(image.getBytes())) ;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("图片转换为base64编码失败");
            return null;
        }

        return base64Str.toString();
    }

    @RequestMapping("/getPariseTwitterById")
    @ResponseBody
    public String getPariseTwitterById(String id,HttpSession session){
        ObjectMapper mapper=new ObjectMapper();
        PraiseTwitter praiseTwitter=new PraiseTwitter();
        User user=(User)session.getAttribute("user");
        Twitter twitter=twitterServiceImpl.getTwitterById(id);
        Praise praise=praiseServiceImpl.getPraiseByUserAndTwitter(user.getId(),twitter.getId());
        List<Timage> timageList=timageServiceImpl.getTimageByTwitter(twitter);
        praiseTwitter.setTwitter(twitter);
        praiseTwitter.setPraise(praise);
        praiseTwitter.setTimageList(timageList);
        String jsonStr=null;
        try {
            jsonStr=mapper.writeValueAsString(praiseTwitter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    @RequestMapping("/getOthersTwitter")
    @ResponseBody
    public String getOthersTwitter(String id,int page,HttpSession session){
        ObjectMapper mapper=new ObjectMapper();
        Date time= (Date) session.getAttribute("toOthersPageTime");
      /*  logger.info("id:"+id);
        System.out.println(id);*/
        AttentedUser attentedUser=new AttentedUser();
        User user=(User)session.getAttribute("user");
        User otheruser= userServiceImpl.findUser(id);
        /* logger.info("user:"+otheruser);*/
        boolean attented=attentionServiceImpl.getAttention(user.getId(),id);
        attentedUser.setUser(otheruser);
        attentedUser.setAttented(attented);

        List<Twitter> list= twitterServiceImpl.getTwitterPageByUserId(id,time,page);
        List<PraiseTwitter> praiseTwitterList=new ArrayList<PraiseTwitter>();
        for(Twitter twitter:list){
            Praise praise=praiseServiceImpl.getPraiseByUserAndTwitter(user.getId(),twitter.getId());
            PraiseTwitter praiseTwitter=new PraiseTwitter();
            praiseTwitter.setPraise(praise);
            praiseTwitter.setTwitter(twitter);
            praiseTwitter.setTimageList(timageServiceImpl.getTimageByTwitter(twitter));
            praiseTwitterList.add(praiseTwitter);
        }
        String jsonStr="";
        try {
            jsonStr=mapper.writeValueAsString(praiseTwitterList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}
