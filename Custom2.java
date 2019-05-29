package com.example.hp.usercentricsimilaritysearch;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.usercentricsimilaritysearch.R;

import java.util.ArrayList;

public class Custom2 extends BaseAdapter {

    private android.content.Context Context;
    ArrayList<String> a,b;

    public Custom2(Context applicationContext, ArrayList<String> a, ArrayList<String> b) {
        this.Context=applicationContext;
        this.a=a;
        this.b=b;

    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return a.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertView==null)
        {
            gridView=new View(Context);
            gridView=inflator.inflate(R.layout.custom2, null);

        }
        else
        {
            gridView=(View)convertView;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView34);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView18);




        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);



        tv1.setText(a.get(position));
        tv2.setText(b.get(position));



        return gridView;

    }



}
