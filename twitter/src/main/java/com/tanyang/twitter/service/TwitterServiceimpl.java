package com.tanyang.twitter.service;

import com.tanyang.twitter.dao.TwitterDao;
import com.tanyang.twitter.pojo.Twitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterServiceimpl implements TwitterService{
    @Autowired
    private TwitterDao twitterDao;

    @Override
    public List<Twitter> getTwitterByAttention(String id) {
        return twitterDao.getTwitterByAttention(id);
    }

    @Override
    public List<Twitter> getTwitterByUserId(String id) {
        return twitterDao.getTwitterByUserId(id);
    }
}
