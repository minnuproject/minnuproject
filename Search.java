package com.example.hp.usercentricsimilaritysearch;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    Spinner s1;
    ListView l1;
    Button b1;
    SharedPreferences sp;
    String url="",ip="";
    JSONParser parser=new JSONParser();
    ArrayList<String> rank,itemname,photo,descrip,rank_score;
    int m=0;

    String cat[]={"Select","mobile","tab"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        s1=(Spinner)findViewById(R.id.spinner7);
        l1=(ListView)findViewById(R.id.listview2);
        b1=(Button)findViewById(R.id.button14);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sp.getString("ip","");

        ArrayAdapter<String> ad= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,cat);
        s1.setAdapter(ad);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String catg=s1.getSelectedItem().toString();
                try {
                    url="http://"+sp.getString("ip","")+":5000/ranking";
                    List<NameValuePair> params=new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("category",catg));

                    JSONArray ar=(JSONArray)parser.makeHttpRequest(url,"GET",params);

                    rank=new ArrayList<String>();
                    itemname=new ArrayList<String>();
                    photo=new ArrayList<String>();
                    descrip=new ArrayList<String>();
                    rank_score=new ArrayList<String>();
                    m=0;



                    for (int i=0;i<ar.length();i++)
                    {

                        JSONObject js=ar.getJSONObject(i);
                        rank.add(js.getString("ranking"));
                        itemname.add(js.getString("item_name"));
                        photo.add(js.getString("photo_1"));
                        descrip.add(js.getString("description"));
                        m=m+1;
                        rank_score.add(Integer.toString(m));


                        l1.setAdapter(new Custom4(getApplicationContext(),rank_score,itemname,photo,descrip));


                    }



                }
                catch (Exception e)
                {

                }
            }
        });

    }
}
