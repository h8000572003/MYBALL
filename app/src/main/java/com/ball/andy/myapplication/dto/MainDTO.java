package com.ball.andy.myapplication.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andy on 2016/1/20.
 */
public class MainDTO implements Serializable{
    private List<MainPlayerDTO> playerDTOs = new ArrayList<MainPlayerDTO>();


    private Map<String, List<MainPlayerDTO>> teams;

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

    public Map<String, List<MainPlayerDTO>> getTeams() {
        return teams;
    }

    public void setTeams(Map<String, List<MainPlayerDTO>> teams) {
        this.teams = teams;
    }
}
