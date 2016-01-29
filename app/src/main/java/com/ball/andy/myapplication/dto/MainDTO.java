package com.ball.andy.myapplication.dto;

import com.ball.andy.myapplication.domain.TeamPO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/20.
 */
public class MainDTO implements Serializable {
    private List<MainPlayerDTO> playerDTOs = new ArrayList<MainPlayerDTO>();


    private List<TeamPO> teamPOs=new ArrayList<>();




    public List<MainPlayerDTO> getPlayerDTOs() {
        return playerDTOs;
    }

    public void setPlayerDTOs(List<MainPlayerDTO> playerDTOs) {
        this.playerDTOs = playerDTOs;
    }

    private MainPlayerDTO selectPlayDTO;

    public MainPlayerDTO getSelectPlayDTO() {
        return selectPlayDTO;
    }

    public void setSelectPlayDTO(MainPlayerDTO selectPlayDTO) {
        this.selectPlayDTO = selectPlayDTO;
    }



    public List<TeamPO> getTeamPOs() {
        return teamPOs;
    }

    public void setTeamPOs(List<TeamPO> teamPOs) {
        this.teamPOs = teamPOs;
    }
}
