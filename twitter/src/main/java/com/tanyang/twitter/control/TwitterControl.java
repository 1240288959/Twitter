package com.tanyang.twitter.control;

import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.TwitterServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TwitterControl {

    private static final Logger logger=LoggerFactory.getLogger(Twitter.class);
    @Autowired
    private TwitterServiceimpl twitterServiceimpl;

    @RequestMapping("/tomain")
    public String getAttentionTwitter(Model model, HttpSession session){
        User user=(User)session.getAttribute("user");
        logger.info("id :"+user.getId());
        List<Twitter> list=twitterServiceimpl.getTwitterByAttention(user.getId());
        model.addAttribute("list",list);
        logger.info("list:"+list);
        return "main";
    }

    @RequestMapping("/tomypage")
    public String getMyAttention(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Twitter> list=twitterServiceimpl.getTwitterByUserId(user.getId());
        model.addAttribute("list",list);
        logger.info("list:"+list);
        return "mypage";
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
        return twitterServiceimpl.deliveryTwitter(twitter);
    }
}
