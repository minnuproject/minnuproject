package com.example.hp.usercentricsimilaritysearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
EditText e1,e2;
Button b1;
TextView t1;
SharedPreferences sp;
String url="",ip="";
    JSONParser parser=new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText3);
        t1=(TextView)findViewById(R.id.textView38);
        b1=(Button)findViewById(R.id.button);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ip="192.168.43.211";
        SharedPreferences.Editor ed=sp.edit();
        ed.putString("ip",ip);
        ed.commit();

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
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(getApplicationContext(),Customers_Home.class);
//                startActivity(i);
                String uname=e1.getText().toString();
                String pswd=e2.getText().toString();
                try
                {
                    url="http://"+ip+":5000/login";
                    Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();

                    List<NameValuePair> params=new ArrayList<>();
                    params.add(new BasicNameValuePair("username",uname));
                    params.add(new BasicNameValuePair("pwd",pswd));

                    JSONObject json=(JSONObject)parser.makeHttpRequest(url,"GET",params);
                    String res=json.getString("task");
                    if(res.equals("invalid"))
                    {
                        Toast.makeText(getApplicationContext(),"INVALID USERNAME OR PASSWORD",Toast.LENGTH_LONG).show();
                    }

                    else if(res.equals("blocked"))
                    {
                        Toast.makeText(getApplicationContext(),"YOU ARE TEMPORARY BLOCKED",Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        SharedPreferences.Editor ed=sp.edit();
                        ed.putString("lid",res);
                        ed.commit();
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();




                        startActivity(new Intent(getApplicationContext(),Customers_Home.class));
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();

                }

                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Intent i=new Intent(getApplicationContext(),registration.class);
                       startActivity(i);
                    }
                });

            }
        });


    }
}
