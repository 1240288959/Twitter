package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.Message;
import com.tanyang.twitter.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends JpaRepository<Message,String>{

    @Query(value = "select * from message where receiver=:receiverid order by date desc",nativeQuery = true)
    List<Message> getMessageByReciver(@Param("receiverid") String receiverid);

    @Query(value = "select count(*) from message where receiver=:receiverid and isread = 0 ",nativeQuery = true)
    Integer countMessagesByReceiverAndIsread(@Param("receiverid")String receiverid);
}
