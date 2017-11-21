package com.example.wedless.myintentservicecontentprovider.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wedless.myintentservicecontentprovider.R;
import com.example.wedless.myintentservicecontentprovider.bean.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wedless on 2017/11/21.
 */

public class MyAdapter extends ArrayAdapter {
    private Context mContext;
    private int mResource;
    private ArrayList<Goods> lists;
    public MyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
        lists=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        view=layoutInflater.inflate(R.layout.list_view_item,null);
        TextView list_name=view.findViewById(R.id.list_name);
        TextView list_price=view.findViewById(R.id.list_price);
        list_name.setText(lists.get(position).getName());
        list_price.setText(lists.get(position).getPrice());
        return view;
    }
}
