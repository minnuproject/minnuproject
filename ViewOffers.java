package com.example.hp.usercentricsimilaritysearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ViewOffers extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    Spinner s1,s2;
    ListView l1;
    Button b1;
    SharedPreferences sp;
    String url="",ip="";
    JSONParser parser=new JSONParser();
    String itemname;
    ArrayList<String>pid,pname,offers,from_date,period,oldprice,price,qty,descrip,photo;
    String cat[]={"Select","mobile","tab"};
    String catg,product_id;
    String url1="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_offers);
        s1=(Spinner)findViewById(R.id.spinner2);
        s2=(Spinner)findViewById(R.id.spinner3);
        l1=(ListView)findViewById(R.id.listview4);
        b1=(Button)findViewById(R.id.button4);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sp.getString("ip","");
        try
        {
            if(android.os.Build.VERSION.SDK_INT>9)
            {
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

        }
        catch(Exception e)
        {

        }

        ArrayAdapter<String> ad= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,cat);
        s1.setAdapter(ad);
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String catg=s1.getSelectedItem().toString();
                String pname=s2.getSelectedItem().toString();
                try {
                    url = "http://" + sp.getString("ip", "") + ":5000/offersview";

                    List<NameValuePair> params = new ArrayList<NameValuePair>();

                    params.add(new BasicNameValuePair("category", catg));
                    params.add(new BasicNameValuePair("pid", itemname));

//                    Toast.makeText(getApplicationContext(),itemname+" haiiiiiiiii",Toast.LENGTH_LONG).show();

                    final JSONArray ar = (JSONArray) parser.makeHttpRequest(url, "GET", params);


                    pid = new ArrayList<String>();
//                  pname = new ArrayList<>();
                    offers=new ArrayList<String>();
                    period=new ArrayList<String>();
//                    to_date=new ArrayList<String>();
                    oldprice=new ArrayList<String>();
                    price=new ArrayList<String>();
                    qty=new ArrayList<String>();
                    descrip=new ArrayList<String>();
                    photo=new ArrayList<String>();





                    for (int io = 0; io < ar.length(); io++) {
                        JSONObject js = ar.getJSONObject(io);
                       pid.add(js.getString("pid"));
//                        pname.add(js.getString("item_name"));
                        offers.add(js.getString("offers"));
                        period.add(js.getString("from_date")+" to"+(js.getString("to_date")));
//                        to_date.add(js.getString("to_date"));
                        oldprice.add(js.getString("old_price"));
                        price.add(js.getString("price"));
                        qty.add(js.getString("qty"));
                        descrip.add(js.getString("description"));
                        photo.add(js.getString("photo_1"));


                    }
                    l1.setAdapter(new Custom1(getApplicationContext(),offers,period,oldprice));
                    l1.setOnItemClickListener(ViewOffers.this);

                    }
                catch (JSONException e)
                {

                }



            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView==s1) {

            catg=cat[i];

            try {
                url1 = "http://" + sp.getString("ip", "") + ":5000/rev";

                List<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("category", catg));

                final JSONArray ar = (JSONArray) parser.makeHttpRequest(url1, "GET", params);


                pid = new ArrayList<String>();

                pname = new ArrayList<String>();


                for (int io = 0; io< ar.length(); io++) {
                    JSONObject js = ar.getJSONObject(io);
                    pid.add(js.getString("pid"));
                    pname.add(js.getString("item_name"));


                }

                ArrayAdapter<String> da=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,pname);
                s2.setAdapter(da);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        else if(adapterView==s2)
        {
            itemname=pid.get(i);
//            Toast.makeText(getApplicationContext(),itemname,Toast.LENGTH_LONG).show();


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent a=new Intent(getApplicationContext(),ViewOfferItem.class);
        a.putExtra("price",price.get(i));
        a.putExtra("qty",qty.get(i));
        a.putExtra("description",descrip.get(i));
        a.putExtra("photo_1",photo.get(i));

        startActivity(a);

    }
}
