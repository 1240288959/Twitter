package com.tanyang.twitter.control;

import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.service.TwitterServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TwitterControl {

    private static final Logger logger=LoggerFactory.getLogger(Twitter.class);
    @Autowired
    private TwitterServiceimpl twitterServiceimpl;
    @RequestMapping("/tomain")
    public String getAttentionTwitter(Model model, HttpSession session){
        String id=session.getAttribute("id").toString();
        logger.info("id :"+id);
        List<Twitter> list=twitterServiceimpl.getTwitterByAttention(id);
        model.addAttribute("list",list);
        logger.info("list:"+list);
        return "main";
    }
}
