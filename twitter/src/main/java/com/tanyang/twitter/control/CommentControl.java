package com.tanyang.twitter.control;

import com.tanyang.twitter.pojo.Comment;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.CommentServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentControl {

    @Autowired
    private CommentServiceimpl commentServiceimpl;

    @RequestMapping("/addComment")
    public boolean addComment(String content, String parent, String twitter, HttpSession session){
        User user=(User)session.getAttribute("user");
        Comment comment=new Comment();
        Comment parentcom=new Comment();
        parentcom.setId(parent);
        Twitter twi=new Twitter();
        twi.setId(twitter);
        comment.setContent(content);
        comment.setParent(parentcom);
        comment.setTwitter(twi);
        comment.setUser(user);
        return commentServiceimpl.addComment(comment);
    }
}
