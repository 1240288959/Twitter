package com.tanyang.twitter.control;

import com.tanyang.twitter.pojo.Praise;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.PraiseService;
import com.tanyang.twitter.service.impl.PraiseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class PraiseControl {
    private static final Logger logger= LoggerFactory.getLogger(Praise.class);
    @Autowired
    private PraiseServiceImpl praiseServiceImpl;


    @RequestMapping("/addPraise")
    @ResponseBody
    public boolean addPraise(String twitterId, HttpSession session){

        User user= (User) session.getAttribute("user");

        Twitter twitter=new Twitter();
        twitter.setId(twitterId);

        Praise praise=new Praise();
        praise.setUser(user);
        praise.setTwitter(twitter);
        try{
            praiseServiceImpl.addPraise(praise);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/deletePraise")
    @ResponseBody
    public boolean deletePraise(String twitterId,HttpSession session){
        User user= (User) session.getAttribute("user");
        try{
            praiseServiceImpl.deletePraiseByUserAndTwitter(user.getId(),twitterId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/isPraise")
    @ResponseBody
    public boolean isPraise(String twitterId,HttpSession session){
        User user= (User) session.getAttribute("user");
        /*System.out.println(user.getId()+"          "+twitterId);*/
        Praise praise=praiseServiceImpl.getPraiseByUserAndTwitter(user.getId(),twitterId);
        /*System.out.println("praise:"+praise);*/
        if(praise!=null){
            return true;
        }else{
            return false;
        }
    }
}
