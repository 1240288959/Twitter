package com.tanyang.twitter.pojo;

import com.tanyang.twitter.utils.UUIDutil;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class Twitter {

    @Id
    private String id;
    private String title;
    private String content;
    private Date date;

    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    public Twitter() {
        this.id= UUIDutil.getUUID();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Twitter(String id, String title, String content, Date date, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.user = user;
    }
}
