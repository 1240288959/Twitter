package com.tanyang.twitter.service;

import com.tanyang.twitter.dao.AttentionDao;
import com.tanyang.twitter.dao.UserDao;
import com.tanyang.twitter.pojo.Attention;
import com.tanyang.twitter.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttentionServiceimpl implements AttentionService {
    private Logger logger= LoggerFactory.getLogger(AttentionService.class);
    @Autowired
    private AttentionDao attentionDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAttented(String id) {
        List<User> userlist=new ArrayList<>();
        List<String> list= attentionDao.getUserByAttent(id);
        for(String str:list){
            logger.info(str);
            User user=userDao.findOne(str);
            logger.info(user.toString());
            userlist.add(user);
        }
        return userlist;
    }

    @Override
    public boolean addAttented(String Attent, String Attented) {
        Attention attention=new Attention();
        User uattent=userDao.findOne(Attent);
        User uattented=userDao.findOne(Attented);
        attention.setAttent(uattent);
        attention.setAttented(uattented);
        try{
            attentionDao.save(attention);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteByAttented(String id) {
        try{
            attentionDao.deleteByAttented(id);
            return  true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Attention getAttention(String attent, String attented) {
        return attentionDao.getAttentionByAttentAndAndAttented(attent,attented);
    }
}
