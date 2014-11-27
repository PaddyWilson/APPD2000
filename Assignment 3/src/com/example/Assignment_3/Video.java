package com.example.Assignment_3;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Paddy Wilson on 19/11/14.
 */
public class Video extends Activity {

    private final String TAG = "Video Activity";

    VideoView videoView;
    Button play, pause, stop;
    String video;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        Bundle bundle = getIntent().getExtras();
        video = bundle.getString("video");
        Log.w(TAG, video);

        videoView = (VideoView) findViewById(R.id.videoView);
        play = (Button) findViewById(R.id.btnPlay);
        pause = (Button) findViewById(R.id.btnPause);
        stop = (Button) findViewById(R.id.btnStop);

        videoView.setVideoURI(Uri.parse("android.resource://"+  getPackageName() + "/raw/" + video));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.pause();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}