package com.example.hp.usercentricsimilaritysearch;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewOfferItem extends AppCompatActivity {
    EditText e1,e2,e3;
    ImageView img2;
    SharedPreferences sp;
    String url="",ip="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_offer_item);
        e1=(EditText)findViewById(R.id.editText8);
        e2=(EditText)findViewById(R.id.editText15);
        e3=(EditText)findViewById(R.id.editText14);
        img2=(ImageView)findViewById(R.id.imageView);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String price=getIntent().getStringExtra("price");
        String qty=getIntent().getStringExtra("qty");
        String description=getIntent().getStringExtra("description");
        String photo_1=getIntent().getStringExtra("photo_1");

        e1.setText(price);
        e1.setEnabled(false);
        e2.setText(qty);
        e2.setEnabled(false);
        e3.setText(description);
        e3.setEnabled(false);
        java.net.URL thumb_u;
        try {
            thumb_u = new java.net.URL("http://"+sp.getString("ip","")+":5000/static/products/"+ photo_1);
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            img2.setImageDrawable(thumb_d);


        }
        catch(Exception e){
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();

        }


    }
}
