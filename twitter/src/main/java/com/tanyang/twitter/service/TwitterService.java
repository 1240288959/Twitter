package com.tanyang.twitter.service;

import com.tanyang.twitter.pojo.Twitter;

import java.util.List;

public interface TwitterService {
    List<Twitter> getTwitterByAttention(String id);

    List<Twitter> getTwitterByUserId(String id);

    boolean addTwitter(Twitter twitter);
}
