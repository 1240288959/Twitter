package com.tanyang.twitter.service.impl;

import com.tanyang.twitter.dao.TimageDao;
import com.tanyang.twitter.pojo.Timage;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.service.TimageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimageServiceImpl implements TimageService {

    @Autowired
    private TimageDao timageDao;
    @Override
    public void addTwitterImage(Timage timage) {
        try{
            timageDao.save(timage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Timage> getTimageByTwitter(Twitter twitter){
        List<Timage> timagesList=new ArrayList<>();
        try{
            timagesList=timageDao.getAllByTwitter(twitter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  timagesList;
    }
}
