package com.example.Assignment_3;

import android.app.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

import static com.example.Assignment_3.R.*;

public class MyActivity extends Activity implements OnItemClickListener {

    public static final String PREFS_NAME = "MyPrefsFile";

    public static final String MAIN_ACTIVITY= "Main Activity";//for logcat
    ListView listView;
    ArrayList<String> movieTitles = new ArrayList<String>();
    ArrayList<String> movieId = new ArrayList<String>();

    //HashMap<String, String> movies2 = new HashMap<String, String>();

    Intent i;
   DBAdapter db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.w(MAIN_ACTIVITY, "Starting");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //if the app is running for the first time
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); // Get preferences file (0 = no option flags set)
        boolean firstRun = settings.getBoolean("firstRun", true); // Is it first run? If not specified, use "true"

        //if the app is running for the first time
        if (firstRun) {
            Log.w(MAIN_ACTIVITY, "first time running");
            firstRun();
            SharedPreferences.Editor editor = settings.edit(); // Open the editor for our settings
            editor.putBoolean("firstRun", false); // It is no longer the first run
            editor.commit(); // Save all changed settings
        }


        Log.w(MAIN_ACTIVITY, "New DBAdapter");
        db = new DBAdapter(this);

        listView = (ListView) findViewById(id.listView);
        listView.setOnItemClickListener(this);
        movie();

        Log.w(MAIN_ACTIVITY, "End");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    protected void onResume()
    {
        super.onResume();
        movie();
    }

    public void movie()
    {
        Log.w(MAIN_ACTIVITY, "movie()");

        Log.w(MAIN_ACTIVITY, "Opening Database");
        db.open();
        Log.w(MAIN_ACTIVITY, "Getting All Movies");
        Cursor c = db.getAllMovies();
        Log.w(MAIN_ACTIVITY, "Displaying Movie");
        if (c.moveToFirst()) {
            do {
                DisplayMovie(c);
            } while (c.moveToNext());
        }
        Log.w(MAIN_ACTIVITY, "Close DB connection");
        db.close();

        movieTitles.clear();//clears the array to be populated again
        movieId.clear();//clears the movie ids from the array
        if (c.moveToFirst()) {
            do {
                movieTitles.add(c.getString(1));
                movieId.add(Long.toString(c.getLong(0)));
                //movies2.put(c.getString(0), c.getString(1));
            } while (c.moveToNext());
        }

        Log.w(MAIN_ACTIVITY, "ListView Things");
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieTitles));

        //listView.setOnItemClickListener(this);
    }

    //edit menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                Intent i = new Intent(MyActivity.this, EditMovieInfo.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.w(MAIN_ACTIVITY, "Item Clicked = " + movieId.get(i));
        this.i = new Intent(MyActivity.this, MovieInfo.class);
        this.i.putExtra("i", Integer.parseInt(movieId.get(i)));
        startActivity(this.i);
        //Toast.makeText(this, "Item clicked" + movieTitles.get(i), Toast.LENGTH_LONG);
    }

    //displaces database info to logcat
    public void DisplayMovie(Cursor c) {
        Log.w("Main Activity",
                "id: " + c.getString(0) +
                        ", Title: " + c.getString(1) +
                        ", Trailer: " + c.getString(2) +
                        ", Image: " + c.getString(3) +
                        ", Rating: " + c.getInt(4) +
                        ", Description: " + c.getString(5));
    }

    private void firstRun()
    {
        Log.w(MAIN_ACTIVITY, "firstRun()");
        DBAdapter db = new DBAdapter(this);
        db.open();
        //title, trailer, image, rating, description
        db.insertMovie("Ghost Rider", "ghost_rider", "ghost_rider.png", "5", "Nicolas Cage in Ghost Rider");
        db.insertMovie("Ghost Rider 2", "ghost_rider_2", "ghost_rider_2.png", "5", "Nicolas Cage in Ghost Rider 2");
        db.insertMovie("Gone In 60 Seconds", "gone_in_60_seconds", "gone_in_60_seconds.png", "5", "Nicolas Cage in Gone In 60 Seconds");
        db.insertMovie("Drive Angry", "drive_angry", "drive_angry.png", "4", "Who Does not want to Drive Angry? Also Nicolas Cage IS IN IT!!!!");
        db.insertMovie("Joe", "joe", "joe.png", "1.5", "Nicolas Cage in Joe");
        db.insertMovie("National Treasure", "national_treasure", "national_treasure.png", "0", "Nicolas Cage in National Treasure");
        db.insertMovie("National Treasure 2", "national_treasure_2", "national_treasure_2.png", "3", "Nicolas Cage in National Treasure 2");
        db.insertMovie("Seeking Justice", "seeking_justice", "seeking_justice.png", "5", "Nicolas Cage in Seeking Justice");
        db.insertMovie("The Wicker Man", "the_wicker_man", "the_wicker_man.png", "4", "Nicolas Cage in The Wicker Man");
        db.close();
    }

}
