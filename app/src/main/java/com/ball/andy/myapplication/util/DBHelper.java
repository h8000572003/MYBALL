package com.ball.andy.myapplication.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andy on 2016/1/20.
 */
public class DBHelper extends SQLiteOpenHelper {

    // 資料庫名稱
    public static final String DATABASE_NAME = "mydata.db";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 5;
    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;

    // 建構子，在一般的應用都不需要修改
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new DBHelper(context, DATABASE_NAME,
                    null, VERSION).getWritableDatabase();
        }

        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("CREATE TABLE player (");
        buffer.append("_id integer PRIMARY KEY NOT NULL,");
        buffer.append("name text(128));");
        db.execSQL(buffer.toString());

        buffer = new StringBuffer();
        buffer.append(" CREATE TABLE game (");
        buffer.append("         _id integer PRIMARY KEY AUTOINCREMENT NOT NULL,");
        buffer.append("         name char(128),");
        buffer.append("        aName text(128),");
        buffer.append("        bName text(128),");
        buffer.append("        aScore text(128),");
        buffer.append("        bScore text(128),");
        buffer.append("        status char(1),");
        buffer.append("        a_id integer(128),");
        buffer.append("        b_id integer(128)");
        buffer.append(");");
        db.execSQL(buffer.toString());


        buffer = new StringBuffer();
        buffer.append(" CREATE TABLE IF NOT EXISTS team (");
        buffer.append("         _id integer PRIMARY KEY NOT NULL,");
        buffer.append("         teamName text(128) DEFAULT(''),");
        buffer.append("         player1 text(128) DEFAULT(''),");
        buffer.append("         player2 text(128) DEFAULT(' ')");
        buffer.append(" );");
        db.execSQL(buffer.toString());

        // 建立應用程式需要的表格
        // 待會再回來完成它
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 刪除原有的表格
        // 待會再回來完成它


        db.execSQL("DROP TABLE IF EXISTS player");
        db.execSQL("DROP TABLE IF EXISTS game");
        db.execSQL("DROP TABLE IF EXISTS team");
        // 呼叫onCreate建立新版的表格
        onCreate(db);
    }
}
