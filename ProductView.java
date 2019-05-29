package com.example.hp.usercentricsimilaritysearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class ProductView extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Spinner s1;
    ListView l1;
    Button b1;
    SharedPreferences sp;
    String url="",ip="";
    JSONParser parser=new JSONParser();
    public static ArrayList<String> pid,photo,itemname,price,photo2,video,qty,descrip,review;
    public static int pos=0;

    String cat[]={"Select","mobile","tab"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        s1=(Spinner)findViewById(R.id.spinner);
        l1=(ListView)findViewById(R.id.listview1);
        b1=(Button)findViewById(R.id.button3);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sp.getString("ip","");

        ArrayAdapter<String> ad= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,cat);
        s1.setAdapter(ad);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String catg=s1.getSelectedItem().toString();
                try {
                    url="http://"+sp.getString("ip","")+":5000/productview";
                    List<NameValuePair> params=new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("category",catg));

                    JSONArray ar=(JSONArray)parser.makeHttpRequest(url,"GET",params);

                    pid=new ArrayList<String>();
                    photo=new ArrayList<String>();
                    itemname=new ArrayList<String>();
                    price=new ArrayList<String>();
                    photo2=new ArrayList<String>();
                    video=new ArrayList<String>();
                    qty=new ArrayList<String>();
                    descrip=new ArrayList<String>();
//                    review=new ArrayList<String>();


                    for (int i=0;i<ar.length();i++)
                    {
                        JSONObject js=ar.getJSONObject(i);
                        pid.add(js.getString("pid"));
                        photo.add(js.getString("photo_1"));
                        itemname.add(js.getString("item_name"));
                        price.add(js.getString("price"));
                        photo2.add(js.getString("photo_2"));
                        video.add(js.getString("video"));
                        qty.add(js.getString("qty"));
                        descrip.add(js.getString("description"));
//                        review.add(js.getString("review"));


                    }
                    l1.setAdapter(new Custom(getApplicationContext(),photo,itemname,price));
                    l1.setOnItemClickListener(ProductView.this);




                }
                catch (Exception e)
                {

                }

            }
        });




    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        pos=i;
        Intent a=new Intent(getApplicationContext(),MoreProductView.class);
        a.putExtra("pid",pid.get(i));
        a.putExtra("name",itemname.get(i));
        a.putExtra("photo2",photo2.get(i));
        a.putExtra("video",video.get(i));
        a.putExtra("qty",qty.get(i));
        a.putExtra("description",descrip.get(i));


        startActivity(a);

    }
}
