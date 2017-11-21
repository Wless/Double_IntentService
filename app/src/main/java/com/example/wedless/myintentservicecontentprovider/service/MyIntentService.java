package com.example.wedless.myintentservicecontentprovider.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.wedless.myintentservicecontentprovider.bean.Goods;
import com.example.wedless.myintentservicecontentprovider.bean.User;
import com.example.wedless.myintentservicecontentprovider.util.MyLog;

import java.util.ArrayList;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private User user=new User();
    private ArrayList<Goods> lists=new ArrayList<>();
    private MyBinder mybinder;
    private static final String ACTION_FOO = "com.example.wedless.myintentservicecontentprovider.service.action.FOO";
    private static final String ACTION_BAZ = "com.example.wedless.myintentservicecontentprovider.service.action.BAZ";
    public static final String ACTION_LOGIN = "com.example.wedless.myintentservicecontentprovider.service.action.LOGIN";
    public static final String ACTION_REGISTER = "com.example.wedless.myintentservicecontentprovider.service.action.REGISTER";
    public static final String ACTION_QUERY = "com.example.wedless.myintentservicecontentprovider.service.action.QUERY";


    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.wedless.myintentservicecontentprovider.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.wedless.myintentservicecontentprovider.service.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }
    public class MyBinder extends Binder{
        public User getUser(){
            return user;
        }
        public ArrayList<Goods> getLists(){
            return lists;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mybinder=new MyBinder();
        return mybinder;
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            } else if (ACTION_LOGIN.equals(action)){
                Cursor cursor=getContentResolver().query(Uri.parse("content://com.bw.example.intent.demo/user"),null,"account="+intent.getStringExtra("account")+" and password="+intent.getStringExtra("password"),null,null);
                    while (cursor.moveToNext()) {
                        user.setAccount(cursor.getString(cursor.getColumnIndex("account")));
                        user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                }
            }else if(ACTION_REGISTER.equals(action)){
                ContentValues value=new  ContentValues();
                value.put("account",intent.getStringExtra("account"));
                value.put("password",intent.getStringExtra("password"));
                getContentResolver().insert(Uri.parse("content://com.bw.example.intent.demo/user"),value);
            }else if(ACTION_QUERY.equals(action)){
                Cursor cursor=getContentResolver().query(Uri.parse("content://com.bw.example.intent.demo/goods"),null,null,null,null);
                while (cursor.moveToNext()) {
                    Goods g=new Goods();
                    g.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    g.setName(cursor.getString(cursor.getColumnIndex("name")));
                    g.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                    lists.add(g);
                }
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
