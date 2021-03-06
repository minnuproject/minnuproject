package com.example.hp.usercentricsimilaritysearch;

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

import java.util.ArrayList;

public class Custom5 extends BaseAdapter {

    private android.content.Context Context;
    ArrayList<String> a,b,c,d,e;




    SharedPreferences sp;

    public Custom5(android.content.Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c,ArrayList<String> d,ArrayList<String> e  ) {
        this.Context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.e=e;




        sp=PreferenceManager.getDefaultSharedPreferences(applicationContext);
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
            gridView=inflator.inflate(R.layout.activity_custom5, null);

        }
        else
        {
            gridView=(View)convertView;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView54);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView56);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView57);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView58);
        ImageView img=(ImageView)gridView.findViewById(R.id.imageView6);







        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);




        tv1.setText(a.get(position));
        tv2.setText(b.get(position));
        tv3.setText(c.get(position));
        tv4.setText(d.get(position));



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

