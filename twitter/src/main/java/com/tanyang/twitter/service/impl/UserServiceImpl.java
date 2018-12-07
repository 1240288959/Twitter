package com.tanyang.twitter.service.impl;

import com.tanyang.twitter.dao.UserDao;
import com.tanyang.twitter.pojo.User;
import com.tanyang.twitter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Value("${peoNumPerPage}")
    private int peoNumPerPage;


    public boolean login(String email, String password, HttpSession session){
        User user=null;
        try{
            user =userDao.getUserByEmailAndPassword(email,password);
            session.setAttribute("user",user);
           /* logger.info("UserId为"+user.getId());
            logger.info("UserService层:login方法:中间产物:User:"+user);*/
        }catch (Exception e){
            e.printStackTrace();
            /*logger.info("UserService层:login方法没有获取到对象");*/
        }
        if(user==null||user.getStatus()==0){
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
    public boolean register(String name, String password,String realname,String gender, String email, String mobile, Date birthday,String img) {
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        user.setRealname(realname);
        user.setGender(gender);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setImage(img);
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
    public boolean updateUserInform(User user){
        try{
            userDao.save(user);
            return true;
        }catch (Exception e){
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

    @Override
    public List<User> searchUser(String name,HttpSession session) {
        String username=((User)session.getAttribute("user")).getName();
        return userDao.getUserByName(name,username);
    }

    @Override
    public List<User> searchUserPage(String name, int page, HttpSession session) {
        String username=((User)session.getAttribute("user")).getName();
        Integer tstart=(page-1)*peoNumPerPage;
        Integer num=peoNumPerPage;
        return userDao.getUserPageByName(name,username,tstart,num);
    }

    @Override
    public User findUser(String id) {
        User user=null;
        try{
            user=userDao.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
