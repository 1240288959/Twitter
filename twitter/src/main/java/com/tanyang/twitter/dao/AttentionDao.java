package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.Attention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttentionDao extends JpaRepository<Attention,String>{
}
