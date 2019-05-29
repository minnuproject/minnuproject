package com.example.hp.usercentricsimilaritysearch;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SimilarProducts extends AppCompatActivity {
    ListView l3;
    SharedPreferences sp;
    String url="",ip="";
    JSONParser parser=new JSONParser();
    ArrayList<String>category,itemname,descrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_products);
        l3=(ListView)findViewById(R.id.listview3);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sp.getString("ip","");
        try {
            url="http://"+sp.getString("ip","")+":5000/similarProd";
            List<NameValuePair> params=new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("uid", sp.getString("lid","0")));

            JSONArray ar=(JSONArray)parser.makeHttpRequest(url,"POST",params);
            category=new ArrayList<String>();
            itemname=new ArrayList<String>();
            descrip=new ArrayList<String>();



            for (int i=0;i<ar.length();i++)
            {
                JSONObject js=ar.getJSONObject(i);
                category.add(js.getString("category"));
                itemname.add(js.getString("item_name"));
                descrip.add(js.getString("description"));

            }
            l3.setAdapter(new Custom1(getApplicationContext(),category,itemname,descrip));
//            l1.setOnItemClickListener((AdapterView.OnItemClickListener) this);



        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();

        }


    }
}
