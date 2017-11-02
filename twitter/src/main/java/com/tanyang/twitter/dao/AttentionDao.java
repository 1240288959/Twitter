package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.Attention;
import com.tanyang.twitter.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface AttentionDao extends JpaRepository<Attention,String>{

    @Query(value = "select attented from attention where attent=:id",nativeQuery = true)
    List<String> getUserByAttent(@Param("id") String id);
}
