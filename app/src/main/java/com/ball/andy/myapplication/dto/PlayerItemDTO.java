package com.ball.andy.myapplication.dto;

/**
 * Created by Andy on 2016/1/29.
 */
public class PlayerItemDTO {
    private Long id;
    private String name = "";
    private String leavel;

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

    public String getLeavel() {
        return leavel;
    }

    public void setLeavel(String leavel) {
        this.leavel = leavel;
    }
}
