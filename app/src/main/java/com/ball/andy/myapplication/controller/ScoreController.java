package com.ball.andy.myapplication.controller;

import android.content.Context;

import com.ball.andy.myapplication.dao.GameDAO;
import com.ball.andy.myapplication.dao.TeamDAO;
import com.ball.andy.myapplication.dto.ScoreDTO;

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
    public void loadTeams(){
        TeamDAO dao = new TeamDAO(context);
        dto.getTeamPOs().clear();
        ;
        dto.getTeamPOs().addAll(dao.getAll());
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
