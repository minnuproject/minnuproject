package com.example.hp.usercentricsimilaritysearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MoreProductView extends AppCompatActivity {
    ImageView m1;
    TextView t2,t4,t6,t8;
    Button b1,b2,b3;
    SharedPreferences sp;
    String url="",ip="";
    String pid="";
    String video="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_product_view);
        t2=(TextView)findViewById(R.id.textView48);
        t4=(TextView)findViewById(R.id.textView42);
        t6=(TextView)findViewById(R.id.textView44);
//        t8=(TextView)findViewById(R.id.textView46);
        m1=(ImageView)findViewById(R.id.imageView4);
        b3=(Button)findViewById(R.id.button15);
        b2=(Button)findViewById(R.id.button13);


        b1=(Button)findViewById(R.id.button12);


        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         pid=getIntent().getStringExtra("pid");
        String name=getIntent().getStringExtra("name");
        String photo2=getIntent().getStringExtra("photo2");
        video=getIntent().getStringExtra("video");
        String qty=getIntent().getStringExtra("qty");
        String description=getIntent().getStringExtra("description");
//        String review=getIntent().getStringExtra("review");

        Toast.makeText(getApplicationContext(),"phto"+photo2,Toast.LENGTH_LONG).show();
        t2.setText(name);
        t4.setText(qty);
        t6.setText(description);
//        t8.setText(review);

        java.net.URL thumb_u;
        try {
            thumb_u = new java.net.URL("http://"+sp.getString("ip","")+":5000/static/products/"+ photo2);
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            m1.setImageDrawable(thumb_d);


        }
        catch(Exception e){

        }

         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i=new Intent(getApplicationContext(),Orders.class);
                 startActivity(i);
             }
         });
         b2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent a=new Intent(getApplicationContext(),Videos.class);
                 a.putExtra("video",video);
                 startActivity(a);
             }
         });
         b3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i=new Intent(getApplicationContext(),ReviewView.class);
                 i.putExtra("pid",pid);
                 startActivity(i);

             }
         });



}
}
