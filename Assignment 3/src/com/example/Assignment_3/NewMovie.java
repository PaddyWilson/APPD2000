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

    Button save, selectImage, selectTrailer;
    EditText des, title;
    RatingBar rating;
    ImageView imageView;
    DBAdapter db;

    String image = "";
    String video = "";


    Intent i;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_movie);

        db = new DBAdapter(this);

        save = (Button) findViewById(R.id.btnSave);
        selectImage = (Button) findViewById(R.id.btnSelectImage);
        selectTrailer = (Button) findViewById(R.id.btnSelectVideo);

        des = (EditText) findViewById(R.id.edtDescription);
        title = (EditText) findViewById(R.id.edtTitle);
        rating = (RatingBar) findViewById(R.id.ratingBar);
        imageView = (ImageView) findViewById(R.id.imageView);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().equals(""))
                    Toast.makeText(NewMovie.this, "Enter A Title", Toast.LENGTH_SHORT).show();
                else if(des.getText().toString().equals(""))
                    Toast.makeText(NewMovie.this, "Enter A Description", Toast.LENGTH_SHORT).show();
                else if(image.equals(""))
                    Toast.makeText(NewMovie.this, "Select a image", Toast.LENGTH_SHORT).show();
                else if(video.equals(""))
                    Toast.makeText(NewMovie.this, "Select a triler", Toast.LENGTH_SHORT).show();
                else
                {
                    Log.w("Edit Movie", "Good data ");
                    db.open();
                    db.insertMovie(title.getText().toString(), video, image, Float.toString(rating.getRating()), des.getText().toString());
                    db.close();
                    finish();
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

        selectTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewMovie.this, videoSelect.class);
                startActivityForResult(intent, 2 );
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
            }
        }
        if ( requestCode == 2 )
        {
            if (resultCode == Activity.RESULT_OK )
            {
                video = pData.getExtras().getString("selectedVideo");
            }
        }
    }
}