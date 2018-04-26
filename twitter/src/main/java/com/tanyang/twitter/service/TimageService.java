package com.tanyang.twitter.service;

import com.tanyang.twitter.pojo.Timage;
import com.tanyang.twitter.pojo.Twitter;

import java.util.List;

public interface TimageService {

   void addTwitterImage(Timage timage);

   List<Timage> getTimageByTwitter(Twitter twitter);
}
