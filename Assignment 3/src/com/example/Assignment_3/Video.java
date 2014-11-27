package com.example.Assignment_3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.VideoView;

/**
 * Created by Paddy Wilson on 19/11/14.
 */
public class Video extends Activity {

    VideoView videoView;

    String video;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        Bundle bundle = getIntent().getExtras();
        video = bundle.getString("video");

        videoView = (VideoView) findViewById(R.id.videoView);

        videoView.setVideoPath("android.resource://com.example.Assignment_3"+video);
        videoView.start();
    }
}