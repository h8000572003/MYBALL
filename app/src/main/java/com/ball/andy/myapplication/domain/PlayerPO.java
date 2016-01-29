package com.ball.andy.myapplication.domain;

/**
 * Created by Andy on 2016/1/20.
 */
public class PlayerPO extends DominKey{
    private Long id;
    private String name = "";


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
