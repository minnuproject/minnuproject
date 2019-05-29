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

public class ReviewView extends AppCompatActivity {
     ListView l1;
    SharedPreferences sp;
    String urr="",ip="",pidd="";
    JSONParser parser=new JSONParser();
    ArrayList<String> name,review;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_view);
        l1=(ListView)findViewById(R.id.listview8);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sp.getString("ip","");
        urr="http://"+sp.getString("ip","")+":5000/viewReviewss";

        pidd=getIntent().getStringExtra("pid");
        try {

            List<NameValuePair> params=new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("pid",pidd));

            JSONArray ar=(JSONArray)parser.makeHttpRequest(urr,"GET",params);

            name=new ArrayList<String>();
            review=new ArrayList<String>();

            for (int i=0;i<ar.length();i++)
            {
                JSONObject js=ar.getJSONObject(i);

                name.add(js.getString("name"));
                review.add(js.getString("review"));

            }
            l1.setAdapter(new Custom2(getApplicationContext(),name,review));
//
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"error"+e,Toast.LENGTH_SHORT).show();

        }

    }
}
