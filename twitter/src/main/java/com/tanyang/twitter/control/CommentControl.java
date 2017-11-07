package com.tanyang.twitter.control;

import com.tanyang.twitter.pojo.Comment;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.CommentServiceimpl;
import com.tanyang.twitter.service.TwitterServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentControl {

    private Logger logger= LoggerFactory.getLogger(CommentControl.class);
    @Autowired
    private CommentServiceimpl commentServiceimpl;
    @Autowired
    private TwitterServiceimpl twitterServiceimpl;

    @RequestMapping("/addComment")
    @ResponseBody
    public boolean addComment(String content, String twitter, HttpSession session){
        User user=(User)session.getAttribute("user");
        Comment comment=new Comment();
        Twitter twi=twitterServiceimpl.getTwitterById(twitter);
        comment.setContent(content);
        comment.setTwitter(twi);
        comment.setUser(user);
        return commentServiceimpl.addComment(comment);
    }

    @RequestMapping("/getComment")
    @ResponseBody
    public List<Comment> getCommentByTwitterId(String twitterid, Model model){
        logger.info("Twitterid:"+twitterid);
        List<Comment> list=null;
        Twitter twitter=twitterServiceimpl.getTwitterById(twitterid);
        list=commentServiceimpl.getCommentByTwitterId(twitter);
        model.addAttribute("commentlist",list);
        return list;
    }
}
