package com.tanyang.twitter.pojo;

import com.tanyang.twitter.utils.UuidUtil;

import javax.persistence.*;

@Entity
@Table
public class Praise {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name="user")
    private User user;
    @ManyToOne
    @JoinColumn(name="twitter")
    private Twitter twitter;

    public Praise() {
        this.id= UuidUtil.getUUID();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return "Praise{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", twitter=" + twitter +
                '}';
    }
}
