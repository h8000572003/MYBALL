package com.ball.andy.myapplication.service;

import android.content.Context;
import android.util.Log;

import com.ball.andy.myapplication.dao.PlayDAO;
import com.ball.andy.myapplication.dao.TeamDAO;
import com.ball.andy.myapplication.domain.PlayerPO;
import com.ball.andy.myapplication.domain.TeamPO;
import com.ball.andy.myapplication.dto.MainDTO;
import com.ball.andy.myapplication.dto.MainPlayerDTO;
import com.ball.andy.myapplication.util.GameCompnet;
import com.ball.andy.myapplication.util.GameCompnetImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andy on 2016/1/21.
 */
public class MainServiceImpl implements MainService {

    private static final String TAG = "MainServiceImpl";
    private static MainService service;
    private GameCompnet gameCompnet = GameCompnetImpl.get();


    public static MainService get() {
        if (service != null) {
        } else {
            service = new MainServiceImpl();
        }
        return service;
    }

    @Override
    public void loadData(MainDTO dto, Context ct) {

        final PlayDAO playDAO = new PlayDAO(ct);


        if (playDAO.getCount() == 0) {
            playDAO.sampleData();
        }

        final List<PlayerPO> playerPOs = playDAO.getAll();

        dto.getPlayerDTOs().clear();
        for (PlayerPO playerPO : playerPOs) {
            final MainPlayerDTO playerDTO = new MainPlayerDTO();
            playerDTO.setName(playerPO.getName());

            dto.getPlayerDTOs().add(playerDTO);
        }


    }

    @Override
    public void ballot(MainDTO dto, Context ct) {


        final List<MainPlayerDTO> playerDTOs = new ArrayList<MainPlayerDTO>();


        for (MainPlayerDTO playerDTO : dto.getPlayerDTOs()) {
            if (playerDTO.isClick()) {
                playerDTOs.add(playerDTO);
            }
        }
        final Map<String, List<MainPlayerDTO>> teamsMap = new LinkedHashMap<>();

//        final List<MainPlayerDTO> tmps = new ArrayList<MainPlayerDTO>();


        int teamIndx = 0;
        int size = playerDTOs.size();

        boolean isHead = true;
        int team = 0;
        while (playerDTOs.size() > 0) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int index = (int) (Math.random() * (playerDTOs.size()));
            index = index < 0 ? 0 : index;

            final MainPlayerDTO playerDTO = playerDTOs.get(index);


            if (isHead) {
                final List<MainPlayerDTO> boxs = new ArrayList<MainPlayerDTO>();
                boxs.add(playerDTO);
                teamsMap.put(team + "", boxs);
                isHead = false;
            } else {

                final List<MainPlayerDTO> boxs = teamsMap.get(team + "");
                boxs.add(playerDTO);
                isHead = true;

                team++;
            }
            playerDTOs.remove(index);

        }


        for (Map.Entry<String, List<MainPlayerDTO>> tmp : teamsMap.entrySet()) {

            Log.i(TAG, tmp.getValue().toString());


        }
        dto.setTeams(teamsMap);


    }

    @Override
    public void deletePlayer(MainDTO deletePlayer, Context ct) {

    }

    @Override
    public void addPlayer(MainDTO player, Context ct) {

    }

    @Override
    public void makeGames(MainDTO player, Context ct) {

        final List<TeamPO> teamPOs = this.convertzer(player.getTeams());
        TeamDAO teamDAO = new TeamDAO(ct);

        for (TeamPO teamPO : teamPOs) {
            teamDAO.insert(teamPO);
        }


        gameCompnet.makeGames(teamPOs, ct);
    }

    private List<TeamPO> convertzer(Map<String, List<MainPlayerDTO>> map) {

        List<TeamPO> teams = new ArrayList<>();

        for (Map.Entry<String, List<MainPlayerDTO>> entry : map.entrySet()) {
            final TeamPO team = new TeamPO();
            team.setTeamName(entry.getKey());


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
