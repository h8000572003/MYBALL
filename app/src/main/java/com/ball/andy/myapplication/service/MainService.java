package com.ball.andy.myapplication.service;

import android.content.Context;

import com.ball.andy.myapplication.dto.MainDTO;

/**
 * Created by Andy on 2016/1/20.
 */
public interface MainService {

    /**
     * 建立初始資料
     *
     * @param dto
     */
    void loadData(MainDTO dto, Context ct);

    /**
     * 分配隊友
     * param dto
     */
    void ballot(MainDTO dto, Context ct);

    /**
     * 刪除
     * @param deletePlayer
     */
    void deletePlayer(MainDTO deletePlayer, Context ct);


    /**
     * 新增
     * @param player
     */
    void addPlayer(MainDTO player, Context ct);


    void makeGames(MainDTO player, Context ct);

}
