package com.example.hp.usercentricsimilaritysearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class registration extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    RadioButton r1,r2;
    Button b1;
    SharedPreferences sp;
    String url="",ip="";
    JSONParser parser=new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText5);
        e3=(EditText)findViewById(R.id.editText6);
        e4=(EditText)findViewById(R.id.editText7);
        e5=(EditText)findViewById(R.id.editText4);
        e6=(EditText)findViewById(R.id.editText9);
        e7=(EditText)findViewById(R.id.editText10);
        e8=(EditText)findViewById(R.id.editText11);
        e9=(EditText)findViewById(R.id.editText12);
        r1=(RadioButton)findViewById(R.id.radioButton2);
        r2=(RadioButton)findViewById(R.id.radioButton3);
        b1=(Button)findViewById(R.id.button2);

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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e1.getText().toString();
                String hname=e2.getText().toString();
                String street=e3.getText().toString();
                String state=e4.getText().toString();
                String country=e5.getText().toString();
                String pincode=e6.getText().toString();
                String email=e7.getText().toString();
                String mobile=e8.getText().toString();
                String pswd=e9.getText().toString();
                String gender="";
                if(r1.isChecked())
                {
                    gender=r1.getText().toString();
                }
                else
                {
                    gender=r2.getText().toString();
                }

                if(name.equals(""))
                {
                    e1.setError("Enter Name");
                }
                if(hname.equals(""))
                {
                    e2.setError("Enter Housename");
                }
                if(street.equals(""))
                {
                    e3.setError("Enter Street");
                }
                if(state.equals(""))
                {
                    e4.setError("Enter State");
                }
                if(country.equals(""))
                {
                    e5.setError("Enter Country name");
                }
                if(pincode.length()!=6)
                {
                     e6.setError("Enter a valid pincode");
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    e7.setError("Enter a valid email");
                }
                if(mobile.length()!=10)
                {
                    e8.setError("Enter a valid mobile");
                }
                if(pswd.length()<6)
                {
                    e9.setError("Atleast 6 characters");
                }

                try
                {
                    url="http://"+ip+":5000/Cust_reg";
                    Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();

                    List<NameValuePair> params=new ArrayList<>();
                    params.add(new BasicNameValuePair("name",name));
                    params.add(new BasicNameValuePair("gender",gender));
                    params.add(new BasicNameValuePair("hname",hname));
                    params.add(new BasicNameValuePair("street",street));
                    params.add(new BasicNameValuePair("state",state));
                    params.add(new BasicNameValuePair("country",country));
                    params.add(new BasicNameValuePair("pincode",pincode));
                    params.add(new BasicNameValuePair("email",email));
                    params.add(new BasicNameValuePair("mob",mobile));
                    params.add(new BasicNameValuePair("pswd",pswd));

                    JSONObject json=(JSONObject)parser.makeHttpRequest(url,"GET",params);
                    String res=json.getString("task");
                    if(res.equals("invalid"))
                    {
                        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

                        startActivity(new Intent(getApplicationContext(),Login.class));
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}
