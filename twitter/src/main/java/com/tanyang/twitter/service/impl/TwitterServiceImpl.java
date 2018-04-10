package com.tanyang.twitter.service.impl;

import com.tanyang.twitter.dao.TwitterDao;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TwitterServiceImpl implements TwitterService {
    @Autowired
    private TwitterDao twitterDao;

    @Value("${twiNumPerPage}")
    private Integer twiNumPerPage;

    @Override
    public List<Twitter> getTwitterByAttention(String id) {
        return twitterDao.getTwitterByAttention(id);
    }

    @Override
    public List<Twitter> getTwitterByUserId(String id) {
        return twitterDao.getTwitterByUserId(id);
    }

    @Override
    public List<Twitter> getTwitterPageByUserId(String id, Date time, Integer page) {
        Integer tstart=(page-1)*twiNumPerPage;
        Integer num=twiNumPerPage;
        return twitterDao.getTwitterPageByUserId(id,time,tstart,num);
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

    @Override
    public List<Twitter> getTwitterPageByAttention(String id, Date time, Integer page) {
        Integer tstart=(page-1)*twiNumPerPage;
        Integer num=twiNumPerPage;
        return twitterDao.getTwitterPageByAttention(id,time,tstart,num);
    }
}
