package com.ball.andy.myapplication.controller;

import android.content.Context;

import com.ball.andy.myapplication.dao.GameDAO;
import com.ball.andy.myapplication.dao.TeamDAO;
import com.ball.andy.myapplication.domain.TeamPO;
import com.ball.andy.myapplication.dto.ScoreDTO;
import com.ball.andy.myapplication.dto.TeamDTO;

/**
 * Created by Andy on 2016/1/25.
 */
public class ScoreController {


    private Context context;

    private ScoreDTO dto = new ScoreDTO();


    public ScoreController(Context context) {
        this.context = context;
    }

    public void loadGames() {

        GameDAO dao = new GameDAO(context);
        dto.getGamePOs().clear();
        ;
        dto.getGamePOs().addAll(dao.getAll());


//        Collections.sort(dto.getGamePOs(), new Comparator<GamePO>() {
//            @Override
//            public int compare(GamePO lhs, GamePO rhs) {
//                return lhs.getStatus().compareTo(rhs.getStatus());
//            }
//        });

    }

    public void loadTeams() {
        TeamDAO dao = new TeamDAO(context);
        dto.getTeamPOs().clear();
        ;
        dto.getTeamPOs().addAll(dao.getAll());

        dto.getTeamDTOs().clear();
        for (TeamPO teamPO : dao.getAll()) {
            final TeamDTO teamDTO = new TeamDTO();
            teamDTO.setId(teamPO.getId());
            teamDTO.setPlayer1(teamPO.getPlayer1());
            teamDTO.setPlayer2(teamPO.getPlayer2());
            teamDTO.setTeamName(teamPO.getTeamName());
            teamDTO.setScore(this.getScore(teamPO));
            dto.getTeamDTOs().add(teamDTO);
        }


    }

    private int getScore(TeamPO teamPO) {

        GameDAO gameDAO = new GameDAO(getContext());

        int score = 0;
        score +=
                gameDAO.get("a_id=? and aScore>bScore and status=? ", new String[]{teamPO.getId() + "", "Y"}).size();
        score +=
                gameDAO.get("b_id=? and bScore>aScore and status=? ", new String[]{teamPO.getId() + "", "Y"}).size();


        return score;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ScoreDTO getDto() {
        return dto;
    }

    public void setDto(ScoreDTO dto) {
        this.dto = dto;
    }
}
