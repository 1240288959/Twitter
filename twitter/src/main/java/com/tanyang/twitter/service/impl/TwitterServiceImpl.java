package com.tanyang.twitter.service.impl;

import com.tanyang.twitter.dao.TwitterDao;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterServiceImpl implements TwitterService {
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

    @Override
    public boolean deliveryTwitter(Twitter twitter) {
        try{
            twitterDao.save(twitter);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Twitter getTwitterById(String twitterid){
        Twitter twitter=null;
        try{
            twitter=twitterDao.findOne(twitterid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  twitter;
    }
}
