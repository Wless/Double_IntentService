package com.example.wedless.myintentservicecontentprovider.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.wedless.myintentservicecontentprovider.R;
import com.example.wedless.myintentservicecontentprovider.bean.Goods;
import com.example.wedless.myintentservicecontentprovider.service.MyIntentService;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment implements View.OnClickListener {
    private EditText message_name;
    private EditText message_price;
    private Button btn_delete;
    private Button btn_update;
    private Goods goods=new Goods();
    private FragmentManager fm;
    private FragmentTransaction trans;
    private int id;
    private MyIntentService.MyBinder myBinder;
    private MyServiceConnection conn;
    private Handler handler;
    private Intent intent;
    public static MessageFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("id",id);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_message,null);
        message_name=view.findViewById(R.id.message_name);
        message_price=view.findViewById(R.id.message_price);
        btn_delete=view.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_update=view.findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
        Bundle b=getArguments();
        id=b.getInt("id");
        intent=new Intent(getActivity(),MyIntentService.class);
        intent.putExtra("id",id);
        intent.setAction(MyIntentService.ACTION_QUERYBYID);
        getActivity().startService(intent);
        conn=new MyServiceConnection();
        getActivity().bindService(intent,conn, Context.BIND_AUTO_CREATE);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                message_name.setText(goods.getName().toString());
                message_price.setText(goods.getPrice().toString());
            }
        },500);
        return view;
    }
    public class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder= (MyIntentService.MyBinder) iBinder;
            goods=myBinder.getGoods();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_delete:
                intent=new Intent(getActivity(),MyIntentService.class);
                intent.putExtra("id",id);
                intent.setAction(MyIntentService.ACTION_DELETE);
                getActivity().startService(intent);
                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fm=getActivity().getSupportFragmentManager();
                        trans=fm.beginTransaction();
                        trans.replace(R.id.main_id,new ListFragment());
                        trans.commit();
                    }
                },500);
                break;
            case R.id.btn_update:
                intent=new Intent(getActivity(),MyIntentService.class);
                intent.putExtra("id",id);
                intent.putExtra("name",message_name.getText().toString());
                intent.putExtra("price",message_price.getText().toString());
                intent.setAction(MyIntentService.ACTION_UPDATE);
                getActivity().startService(intent);
                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fm=getActivity().getSupportFragmentManager();
                        trans=fm.beginTransaction();
                        trans.replace(R.id.main_id,new ListFragment());
                        trans.commit();
                    }
                },500);
                break;

        }

    }
}
