package com.ball.andy.myapplication.util;

import android.content.Context;

import com.ball.andy.myapplication.domain.TeamPO;
import com.ball.andy.myapplication.dto.MainPlayerDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by Andy on 2016/1/28.
 */
public interface TeamCompnet {

    public List<TeamPO> makeTeams(Map<String, List<MainPlayerDTO>> teams, Context ct);
}
