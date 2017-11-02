package com.tanyang.twitter.service;

import com.tanyang.twitter.pojo.User;

import java.util.List;

public interface AttentionService {
    List<User> getAttented(String id);
}
