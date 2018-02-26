package com.tanyang.twitter.service.impl;

import com.tanyang.twitter.dao.PraiseDao;
import com.tanyang.twitter.pojo.Praise;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.PraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraiseServiceImpl implements PraiseService {

    @Autowired
    private PraiseDao praiseDao;
    @Override
    public boolean addPraise(Praise praise) {
        try{
            praiseDao.save(praise);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Praise getPraiseByUserAndTwitter(String userid, String twitterid) {
        Praise praise= praiseDao.getOneByUserAndTwitter(userid,twitterid);
        return praise;
    }

    @Override
    public boolean deletePraiseByUserAndTwitter(String userid, String twitterid) {
        try{
            praiseDao.deleteByUserAndTwitter(userid,twitterid);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
