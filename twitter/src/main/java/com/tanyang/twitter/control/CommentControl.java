package com.tanyang.twitter.control;

import com.tanyang.twitter.pojo.Comment;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.CommentServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping("/getComment")
    @ResponseBody
    public List<Comment> getCommentByTwitterId(String twitterid){
        List<Comment> list=null;
        Twitter twitter=new Twitter();
        twitter.setId(twitterid);
        list=commentServiceimpl.getCommentByTwitterId(twitter);
        return list;
    }
}
