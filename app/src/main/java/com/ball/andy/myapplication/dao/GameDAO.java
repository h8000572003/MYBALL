package com.ball.andy.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ball.andy.myapplication.domain.GamePO;
import com.ball.andy.myapplication.domain.TeamPO;
import com.ball.andy.myapplication.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/25.
 */
public class GameDAO {
    private static final String TAG = "GameDAO"; // 資料庫物件
    private SQLiteDatabase db;
    // 表格名稱
    public static final String TABLE_NAME = "game";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";


    public GameDAO(Context context) {
        db = DBHelper.getDatabase(context);
    }

    public void close() {
        db.close();
    }

    public GamePO insert(GamePO gamePO) {

        ContentValues cv = new ContentValues();
        cv.put("name", gamePO.getName());
        cv.put("aName", gamePO.getaName());
        cv.put("bName", gamePO.getbName());
        cv.put("aScore", gamePO.getaScore());
        cv.put("bScore", gamePO.getbScore());
        cv.put("status", gamePO.getStatus());

        cv.put("a_id", gamePO.getaId());
        cv.put("b_id", gamePO.getbId());

        long id = db.insert(TABLE_NAME, null, cv);
        gamePO.setId(id);
        return gamePO;
    }

    public void insert(List<GamePO> pos) {
        for (GamePO po : pos) {
            this.insert(po);
        }

    }

    public boolean update(GamePO gamePO) {

        ContentValues cv = new ContentValues();
        cv.put("name", gamePO.getName());
        cv.put("aName", gamePO.getaName());
        cv.put("bName", gamePO.getbName());
        cv.put("aScore", gamePO.getaScore());
        cv.put("bScore", gamePO.getbScore());
        cv.put("status", gamePO.getStatus());
        cv.put("a_id", gamePO.getaId());
        cv.put("b_id", gamePO.getbId());

        return db.update(TABLE_NAME, cv, "_id=? ", new String[]{gamePO.getId() + ""}) > 0;
    }


    public boolean deleteAll(List<GamePO> pos) {
        boolean isOK = true;
        for (GamePO po : pos) {
            isOK &= delete(po);
        }

        return isOK;
    }

    public boolean delete(GamePO po) {
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + po.getId();
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where, null) > 0;
    }

    public List<GamePO> getAll() {
        List<GamePO> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    public GamePO get(long id) {
        // 準備回傳結果用的物件
        GamePO item = null;
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

    public List<GamePO> get(String where, String[] values) {
        // 準備回傳結果用的物件
        List<GamePO> gamePOs = new ArrayList<GamePO>();
        // 使用編號為查詢條件

        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, values, null, null, null, null);

        // 如果有查詢結果
        while (result.moveToNext()) {
            // 讀取包裝一筆資料的物件
            gamePOs.add(getRecord(result));
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return gamePOs;
    }


    /**
     * @param teamList
     */
    public void makeGames(List<TeamPO> teamList) {

//        List<TeamPO> teamList = this.convertzer(teams);


        List<GamePO> pos = new ArrayList<>();

        for (int i = 1; i < teamList.size(); i++) {
            for (int j = i + 1; j <= teamList.size(); j++) {
                Log.i(TAG, String.format("%d vs %d", i, j));
                final GamePO po = new GamePO();

                final TeamPO teamA = teamList.get(i - 1);
                final TeamPO teamB = teamList.get(j - 1);


                po.setaName(teamA.getPlayer1() + "," + teamA.getPlayer2());
                po.setbName(teamB.getPlayer1() + "," + teamB.getPlayer2());
                po.setName(teamA.getTeamName() + " VS " + teamB.getTeamName());
                po.setaScore("0");
                po.setbScore("0");
                po.setStatus("");
                po.setaId(teamA.getId());
                po.setbId(teamB.getId());
                pos.add(po);
            }
        }


        insert(pos);

    }

    public GamePO getRecord(Cursor cursor) {
        final GamePO po = new GamePO();
        po.setId(cursor.getLong(0));
        po.setName(cursor.getString(1));
        po.setaName(cursor.getString(2));
        po.setbName(cursor.getString(3));
        po.setaScore(cursor.getString(4));
        po.setbScore(cursor.getString(5));
        po.setStatus(cursor.getString(6));
        po.setaId(cursor.getLong(7));
        po.setbId(cursor.getLong(8));


        return po;

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
