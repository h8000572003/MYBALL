package com.ball.andy.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ball.andy.myapplication.domain.TeamPO;

/**
 * Created by Andy on 2016/1/28.
 */
public class TeamDAO extends BaseDAO<TeamPO> {


    private static Convertz convertz = new Convertz<TeamPO>() {
        @Override
        public TeamPO getDomain(Cursor cursor) {
            final TeamPO po = new TeamPO();
            po.setId(cursor.getLong(0));
            po.setTeamName(cursor.getString(1));
            po.setPlayer1(cursor.getString(2));
            po.setPlayer2(cursor.getString(3));

            return po;
        }

        @Override
        public ContentValues getContentValues(TeamPO domainType) {
            ContentValues values = new ContentValues();
            values.put("_id", domainType.getId());
            values.put("teamName", domainType.getTeamName());
            values.put("player1", domainType.getPlayer1());
            values.put("player2", domainType.getPlayer2());
            return values;
        }
    };


    public TeamDAO(Context context) {
        super(convertz, context);
    }

    @Override
    public String TABLE_NAME() {
        return "team";
    }



}
