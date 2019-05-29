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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DebitCard extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1;
    EditText e1, e2, e3, e4, e5;
    Button b1;
    String bk;
    SharedPreferences sp;
    String url = "", ip = "";
    JSONParser parser = new JSONParser();
    public static ArrayList<String> Bank;
    String acc_name, acc_no, valid, ccv, amount, uid;

    String bank[] = {"Select", "Fedral Bank", "SBI", "Punjab National Bank", "Syndicate Bank", "UCO Bank"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_card);
        s1 = (Spinner) findViewById(R.id.spinner6);
        e1 = (EditText) findViewById(R.id.editText21);
        e2 = (EditText) findViewById(R.id.editText22);
        e3 = (EditText) findViewById(R.id.editText23);
        e4 = (EditText) findViewById(R.id.editText25);
        e5 = (EditText) findViewById(R.id.editText26);
        b1 = (Button) findViewById(R.id.button9);


        e5.setText(Orders.total + "");
        e5.setEnabled(false);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sp.getString("ip", "");

        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, bank);
        s1.setAdapter(ad);

        s1.setOnItemSelectedListener(this);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(getApplicationContext(),Customers_Home.class);
//                startActivity(i);
                acc_name = e1.getText().toString();
                acc_no = e2.getText().toString();
                valid = e3.getText().toString();
                ccv = e4.getText().toString();
                amount = e5.getText().toString();

                try {

                    url="http://"+ip+":5000/payment";
                    List<NameValuePair> params=new ArrayList<>();
                    params.add(new BasicNameValuePair("acc_name",acc_name ));
                    params.add(new BasicNameValuePair("acc_no",acc_no ));
                    params.add(new BasicNameValuePair("valid",valid ));
                    params.add(new BasicNameValuePair("ccv",ccv  ));
                    params.add(new BasicNameValuePair("amount",amount ));
                    params.add(new BasicNameValuePair("uid",sp.getString("lid","") ));
                    params.add(new BasicNameValuePair("pid",ProductView.pid.get(ProductView.pos) ));
                    params.add(new BasicNameValuePair("qty", Orders.qty+""));

                    JSONObject json=(JSONObject)parser.makeHttpRequest(url,"GET",params);

                    String res=json.getString("result");


                    if(res.equalsIgnoreCase("invalid"))
                    {
                        Toast.makeText(getApplicationContext(),"You provide a wrong details...!",Toast.LENGTH_LONG).show();

                    }
                    else if(res.equalsIgnoreCase("no"))
                    {
                        Toast.makeText(getApplicationContext(),"Insufficient balance....!",Toast.LENGTH_LONG).show();
                    }


                    else if(res.equalsIgnoreCase("ok"))
                    {
                        Toast.makeText(getApplicationContext(),"Transaction Successfull",Toast.LENGTH_LONG).show();

                        Toast.makeText(getApplicationContext(),"Your order received. Thank you, Have a nice day..",Toast.LENGTH_LONG).show();

                        Intent e=new Intent(getApplicationContext(),Customers_Home.class);
                        startActivity(e);
                    }

                }
                catch (JSONException e)
                {
                    Toast.makeText(getApplicationContext(),"Error"+e.getMessage()+"",Toast.LENGTH_LONG).show();
                }


            }


        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String bk=bank[i];

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


