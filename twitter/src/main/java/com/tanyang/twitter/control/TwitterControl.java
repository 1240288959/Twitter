package com.tanyang.twitter.control;

import com.tanyang.twitter.pojo.Praise;
import com.tanyang.twitter.pojo.PraiseTwitter;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.impl.PraiseServiceImpl;
import com.tanyang.twitter.service.impl.TwitterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TwitterControl {

    private static final Logger logger=LoggerFactory.getLogger(Twitter.class);
    @Autowired
    private TwitterServiceImpl twitterServiceImpl;
    @Autowired
    private PraiseServiceImpl praiseServiceImpl;

    @RequestMapping("/tomain")
    public String getAttentionTwitter(Model model, HttpSession session){
        User user=(User)session.getAttribute("user");
        logger.info("id :"+user.getId());
        List<Twitter> list= twitterServiceImpl.getTwitterByAttention(user.getId());
        List<PraiseTwitter> praiseTwitterList=new ArrayList<PraiseTwitter>();
        for(Twitter twitter:list){
            Praise praise=praiseServiceImpl.getPraiseByUserAndTwitter(user.getId(),twitter.getId());
            PraiseTwitter praiseTwitter=new PraiseTwitter();
            praiseTwitter.setPraise(praise);
            praiseTwitter.setTwitter(twitter);
            praiseTwitterList.add(praiseTwitter);
        }
        model.addAttribute("list",praiseTwitterList);
        logger.info("list:"+list);
        return "main";
    }

    @RequestMapping("/tomypage")
    public String toMyPage(){
        return "mypage";
    }

    @RequestMapping("/tomytwitter")
    public String getMyTwitter(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Twitter> list= twitterServiceImpl.getTwitterByUserId(user.getId());
        model.addAttribute("list",list);
        logger.info("list:"+list);
        return "mytwitter";
    }

    @RequestMapping("/todeliverytwitter")
    public String todeliverytwitter(){
        return "deliverytwitter";
    }

    @RequestMapping("/deliverytwitter")
    @ResponseBody
    public boolean deliverytwitter(@RequestParam("title") String title,@RequestParam("content") String content, HttpSession session){
        logger.info("title:"+title+" content:"+content);
        Twitter twitter=new Twitter();
        User user=(User)session.getAttribute("user");
        twitter.setUser(user);
        twitter.setTitle(title);
        twitter.setContent(content);
        return twitterServiceImpl.deliveryTwitter(twitter);
    }
}
