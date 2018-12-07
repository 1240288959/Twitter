package com.tanyang.twitter.service.impl;

import com.tanyang.twitter.dao.AttentionDao;
import com.tanyang.twitter.dao.UserDao;
import com.tanyang.twitter.pojo.Attention;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.AttentionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttentionServiceImpl implements AttentionService {
    private Logger logger= LoggerFactory.getLogger(AttentionService.class);
    @Autowired
    private AttentionDao attentionDao;
    @Autowired
    private UserDao userDao;
    @Value("${peoNumPerPage}")
    private int peoNumPerPage;
    @Override
    public List<User> getAttented(String id) {
        List<User> userlist=new ArrayList<>();
        List<String> list= attentionDao.getUserByAttent(id);
        for(String str:list){
            logger.info(str);
            User user=userDao.findById(str).get();
            logger.info(user.toString());
            userlist.add(user);
        }
        return userlist;
    }

    @Override
    public List<User> getAttentedPage(String id,int page) {
        Integer tstart=(page-1)*peoNumPerPage;
        Integer num=peoNumPerPage;
        List<User> userlist=new ArrayList<>();
        List<String> list= attentionDao.getUserPageByAttent(id,tstart,num);
        for(String str:list){
            logger.info(str);
            User user=userDao.findById(str).get();
            logger.info(user.toString());
            userlist.add(user);
        }
        return userlist;
    }

    @Override
    public boolean addAttented(String Attent, String Attented) {
        Attention attention=new Attention();
        User uattent=userDao.findById(Attent).get();
        User uattented=userDao.findById(Attented).get();
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
    public boolean getAttention(String attent, String attented) {
        Attention attention=null;
        try{
            attention=attentionDao.getAttentionByAttentAndAndAttented(attent,attented);
            if(attention==null||attention.getAttent()==null||attention.getAttented()==null){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public List<User> getAttent(String attentedid) {
        List<String> attentIdList=attentionDao.getAttentByAttented(attentedid);
        List<User> attentList=new ArrayList<>();
        for(String id:attentIdList){
            attentList.add(userDao.findById(id).get());
        }
        return attentList;
    }
}
