package com.example.hp.usercentricsimilaritysearch;

import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Videos extends AppCompatActivity {
    VideoView v1;
    String url="",ip="";
    String video="";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        v1=(VideoView)findViewById(R.id.videoView1);
        video=getIntent().getStringExtra("video");
//        MediaController mc = new MediaController(this);
//        mc.setAnchorView(v1);
//        mc.setMediaPlayer(v1);
//
        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sp.getString("ip", "");
//
//
//        Toast.makeText(getApplicationContext(),video,Toast.LENGTH_SHORT).show();
//
//        Uri uri = Uri.parse("http://"+ip+"/static/Prt_videos/"+video);
//        v1.setMediaController(mc);
//        v1.setVideoURI(uri);


        //Creating MediaController
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(v1);

        //specify the location of media file
//        Uri uri=Uri.parse("http://192.168.43.211:5000/static/Prt_videos/VID-20170402-WA0005.mp4");

        Log.d("====video=====","http://"+ip+"/static/Prt_videos/"+video);
          Uri uri = Uri.parse("http://"+ip+":5000/static/Prt_videos/"+video);


        //Setting MediaController and URI, then starting the videoView
        v1.setMediaController(mediaController);
        v1.setVideoURI(uri);
       v1.requestFocus();
        v1.start();



    }
}
