package com.tanyang.twitter.service;

import javax.servlet.http.HttpSession;
import java.sql.Date;

public interface UserService  {

    boolean login(String email, String password, HttpSession session);

    boolean register(String name,String password,String realname,String gender,String email,String mobile,Date birthday);

    boolean vertify(String email);

    boolean setImage(String image,String id);
}
