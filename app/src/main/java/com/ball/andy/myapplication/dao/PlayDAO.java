package com.ball.andy.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ball.andy.myapplication.util.DBHelper;
import com.ball.andy.myapplication.domain.PlayerPO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/20.
 */
public class PlayDAO {


    // 資料庫物件
    private SQLiteDatabase db;
    // 表格名稱
    public static final String TABLE_NAME = "player";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";


    private static final String name = "name";


    public PlayDAO(Context context) {
        db = DBHelper.getDatabase(context);
    }

    public void close() {
        db.close();
    }

    public PlayerPO insert(PlayerPO playerPO) {

        ContentValues cv = new ContentValues();
        cv.put("name", playerPO.getName());


        long id = db.insert(TABLE_NAME, null, cv);
        playerPO.setId(id);
        return playerPO;
    }

    public boolean update(PlayerPO playerPO) {
        ContentValues cv = new ContentValues();
        cv.put("name", playerPO.getName());

        return db.update(TABLE_NAME, cv, "_id=? ", new String[]{playerPO.getId() + ""}) > 0;
    }

    public boolean delete(PlayerPO playerPO) {
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + playerPO.getId();
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where, null) > 0;
    }

    public List<PlayerPO> getAll() {
        List<PlayerPO> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    public PlayerPO get(long id) {
        // 準備回傳結果用的物件
        PlayerPO item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    public PlayerPO get(String where, String[] values) {
        // 準備回傳結果用的物件
        PlayerPO item = null;
        // 使用編號為查詢條件

        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, values, null, null, null, null);

        // 如果有查詢結果
        while (result.moveToNext()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
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
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

}



