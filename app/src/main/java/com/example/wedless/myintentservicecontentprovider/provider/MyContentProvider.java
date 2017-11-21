package com.example.wedless.myintentservicecontentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.wedless.myintentservicecontentprovider.sqlite.MySqliteOpenHalper;

import java.util.regex.Matcher;

public class MyContentProvider extends ContentProvider {
    private static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
    private MySqliteOpenHalper mySqliteOpenHalper;
    private SQLiteDatabase db;
    private Cursor cursor;
    public static final int USER_NUM=1;
    public static final int GOODS_NUM=2;
    public MyContentProvider() {

    }
    static {
        matcher.addURI("com.bw.example.intent.demo","user",USER_NUM);
        matcher.addURI("com.bw.example.intent.demo","goods",GOODS_NUM);
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        db.insert("user",null,values);
        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        mySqliteOpenHalper=new MySqliteOpenHalper(getContext(),"db_test",null,1);
        db=mySqliteOpenHalper.getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        switch (matcher.match(uri)){
            case 1:
                cursor= db.query("user",null,selection,null,null,null,null);
            break;
            case 2:
                cursor= db.query("goods",null,selection,null,null,null,null);
            break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
