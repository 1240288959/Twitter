package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment,String>{
}
