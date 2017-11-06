package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.Comment;
import com.tanyang.twitter.pojo.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment,String>{

    List<Comment> getAllByTwitter(Twitter twitter);
}
