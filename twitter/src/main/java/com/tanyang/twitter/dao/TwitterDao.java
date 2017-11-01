package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterDao extends JpaRepository<Twitter,String> {
}
