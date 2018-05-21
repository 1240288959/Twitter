package com.tanyang.twitter.service;

import com.tanyang.twitter.pojo.User;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

public interface UserService  {

    boolean login(String email, String password, HttpSession session);

    boolean register(String name,String password,String realname,String gender,String email,String mobile,Date birthday);

    boolean register(String name,String password,String realname,String gender,String email,String mobile,Date birthday,String img);

    boolean vertify(String email);

    boolean setImage(String image,String id);

    List<User> searchUser(String name,HttpSession session);

    List<User> searchUserPage(String name,int page,HttpSession session);

    User findUser(String id);

    boolean updateUserInform(User user);
}
