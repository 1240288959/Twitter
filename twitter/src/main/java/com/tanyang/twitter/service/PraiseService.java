package com.tanyang.twitter.service;

import com.tanyang.twitter.pojo.Praise;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;

public interface PraiseService {

    boolean addPraise(Praise praise);

    Praise getPraiseByUserAndTwitter(String userid, String twitterid);

    boolean deletePraiseByUserAndTwitter(String userid,String twitterid);
}
