package com.ball.andy.myapplication.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/21.
 */
public class TeamType {
    private List<String> players = new ArrayList<String>();

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }


    @Override
    public String toString() {
        return StringUtils.join(players, ",");
    }
}
