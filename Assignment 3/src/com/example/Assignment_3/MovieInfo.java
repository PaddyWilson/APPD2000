package com.example.Assignment_3;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Paddy Wilson on 14/11/14.
 */
public class MovieInfo extends Activity {

    public static final String MOVIE_ACTIVITY = "Movie Info Activity";//for logcat

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);
    }
}