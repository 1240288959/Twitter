package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface TwitterDao extends JpaRepository<Twitter,String> {
    /*
    * 获取关注的推特
    * */
    @Query(value = "select * from twitter where user in (select attented from attention where attent=:id) or user = :id order by date desc",nativeQuery = true)
    List<Twitter> getTwitterByAttention(@Param("id") String id);

    @Query(value = "select * from twitter where user =(select id from user where name=:name) order by date desc",nativeQuery = true)
    List<Twitter> getTwitterByUserName(@Param("name") String name);

    @Query(value = "select * from twitter where user =:id order by date desc",nativeQuery = true)
    List<Twitter> getTwitterByUserId(@Param("id") String id);

    @Query(value="select * from twitter where user =:id and date<:time order by date desc limit :tstart, :num",nativeQuery = true)
    List<Twitter> getTwitterPageByUserId(@Param("id") String id,@Param("time")Date time,@Param("tstart")Integer tstart,@Param("num")Integer num);

    @Query(value = "select * from twitter where user in (select attented from attention where attent=:id) or user = :id and date<:time order by date desc limit :tstart, :num",nativeQuery = true)
    List<Twitter> getTwitterPageByAttention(@Param("id") String id,@Param("time")Date time,@Param("tstart")Integer tstart,@Param("num")Integer num);
}
