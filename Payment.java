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
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Payment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1;
    SharedPreferences sp;
    String url="",ip="";
    JSONParser parser=new JSONParser();
    public static ArrayList<String>Payment;
    Button b1;

    String type[]={"Select","cashOnDelivery","DebitCard"};

    String ty="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        s1=(Spinner)findViewById(R.id.spinner5);
        b1=(Button)findViewById(R.id.button1);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sp.getString("ip","");

        ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,type);
        s1.setAdapter(ad);

        s1.setOnItemSelectedListener(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ty.equalsIgnoreCase("cashOnDelivery"))
                {

                    try {

                        url="http://"+ip+":5000/cashondelivery";
                        List<NameValuePair> params=new ArrayList<>();

                        params.add(new BasicNameValuePair("amount",Orders.total+""));
                        params.add(new BasicNameValuePair("pid",ProductView.pid.get(ProductView.pos) ));
                        params.add(new BasicNameValuePair("qty", Orders.qty+""));
                        params.add(new BasicNameValuePair("uid",sp.getString("lid","") ));

                        JSONObject json=(JSONObject)parser.makeHttpRequest(url,"GET",params);

                        String res=json.getString("result");

                        if(res.equalsIgnoreCase("ok"))
                        {

                            Toast.makeText(getApplicationContext(),"Your order received. Thank you, Have a nice day..",Toast.LENGTH_LONG).show();

                            Intent e=new Intent(getApplicationContext(),Customers_Home.class);
                            startActivity(e);

                        }

                    }
                    catch (JSONException e)
                    {

                        Toast.makeText(getApplicationContext(),"Error "+e.getMessage()+"",Toast.LENGTH_LONG).show();

                    }


                }
                else if(ty.equalsIgnoreCase("DebitCard"))
                {
                    Intent a=new Intent(getApplicationContext(),DebitCard.class);
                    startActivity(a);
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        ty=type[i];


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
