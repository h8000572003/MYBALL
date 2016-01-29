package com.ball.andy.myapplication.util;

import android.content.Context;

import com.ball.andy.myapplication.domain.TeamPO;
import com.ball.andy.myapplication.dto.MainPlayerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andy on 2016/1/28.
 */
public class TeamCompnetImpl implements TeamCompnet {
    private static TeamCompnet compnet;

    public static TeamCompnet get() {
        if (compnet == null) {
            compnet = new TeamCompnetImpl();
        }
        return compnet;
    }

    @Override
    public List<TeamPO> makeTeams(Map<String, List<MainPlayerDTO>> teams, Context ct) {
        return this.convertzer(teams);
    }

    private List<TeamPO> convertzer(Map<String, List<MainPlayerDTO>> map) {

        List<TeamPO> teams = new ArrayList<>();


        char teamName = 'A';
        for (Map.Entry<String, List<MainPlayerDTO>> entry : map.entrySet()) {
            final TeamPO team = new TeamPO();
            team.setTeamName(++teamName + "");


            for (int i = 0; i < entry.getValue().size(); i++) {
                if (i == 0) {
                    team.setPlayer1(entry.getValue().get(i).getName());
                } else {
                    team.setPlayer2(entry.getValue().get(i).getName());
                }
            }
            teams.add(team);
        }

        return teams;

    }
}
