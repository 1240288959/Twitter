package com.tanyang.twitter.pojo;

import java.util.List;

public class PraiseTwitter {
    private Praise praise;
    private Twitter twitter;
    private List<Timage> timageList ;

    public Praise getPraise() {
        return praise;
    }

    public void setPraise(Praise praise) {
        this.praise = praise;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public List<Timage> getTimageList() {
        return timageList;
    }

    public void setTimageList(List<Timage> timageList) {
        this.timageList = timageList;
    }

    @Override
    public String toString() {
        return "PraiseTwitter{" +
                "praise=" + praise +
                ", twitter=" + twitter +
                ", timageList=" + timageList +
                '}';
    }
}
