package com.example.hp.usercentricsimilaritysearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Review extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    Spinner s1,s2;
    EditText e1;
    ImageView img1;
    RatingBar r1;
    Button b1,b2;
    SharedPreferences sp;
    String url="",ip="";
    JSONParser parser=new JSONParser();
    private static final int FILE_SELECT_CODE = 0;
    String path,fileName,itemname,review;
        float rating;
     ArrayList<String>pid,pname;
    String cat[]={"Select","mobile","tab"};
    String categ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        s1=(Spinner)findViewById(R.id.spinner4);
        s2=(Spinner)findViewById(R.id.spinner10);
        e1=(EditText)findViewById(R.id.editText19);
        img1=(ImageView)findViewById(R.id.imageView2);
        r1=(RatingBar)findViewById(R.id.ratingBar);
        b1=(Button)findViewById(R.id.button7);
        b2=(Button)findViewById(R.id.button8);

        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sp.getString("ip","");


        ArrayAdapter<String> ad= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,cat);
        s1.setAdapter(ad);
        s1.setOnItemSelectedListener(this);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //getting all types of files
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(Intent.createChooser(intent, ""),FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {

                    Toast.makeText(getApplicationContext(), "Please install a File Manager.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(getApplicationContext(),Review.class);
//                startActivity(i);
                review=e1.getText().toString();

                rating=r1.getRating();

               // Toast.makeText(getApplicationContext(),path+" path",Toast.LENGTH_LONG).show();

                int res=uploadFile(path);
                if(res==1)
                {
                      Toast.makeText(getApplicationContext(), " uploaded", Toast.LENGTH_LONG).show();
//                    Intent i=new Intent(getApplicationContext(),Customers_Home.class);
//                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), " error", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d("File Uri", "File Uri: " + uri.toString());
                    // Get the path
                    try {
                        path = FileUtils.getPath(this, uri);
                        //e4.setText(path1);
                        Log.d("path", path);
                        File fil = new File(path);
                        int fln=(int) fil.length();
                        //e2.setText(path);

//						File file = new File(path);

                        byte[] byteArray = null;
                        try
                        {
                            InputStream inputStream = new FileInputStream(fil);
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            byte[] b = new byte[fln];
                            int bytesRead =0;

                            while ((bytesRead = inputStream.read(b)) != -1)
                            {
                                bos.write(b, 0, bytesRead);
                            }

                            byteArray = bos.toByteArray();
                            inputStream.close();
				        Bitmap bmp=BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                           // Toast.makeText(getApplicationContext(),bmp+"",Toast.LENGTH_LONG).show();
				        if(bmp!=null){


						img1.setVisibility(View.VISIBLE);
						img1.setImageBitmap(bmp);
				        }
                        }
                        catch (Exception e) {
                            // TODO: handle exception
                            Toast.makeText(getApplicationContext(),e+"eeeeeeee",Toast.LENGTH_LONG).show();
                        }
                    }

                    catch (URISyntaxException e) {
                        // TODO Auto-generated catch block
                        Toast.makeText(getApplicationContext(),e+"uriiiii",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(this,"Please select suitable file", Toast.LENGTH_LONG).show();
                }
                break;



        }


    }
    public int uploadFile(String sourceFileUri) {

                sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                ip=sp.getString("ip","");

                fileName = sourceFileUri;
                String upLoadServerUri = "http://"+sp.getString("ip", "")+":5000/reviews";

                // Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_LONG).show();
                FileUpload fp = new FileUpload(fileName);
//                Toast.makeText(getApplicationContext(),rating+"",Toast.LENGTH_LONG).show();

                Map mp = new HashMap<String,String>();

        try {
            mp.put("uid", sp.getString("lid", ""));
            mp.put("pid", itemname);
                mp.put("review", review);
               mp.put("rate", rating+"");

           String jsonresult=fp.multipartRequest(upLoadServerUri, mp, fileName, "files", "application/octet-stream");

           JSONObject obj=new JSONObject(jsonresult.toString());
           String res=obj.getString("task");

        }

        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"error "+e.getMessage()+"",Toast.LENGTH_LONG).show();
        }







//        fp.multipartRequest(upLoadServerUri, mp, fileName, "files", "application/octet-stream");


        return 1;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    categ=cat[i];
    if(adapterView==s1) {
    try {
        url = "http://" + sp.getString("ip", "") + ":5000/rev";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("category", categ));
        JSONArray ar = (JSONArray) parser.makeHttpRequest(url, "GET", params);


        pid = new ArrayList<String>();

        pname = new ArrayList<>();

        for (int io = 0; io < ar.length(); io++) {
            JSONObject js = ar.getJSONObject(io);
            pid.add(js.getString("pid"));
            pname.add(js.getString("item_name"));


        }
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pname);
        s2.setAdapter(ad);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int f, long l) {

                itemname=pid.get(f);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    } catch (JSONException e) {
        e.printStackTrace();
    }

}
 }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
