package com.tanyang.twitter.pojo;

import com.tanyang.twitter.utils.UuidUtil;

import javax.persistence.*;

@Entity
@Table
public class Attention {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name="attent")
    private User attent;
    @ManyToOne
    @JoinColumn(name="attented")
    private User attented;

    public Attention() {
        this.id= UuidUtil.getUUID();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getAttent() {
        return attent;
    }

    public void setAttent(User attent) {
        this.attent = attent;
    }

    public User getAttented() {
        return attented;
    }

    public void setAttented(User attented) {
        this.attented = attented;
    }

    @Override
    public String toString() {
        return "Attention{" +
                "id='" + id + '\'' +
                ", attent=" + attent +
                ", attented=" + attented +
                '}';
    }
}
