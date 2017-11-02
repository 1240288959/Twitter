package com.tanyang.twitter.service;

import com.tanyang.twitter.dao.CommentDao;
import com.tanyang.twitter.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
