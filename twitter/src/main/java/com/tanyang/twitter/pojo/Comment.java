package com.tanyang.twitter.pojo;

import com.tanyang.twitter.utils.UuidUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Comment {

    @Id
    private String id;
    private String content;
    private Date date;

    @ManyToOne
    @JoinColumn(name="twitter")
    private Twitter twitter;
    @ManyToOne
    @JoinColumn(name="user")
    private User user;
    private int floor;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Comment parent;

    public Comment() {
        this.id= UuidUtil.getUUID();
        this.date=new Date(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", twitter=" + twitter +
                ", user=" + user +
                ", floor=" + floor +
                ", parent='" + parent + '\'' +
                '}';
    }
}
