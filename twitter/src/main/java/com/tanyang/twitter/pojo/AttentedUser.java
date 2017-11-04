package com.tanyang.twitter.pojo;

import org.springframework.stereotype.Component;

import java.sql.Date;
@Component
public class AttentedUser {
    private User user;
    private boolean attented;

    public AttentedUser(User user, boolean attented) {
        this.user = user;
        this.attented = attented;
    }

    public AttentedUser() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAttented() {
        return attented;
    }

    public void setAttented(boolean attented) {
        this.attented = attented;
    }

    @Override
    public String toString() {
        return "AttentedUser{" +
                "user=" + user +
                ", attented=" + attented +
                '}';
    }
}
