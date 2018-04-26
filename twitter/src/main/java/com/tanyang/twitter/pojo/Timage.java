package com.tanyang.twitter.pojo;

import com.tanyang.twitter.utils.UuidUtil;

import javax.persistence.*;

@Table
@Entity
public class Timage {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name="twitter")
    private Twitter twitter;
    private String image;

    public Timage() {
        id= UuidUtil.getUUID();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Timage{" +
                "id='" + id + '\'' +
                ", twitter=" + twitter +
                ", image='" + image + '\'' +
                '}';
    }
}
