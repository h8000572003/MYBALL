package com.ball.andy.myapplication.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/29.
 */
public class PlayerSettingDTO implements Serializable {
    private List<PlayerItemDTO> playerPOs=new ArrayList<PlayerItemDTO>();

    public List<PlayerItemDTO> getPlayerPOs() {
        return playerPOs;
    }

    public void setPlayerPOs(List<PlayerItemDTO> playerPOs) {
        this.playerPOs = playerPOs;
    }
}
