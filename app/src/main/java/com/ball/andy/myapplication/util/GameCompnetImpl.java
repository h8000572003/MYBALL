package com.ball.andy.myapplication.util;

import android.content.Context;

import com.ball.andy.myapplication.dao.GameDAO;
import com.ball.andy.myapplication.domain.TeamPO;

import java.util.List;

/**
 * Created by Andy on 2016/1/25.
 */
public class GameCompnetImpl implements GameCompnet {

    private transient static GameCompnet gameCompnet;

    public static GameCompnet get() {
        if (gameCompnet == null) {
            gameCompnet = new GameCompnetImpl();
        }
        return gameCompnet;
    }

    @Override
    public void makeGames(List<TeamPO> teamList, Context ct) {
        final GameDAO dao = new GameDAO(ct);


        dao.deleteAll(dao.getAll());
        dao.makeGames(teamList);
    }
}
