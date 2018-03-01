package com.tanyang.twitter.service;

import com.tanyang.twitter.pojo.Attention;
import com.tanyang.twitter.pojo.User;

import java.util.List;

public interface AttentionService {
    List<User> getAttented(String id);

    boolean addAttented(String Attent,String Attented);

    boolean deleteByAttented(String id);

    boolean getAttention(String attent,String attented);

    List<User> getAttent(String attentedid);
}
