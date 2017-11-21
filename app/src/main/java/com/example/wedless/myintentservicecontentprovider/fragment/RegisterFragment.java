package com.example.wedless.myintentservicecontentprovider.fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wedless.myintentservicecontentprovider.R;
import com.example.wedless.myintentservicecontentprovider.bean.User;
import com.example.wedless.myintentservicecontentprovider.service.MyIntentService;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText register_account;
    private EditText register_password;
    private Button register_register;
    private Intent intent;
    private User user;
    private Handler handler;
    private MyIntentService.MyBinder myBinder;
    private LoginFragment.MyServiceConnection conn;
    private FragmentManager fm;
    private FragmentTransaction trans;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_register:
                Intent intent=new Intent(getContext(),MyIntentService.class);
                intent.putExtra("account",register_account.getText().toString());
                intent.putExtra("password",register_password.getText().toString());
                intent.setAction(MyIntentService.ACTION_REGISTER);
                getActivity().startService(intent);
                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fm=getActivity().getSupportFragmentManager();
                        trans=fm.beginTransaction();
                        trans.replace(R.id.main_id,new LoginFragment()).commit();
                    }
                },1000);
                break;
        }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view=inflater.inflate(R.layout.fragment_register,null);
        register_account=view.findViewById(R.id.register_account);
        register_password=view.findViewById(R.id.register_password);
        register_register=view.findViewById(R.id.register_register);
        register_register.setOnClickListener(this);
        return view;
    }

}