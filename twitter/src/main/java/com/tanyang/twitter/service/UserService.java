package com.tanyang.twitter.service;

import java.sql.Date;

public interface UserService  {

    boolean login(String email,String password);

    boolean register(String name,String password,String realname,String gender,String email,String mobile,Date birthday);

    boolean vertify(String email);
}
