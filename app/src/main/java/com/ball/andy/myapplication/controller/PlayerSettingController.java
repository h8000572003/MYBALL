package com.ball.andy.myapplication.controller;

import android.content.Context;

import com.ball.andy.myapplication.dao.PlayDAO;
import com.ball.andy.myapplication.domain.PlayerPO;
import com.ball.andy.myapplication.dto.PlayerItemDTO;
import com.ball.andy.myapplication.dto.PlayerSettingDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andy on 2016/1/29.
 */
public class PlayerSettingController implements Serializable {
    private PlayerSettingDTO dto = new PlayerSettingDTO();
    private Context context;

    public PlayerSettingController(Context context) {
        this.context = context;
    }

    /**
     *
     */
    public void load() {

        final PlayDAO dao = new PlayDAO(getContext());
        dto.getPlayerPOs().clear();

        final List<PlayerPO>playerPOs=dao.getAll();
        for(PlayerPO playerPO:playerPOs){

            PlayerItemDTO item=new PlayerItemDTO();
            item.setId(playerPO.getId());
            item.setName(playerPO.getName());

            dto.getPlayerPOs().add(item);
        }



    }


    public PlayerSettingDTO getDto() {
        return dto;
    }

    public void setDto(PlayerSettingDTO dto) {
        this.dto = dto;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
