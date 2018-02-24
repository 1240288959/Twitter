package com.tanyang.twitter.pojo;

public class PraiseTwitter {
    private Praise praise;
    private Twitter twitter;

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

    @Override
    public String toString() {
        return "PraiseTwitter{" +
                "praise=" + praise +
                ", twitter=" + twitter +
                '}';
    }
}
