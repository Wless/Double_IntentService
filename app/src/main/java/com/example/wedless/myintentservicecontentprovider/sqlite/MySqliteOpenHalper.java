package com.example.wedless.myintentservicecontentprovider.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wedless on 2017/11/21.
 */

public class MySqliteOpenHalper extends SQLiteOpenHelper {
    public MySqliteOpenHalper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user(id integer primary key autoincrement,account text,password text)");
        sqLiteDatabase.execSQL("CREATE TABLE goods(id integer primary key autoincrement,name text,price text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
