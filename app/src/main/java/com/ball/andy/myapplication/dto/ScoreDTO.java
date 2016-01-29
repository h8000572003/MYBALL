package com.ball.andy.myapplication.dto;

import com.ball.andy.myapplication.domain.GamePO;
import com.ball.andy.myapplication.domain.TeamPO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/25.
 */
public class ScoreDTO {
    private List<GamePO>gamePOs=new ArrayList<GamePO>();

    private List<TeamPO>teamPOs=new ArrayList<>();

    private List<TeamDTO>teamDTOs=new ArrayList<TeamDTO>();


    public List<TeamDTO> getTeamDTOs() {
        return teamDTOs;
    }

    public void setTeamDTOs(List<TeamDTO> teamDTOs) {
        this.teamDTOs = teamDTOs;
    }

    public List<TeamPO> getTeamPOs() {
        return teamPOs;
    }

    public void setTeamPOs(List<TeamPO> teamPOs) {
        this.teamPOs = teamPOs;
    }

    public List<GamePO> getGamePOs() {
        return gamePOs;
    }

    public void setGamePOs(List<GamePO> gamePOs) {
        this.gamePOs = gamePOs;
    }
}
