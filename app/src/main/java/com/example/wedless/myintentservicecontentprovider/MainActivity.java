package com.example.wedless.myintentservicecontentprovider;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wedless.myintentservicecontentprovider.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fm;
    private FragmentTransaction trans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm=getSupportFragmentManager();
        trans=fm.beginTransaction();
        trans.replace(R.id.main_id,new LoginFragment()).commit();
    }
}
