package com.ball.andy.myapplication.util;

import android.content.Context;

import com.ball.andy.myapplication.domain.TeamPO;

import java.util.List;

/**
 * Created by Andy on 2016/1/25.
 */
public interface GameCompnet {
    public void makeGames(List<TeamPO> teamList, Context ct);
}
