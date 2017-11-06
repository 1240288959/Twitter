package com.tanyang.twitter.service;

import com.tanyang.twitter.dao.CommentDao;
import com.tanyang.twitter.pojo.Comment;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceimpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public boolean addComment(Comment comment) {
        try{
            commentDao.save(comment);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public List<Comment> getCommentByTwitterId(Twitter twitter) {
        List<Comment> list=null;
        try{
            list=commentDao.getAllByTwitter(twitter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
