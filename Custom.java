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

public class Custom extends BaseAdapter {

    private android.content.Context Context;
    ArrayList<String> a,b,c;




    SharedPreferences sp;

    public Custom(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c  ) {
        this.Context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;


        sp=PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    public Custom(android.content.Context applicationContext, ArrayList<String> message, ArrayList<String> date) {
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
            gridView=inflator.inflate(R.layout.activity_custom, null);

        }
        else
        {
            gridView=(View)convertView;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView39);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView40);
        ImageView img=(ImageView)gridView.findViewById(R.id.imageView3);





        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);



        tv1.setText(b.get(position));
        tv2.setText(c.get(position));



        //String urll="http://192.168.1.3//pinkpolice//josn//pic//"+c[position];
        //Toast.makeText(Context, urll, Toast.LENGTH_LONG).show();
        java.net.URL thumb_u;
        try {
            thumb_u = new java.net.URL("http://"+sp.getString("ip","")+":5000/static/products/"+ a.get(position));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            img.setImageDrawable(thumb_d);


        }
        catch(Exception e){

        }

        return gridView;

    }



}
