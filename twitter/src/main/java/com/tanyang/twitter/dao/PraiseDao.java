package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.Praise;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PraiseDao extends JpaRepository<Praise,String> {

    @Query(value = "select * from praise where user=:userid and twitter=:twitterid",nativeQuery = true)
    Praise getOneByUserAndTwitter(@Param("userid") String userid, @Param("twitterid") String twitterid);

    @Query(value = "delete from praise where user=:userid and twitter=:twitterid",nativeQuery = true)
    @Modifying
    Integer deleteByUserAndTwitter(@Param("userid") String user,@Param("twitterid") String twitter);
}
