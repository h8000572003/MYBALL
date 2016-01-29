package com.ball.andy.myapplication.dto;

import java.io.Serializable;

/**
 * Created by Andy on 2016/1/20.
 */
public class MainPlayerDTO implements Serializable {
    private String name = "";
    private String mail;

    private boolean isClick = false;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MainPlayerDTO{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", isClick=" + isClick +
                '}';
    }
}
