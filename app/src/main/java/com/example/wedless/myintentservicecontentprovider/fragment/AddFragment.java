package com.example.wedless.myintentservicecontentprovider.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.wedless.myintentservicecontentprovider.R;
import com.example.wedless.myintentservicecontentprovider.bean.Goods;
import com.example.wedless.myintentservicecontentprovider.service.MyIntentService;
import com.example.wedless.myintentservicecontentprovider.sqlite.MySqliteOpenHalper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment implements View.OnClickListener {
    private MySqliteOpenHalper mySqliteOpenHalper;
    private SQLiteDatabase db;
    private ListView listshow;
    private FragmentManager fm;
    private FragmentTransaction trans;
    private MyIntentService.MyBinder myBinder;
    private MyServiceConnection conn;
    private Intent intent;
    private ArrayList<Goods> lists=new ArrayList<>();
    private EditText add_name;
    private EditText add_price;
    private Button btn_add;
    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view=inflater.inflate(R.layout.fragment_add,null);
        btn_add=view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        intent=new Intent(getActivity(), MyIntentService.class);
        intent.setAction(MyIntentService.ACTION_QUERY);
        getActivity().startService(intent);
        conn=new MyServiceConnection();
        getActivity().bindService(intent,conn, Context.BIND_AUTO_CREATE);
        return view;
    }
    public class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder= (MyIntentService.MyBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:

                break;
        }
    }
}
