package com.tanyang.twitter.pojo;

import com.tanyang.twitter.utils.UuidUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Message {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "comment")
    private Comment comment;
    @ManyToOne
    @JoinColumn(name = "twitter")
    private Twitter twitter;
    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

    private Date date;

    private int isread;

    private int type;

    private String content;

    public Message() {
        this.id= UuidUtil.getUUID();
        this.date=new Date(System.currentTimeMillis());
        isread=0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public int getIsread() {
        return isread;
    }

    public void setIsread(int isread) {
        this.isread = isread;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", comment=" + comment +
                ", twitter=" + twitter +
                ", receiver=" + receiver +
                ", date=" + date +
                ", isread=" + isread +
                ", type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
