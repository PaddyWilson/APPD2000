package com.example.Assignment_3;

import android.app.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.Assignment_3.R.*;

public class MyActivity extends Activity implements OnItemClickListener {

    public static final String PREFS_NAME = "MyPrefsFile";

    public static final String MAIN_ACTIVITY= "Main Activity";//for logcat
    ListView listView;
    ArrayList<String> movies = new ArrayList<String>();
    //HashMap<String, String> movies2 = new HashMap<String, String>();

    Intent i;

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
        DBAdapter db = new DBAdapter(this);

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

        if (c.moveToFirst()) {
            do {
                movies.add(c.getString(1));
                //movies2.put(c.getString(0), c.getString(1));
            } while (c.moveToNext());
        }

        Log.w(MAIN_ACTIVITY, "ListView Things");
        listView = (ListView) findViewById(id.listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, movies));

        listView.setOnItemClickListener(this);
        Log.w(MAIN_ACTIVITY, "End");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.w(MAIN_ACTIVITY, "Item Clicked = " + movies.get(i));

        this.i = new Intent(MyActivity.this, MovieInfo.class);
        this.i.putExtra("i", i + 1);
        startActivity(this.i);

        Toast.makeText(this, "Item clicked" + movies.get(i), Toast.LENGTH_LONG);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

    private void firstRun()
    {
        DBAdapter db = new DBAdapter(this);
        db.open();
        //title, trailer, image, rating, description
        long id = db.insertMovie("Ghost Rider", "ghost_rider", "ghost_rider", "5", "Nicolas Cage in Ghost Rider");
        id = db.insertMovie("Ghost Rider 2", "ghost_rider_2", "ghost_rider_2", "5", "Nicolas Cage in Ghost Rider 2");
        id = db.insertMovie("Gone In 60 Seconds", "gone_in_60_seconds", "gone_in_60_seconds", "5", "Nicolas Cage in Gone In 60 Seconds");
        id = db.insertMovie("Drive Angry", "drive_angry", "drive_angry", "4", "Who Does not want to Drive Angry? Also Nicolas Cage IS IN IT!!!!");
        id = db.insertMovie("Joe", "joe", "joe", "1.5", "Nicolas Cage in Joe");
        id = db.insertMovie("National Treasure", "national_treasure", "national_treasure", "0", "Nicolas Cage in National Treasure");
        id = db.insertMovie("National Treasure 2", "national_treasure_2", "national_treasure_2", "3", "Nicolas Cage in National Treasure 2");
        id = db.insertMovie("Seeking Justice", "seeking_justice", "seeking_justice", "5", "Nicolas Cage in Seeking Justice");
        id = db.insertMovie("The Wicker Man", "the_wicker_man", "the_wicker_man", "4", "Nicolas Cage in The Wicker Man");
        db.close();
    }

}
