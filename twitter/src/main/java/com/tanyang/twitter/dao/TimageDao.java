package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.Timage;
import com.tanyang.twitter.pojo.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimageDao extends JpaRepository<Timage,String> {

    List<Timage> getAllByTwitter(Twitter twitter);
}
