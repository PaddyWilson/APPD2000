package com.example.Assignment_3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.InputStream;

/**
 * Created by Paddy Wilson on 20/11/14.
 */
public class NewMovie extends Activity {

    Button save, selectImage;
    EditText des, title;
    RatingBar rating;
    ImageView imageView;
    DBAdapter db;

    String image = "";


    Intent i;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_movie);

        db = new DBAdapter(this);

        save = (Button) findViewById(R.id.btnSave);
        selectImage = (Button) findViewById(R.id.btnSelectImage);

        des = (EditText) findViewById(R.id.edtDescription);
        title = (EditText) findViewById(R.id.edtTitle);
        rating = (RatingBar) findViewById(R.id.ratingBar);
        imageView = (ImageView) findViewById(R.id.imageView);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("Edit Movie", "Title: " + title.getText().toString());
                Log.w("Edit Movie", "Des: " + des.getText().toString());
                if (!title.getText().toString().equals("") && !des.getText().toString().equals(""))
                {
                    Log.w("Edit Movie", "Good data ");
                    db.open();
                    db.insertMovie(title.getText().toString(), "", image, Float.toString(rating.getRating()), des.getText().toString());
                    db.close();
                    finish();
                }
                else
                {
                    Log.w("Edit Movie", "Bad data");
                    Toast.makeText(NewMovie.this, "Dont leave anyhitng blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewMovie.this, imageSelect.class);
                startActivityForResult(intent, 1 );
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent pData)
    {
        if ( requestCode == 1 )
        {
            if (resultCode == Activity.RESULT_OK )
            {
                image = pData.getExtras().getString("selectedImage");
                InputStream is = getClass().getResourceAsStream("/assets/image/"+image);
                imageView.setImageDrawable(Drawable.createFromStream(is, ""));
                Log.w("Edit Movie", "Retrieved Value zData is "+image );

            }
        }

    }
}