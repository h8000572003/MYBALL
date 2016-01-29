package com.ball.andy.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ball.andy.myapplication.domain.DominKey;
import com.ball.andy.myapplication.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/28.
 */
public abstract class BaseDAO<T extends DominKey> {


    private SQLiteDatabase db;

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";


    public abstract String TABLE_NAME();

    public Convertz<T> convertz = null;


     public  BaseDAO(Convertz<T> convertz, Context context) {
        this.convertz = convertz;

        db = DBHelper.getDatabase(context);

    }


    /**
     * @param <T>
     */
    public interface Convertz<T extends  DominKey> {
        public abstract T getDomain(Cursor cursor);

        public abstract ContentValues getContentValues(T domainType);

    }


    public BaseDAO(Context context) {
        db = DBHelper.getDatabase(context);
    }

    public final void close() {
        db.close();
    }


    public final T insert(T domainType) {

        ContentValues cv = this.convertz.getContentValues(domainType);


        long id = db.insert(TABLE_NAME(), null, cv);
        domainType.setId(id);

        return domainType;
    }


    public final boolean update(T domainType) {

        ContentValues cv = this.convertz.getContentValues(domainType);


        return db.update(TABLE_NAME(), cv, "_id=? ", new String[]{domainType.getId() + ""}) > 0;
    }

    public final boolean delete(T domainType) {
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + domainType.getId();
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME(), where, null) > 0;
    }

    public final List<T> getAll() {
        List<T> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME(), null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(convertz.getDomain(cursor));
        }

        cursor.close();
        return result;
    }

    public final T get(long id) {
        // 準備回傳結果用的物件
        T item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME(), null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = convertz.getDomain(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    public final List<T> get(String where, String[] values) {
        // 準備回傳結果用的物件
        List<T> item = new ArrayList<>();
        // 使用編號為查詢條件

        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME(), null, where, values, null, null, null, null);

        // 如果有查詢結果
        while (result.moveToNext()) {
            // 讀取包裝一筆資料的物件
            item.add(convertz.getDomain(result))
            ;
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
