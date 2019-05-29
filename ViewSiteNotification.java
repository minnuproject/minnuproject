package com.example.hp.usercentricsimilaritysearch;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewSiteNotification extends AppCompatActivity {
    ListView l1;
    Button b1;
    SharedPreferences sp;
    String url="",ip="";
    JSONParser parser=new JSONParser();
    ArrayList<String>message,date;
//    public static int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_site_notification);
        l1=(ListView)findViewById(R.id.listview5);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sp.getString("ip","");

        try {
            url="http://"+sp.getString("ip","")+":5000/sitenoti";
            List<NameValuePair> params=new ArrayList<NameValuePair>();

            JSONArray ar=(JSONArray)parser.makeHttpRequest(url,"POST",params);


            message=new ArrayList<String>();
            date=new ArrayList<String>();



            for (int i=0;i<ar.length();i++)
            {
                JSONObject js=ar.getJSONObject(i);
                message.add(js.getString("message"));
                date.add(js.getString("date"));

            }
            l1.setAdapter(new Custom2(getApplicationContext(),message,date));
//            l1.setOnItemClickListener((AdapterView.OnItemClickListener) this);



        }
        catch (Exception e)
        {
           Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();

        }


    }
}
