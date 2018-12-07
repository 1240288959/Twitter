package com.tanyang.twitter.service.impl;

import com.tanyang.twitter.dao.CommentDao;
import com.tanyang.twitter.pojo.Comment;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
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
    public int countCommentByTwitter(Twitter twitter){
        return commentDao.countCommentByTwitter(twitter);
    }

    @Override
    public List<Comment> getCommentByTwitterId(Twitter twitter) {
        List<Comment> list=null;
        try{
            list=commentDao.getAllByTwitterId(twitter.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Comment getCommentById(String id){
        return commentDao.findById(id).get();
    }
}
