package com.example.hp.usercentricsimilaritysearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class ViewStockNotification extends AppCompatActivity {
    Spinner s1,s2;
    ListView l1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock_notification);
        s1=(Spinner)findViewById(R.id.spinner8);
        s2=(Spinner)findViewById(R.id.spinner9);
        l1=(ListView)findViewById(R.id.listview6);
        b1=(Button)findViewById(R.id.button11);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
