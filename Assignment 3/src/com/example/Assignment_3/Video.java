package com.example.Assignment_3;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Paddy Wilson on 19/11/14.
 */
public class Video extends Activity {

    private final String TAG = "Video Activity";

    VideoView videoView;
    String video;
    String SrcPath = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp";//test video

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

//        VideoView myVideoView = (VideoView)findViewById(R.id.myvideoview);
//        myVideoView.setVideoURI(Uri.parse(SrcPath));
//        myVideoView.setMediaController(new MediaController(this));
//        myVideoView.requestFocus();
//        myVideoView.start();

        Bundle bundle = getIntent().getExtras();
        video = bundle.getString("video");
        Log.w(TAG, video);
        videoView = (VideoView) findViewById(R.id.myvideoview);

        videoView.setVideoURI(Uri.parse("android.resource://"+  getPackageName() + "/raw/" + video));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
    }
}