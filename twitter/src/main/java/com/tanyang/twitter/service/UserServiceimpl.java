package com.tanyang.twitter.service;

import com.tanyang.twitter.dao.UserDao;
import com.tanyang.twitter.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl {

    private static final Logger logger= LoggerFactory.getLogger(UserServiceimpl.class);
    @Autowired
    private UserDao userDao;

    public boolean login(String email,String password){
        User user=null;
        try{
            user =userDao.getUserByEmailAndPassword(email,password);
            logger.debug("UserService层:login方法:中间产物:User:"+user);
            System.out.println("UserService层:login方法:中间产物:User:"+user);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("UserService层:login方法没有获取到对象");
            System.out.println("UserService层:login方法:没有获取到对象");
        }
        if(user==null){
            return false;
        }
        return true;
    }
}
