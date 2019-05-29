package com.example.hp.usercentricsimilaritysearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Orders extends AppCompatActivity implements TextWatcher {
    EditText e1,e2,e3;
    Button b2;
    SharedPreferences sp;
    String url="",ip="";
    JSONParser parser=new JSONParser();

    public static int qty=1;
   public static float total= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        e1=(EditText)findViewById(R.id.editText16);
        e2=(EditText)findViewById(R.id.editText17);
        e3=(EditText)findViewById(R.id.editText18);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sp.getString("ip","");


        e1.setText(ProductView.itemname.get(ProductView.pos));
        e1.setEnabled(false);

        e2.setText("1");
        e3.setText(ProductView.price.get(ProductView.pos));

        e3.setEnabled(false);

        total = qty * Integer.parseInt(e3.getText().toString());

        e2.addTextChangedListener(Orders.this);



        b2=(Button)findViewById(R.id.button6);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Payment.class);
                startActivity(i);
            }
        });



    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


    }

    @Override
    public void afterTextChanged(Editable editable) {


        if (!e2.getText().toString().equalsIgnoreCase("")) {

            qty = Integer.parseInt(e2.getText().toString());
            total = qty * Integer.parseInt(e3.getText().toString());

            e3.setText(total+"");

        }

    }

}
