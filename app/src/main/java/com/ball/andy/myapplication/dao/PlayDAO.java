package com.ball.andy.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ball.andy.myapplication.domain.PlayerPO;

/**
 * Created by Andy on 2016/1/20.
 */
public class PlayDAO extends BaseDAO<PlayerPO> {


    // 表格名稱
    public static final String TABLE_NAME = "player";


    private static Convertz convertz = new Convertz<PlayerPO>() {
        @Override
        public PlayerPO getDomain(Cursor cursor) {
            final PlayerPO po = new PlayerPO();
            po.setId(cursor.getLong(0));
            po.setName(cursor.getString(1));

            return po;
        }

        @Override
        public ContentValues getContentValues(PlayerPO domainType) {
            ContentValues values = new ContentValues();
            values.put("_id", domainType.getId());
            values.put("name", domainType.getName());

            return values;
        }
    };

    public PlayDAO(Context context) {
        super(convertz, context);
    }


    public PlayerPO getRecord(Cursor cursor) {
        PlayerPO po = new PlayerPO();
        po.setId(cursor.getLong(0));
        po.setName(cursor.getString(1));

        return po;

    }

    final String[] NAMES = new String[]{//
            "忠", "阿梅", "詹組長", //
            "曉初", "美貞", "小藍", //
            "美君", "俊呱", "三井",//
            "雅輪", "Ryan", "9527", //
            "小新", "小瑋", "尚恩"};//

    public void sampleData() {
        for (String name : NAMES) {
            final PlayerPO po = new PlayerPO();
            po.setName(name);

            insert(po);
        }

    }

    public int getCount() {
        int result = 0;
        Cursor cursor = getDb().rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    @Override
    public String TABLE_NAME() {
        return TABLE_NAME;
    }
}



