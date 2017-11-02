package com.tanyang.twitter.service;

import com.tanyang.twitter.dao.UserDao;
import com.tanyang.twitter.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Service
public class UserServiceimpl implements UserService {

    private static final Logger logger= LoggerFactory.getLogger(UserServiceimpl.class);
    @Autowired
    private UserDao userDao;

    public boolean login(String email, String password, HttpSession session){
        User user=null;
        try{
            user =userDao.getUserByEmailAndPassword(email,password);
            session.setAttribute("user",user);
            logger.info("UserId为"+user.getId());
            logger.info("UserService层:login方法:中间产物:User:"+user);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("UserService层:login方法没有获取到对象");
        }
        if(user==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean register(String name, String password,String realname,String gender, String email, String mobile, Date birthday) {
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        user.setRealname(realname);
        user.setGender(gender);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setBirthday(birthday);
        try{
            user=userDao.save(user);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean vertify(String email) {
        int flag=0;
        try{
            flag=userDao.updateStatusByEmail(email);
            if(flag==1)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public boolean setImage(String image, String id) {
        try{
            userDao.updateImageById(image,id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
}
