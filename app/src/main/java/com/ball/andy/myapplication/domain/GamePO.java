package com.ball.andy.myapplication.domain;

import java.io.Serializable;

/**
 * Created by Andy on 2016/1/25.
 */
public class GamePO extends DominKey implements Serializable {
    private Long id;
    private Long aId;
    private Long bId;
    private String name="";
    private String aName="";
    private String bName="";
    private String aScore="";
    private String bScore="";
    private String status="";


    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

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

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getaScore() {
        return aScore;
    }

    public void setaScore(String aScore) {
        this.aScore = aScore;
    }

    public String getbScore() {
        return bScore;
    }

    public void setbScore(String bScore) {
        this.bScore = bScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
