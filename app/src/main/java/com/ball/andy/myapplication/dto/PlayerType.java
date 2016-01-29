package com.ball.andy.myapplication.dto;

/**
 * Created by Andy on 2016/1/21.
 */
public class PlayerType {
    private TeamType teamType = new TeamType();


    public TeamType getTeamType() {
        return teamType;
    }

    public void setTeamType(TeamType teamType) {
        this.teamType = teamType;
    }
}
