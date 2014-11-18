package com.example.Assignment_3;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by Paddy Wilson on 14/11/14.
 */
public class MovieInfo extends Activity {

    public static final String MOVIE_ACTIVITY = "Movie Info Activity";//for logcat

    TextView name;
    RatingBar rating;
    EditText description;
    ImageView image;

    long index;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        Log.w(MOVIE_ACTIVITY, "Start");
        Log.w(MOVIE_ACTIVITY, "Getting index");
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("i");

        Log.w(MOVIE_ACTIVITY, "Things = Things");
        name = (TextView) findViewById(R.id.txtTitle);
        rating = (RatingBar) findViewById(R.id.ratingBar);
        description = (EditText) findViewById(R.id.editText);
        image = (ImageView) findViewById(R.id.imageView);

        Log.w(MOVIE_ACTIVITY, "Connect to  DB");
        DBAdapter db = new DBAdapter(this);
        //Cursor c;
        Log.w(MOVIE_ACTIVITY, "Open DB");
        db.open();
        Log.w(MOVIE_ACTIVITY, "Get movie");
        Cursor c = db.getMovie(index);
        Log.w(MOVIE_ACTIVITY, "Close DB");
        db.close();

        Log.w(MOVIE_ACTIVITY, "Set text things");
        c.moveToFirst();
        name.setText(c.getString(1));
        description.setText(c.getString(5));
        InputStream is = getClass().getResourceAsStream("/assets/image/"+c.getString(3)+".png");
        image.setImageDrawable(Drawable.createFromStream(is, ""));
        rating.setRating((float) Double.parseDouble(c.getString(4)));
    }
}