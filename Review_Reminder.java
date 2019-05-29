package com.example.hp.usercentricsimilaritysearch;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Review_Reminder extends AppCompatActivity {
    ListView l1;
    SharedPreferences sp;
    String url="",ip="";
    JSONParser parser=new JSONParser();
    ArrayList<String>category,itemname,review,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review__reminder);
        l1=(ListView)findViewById(R.id.listview7);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sp.getString("ip","");

        try {
            url="http://"+sp.getString("ip","")+":5000/reminder";
            List<NameValuePair> params=new ArrayList<NameValuePair>();

             params.add(new BasicNameValuePair("uid", sp.getString("lid","0")));



            JSONArray ar=(JSONArray)parser.makeHttpRequest(url,"POST",params);

            category=new ArrayList<String>();
            itemname=new ArrayList<String>();
            review=new ArrayList<String>();
            name=new ArrayList<String>();



            for (int i=0;i<ar.length();i++)
            {
                JSONObject js=ar.getJSONObject(i);

                Log.d("=====value=====",js.getString("review"));

                String r=js.getString("review");

                if(r=="null") {


                    category.add(js.getString("category"));
                    itemname.add(js.getString("item_name"));
                    review.add(js.getString("review"));
                    name.add(js.getString("name"));

                }


            }
            l1.setAdapter(new Custom2(getApplicationContext(),category,itemname));
//            l1.setOnItemClickListener((AdapterView.OnItemClickListener) this);




        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();

        }

    }
}
