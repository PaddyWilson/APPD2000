package com.example.Assignment_3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by Paddy Wilson on 20/11/14.
 */
public class EditMovieInfo extends Activity {

    Button save;
    EditText des, title;
    RatingBar rating;
    DBAdapter db;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie);

        db = new DBAdapter(this);

        save = (Button) findViewById(R.id.btnSave);
        des = (EditText) findViewById(R.id.edtDescription);
        title = (EditText) findViewById(R.id.edtTitle);
        rating = (RatingBar) findViewById(R.id.ratingBar);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("Edit Movie", "Title: " + title.getText().toString());
                Log.w("Edit Movie", "Des: " + des.getText().toString());
                if (!title.getText().toString().equals("") && !des.getText().toString().equals(""))
                {
                    Log.w("Edit Movie", "Good data ");
                    db.open();
                    db.insertMovie(title.getText().toString(), "", "", Float.toString(rating.getRating()), des.getText().toString());
                    db.close();
                    finish();
                }
                else
                {
                    Log.w("Edit Movie", "Bad data");
                    Toast.makeText(EditMovieInfo.this, "Dont leave anyhitng blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}