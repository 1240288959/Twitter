package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.Comment;
import com.tanyang.twitter.pojo.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment,String>{

    List<Comment> getAllByTwitter(Twitter twitter);

    @Query(value = "select * from comment where twitter=:twitterid order by date desc",nativeQuery = true)
    List<Comment> getAllByTwitterId(@Param("twitterid") String twitterid);

    int countCommentByTwitter (Twitter twitter);
}
