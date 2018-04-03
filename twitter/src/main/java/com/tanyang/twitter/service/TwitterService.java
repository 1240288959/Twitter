package com.tanyang.twitter.service;

import com.tanyang.twitter.pojo.Twitter;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TwitterService {
    List<Twitter> getTwitterByAttention(String id);

    List<Twitter> getTwitterByUserId(String id);

    boolean deliveryTwitter(Twitter twitter);

    Twitter getTwitterById(String twitterid);

    List<Twitter> getTwitterPageByAttention(String id, Date time, Integer page);
}
