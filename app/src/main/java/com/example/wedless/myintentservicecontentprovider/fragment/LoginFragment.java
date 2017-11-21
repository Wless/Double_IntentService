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
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wedless.myintentservicecontentprovider.R;
import com.example.wedless.myintentservicecontentprovider.bean.User;
import com.example.wedless.myintentservicecontentprovider.service.MyIntentService;
import com.example.wedless.myintentservicecontentprovider.util.MyLog;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    private EditText login_account;
    private EditText login_password;
    private Button login_login;
    private Button login_register;
    private Intent intent;
    private User user=new User();
    private Handler handler;
    private MyIntentService.MyBinder myBinder;
    private MyServiceConnection conn;
    private FragmentManager fm;
    private FragmentTransaction trans;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view=inflater.inflate(R.layout.fragment_login,null);
        login_account=view.findViewById(R.id.login_account);
        login_password=view.findViewById(R.id.login_password);
        login_login=view.findViewById(R.id.login_login);
        login_register=view.findViewById(R.id.login_register);
        login_login.setOnClickListener(this);
        login_register.setOnClickListener(this);
        return view;
    }

    public class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder= (MyIntentService.MyBinder) iBinder;
            user=myBinder.getUser();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_login:
                intent=new Intent(getContext(),MyIntentService.class);
                intent.setAction(MyIntentService.ACTION_LOGIN);
                intent.putExtra("account",login_account.getText().toString());
                intent.putExtra("password",login_password.getText().toString());
                getActivity().startService(intent);
                conn=new MyServiceConnection();
                getActivity().bindService(intent,conn, Context.BIND_AUTO_CREATE);
                handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(user.getPassword()!=null){
                            Toast.makeText(getActivity(), "登陆成功!", Toast.LENGTH_SHORT).show();
                            fm=getActivity().getSupportFragmentManager();
                            trans=fm.beginTransaction();
                            trans.replace(R.id.main_id,new com.example.wedless.myintentservicecontentprovider.fragment.ListFragment());
                            trans.commit();
                        }else{
                            Toast.makeText(getActivity(), "用户名或密码不存在!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },1000);
                break;
            case R.id.login_register:
                fm=getActivity().getSupportFragmentManager();
                trans=fm.beginTransaction();
                trans.replace(R.id.main_id,new RegisterFragment()).commit();
                break;
        }
    }
}
