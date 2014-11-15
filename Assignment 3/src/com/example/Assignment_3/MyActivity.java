package com.example.Assignment_3;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;

public class MyActivity extends Activity implements OnItemClickListener {

    public static final String PREFS_NAME = "MyPrefsFile";

    public static final String MAIN_ACTIVITY= "Main Activity";//for logcat
    ListView listView;
    ArrayList<String> movies = new ArrayList<String>();

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
            } while (c.moveToNext());
        }

        Log.w(MAIN_ACTIVITY, "ListView Things");
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, movies));

        listView.setOnItemClickListener(this);
        Log.w(MAIN_ACTIVITY, "End");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.w(MAIN_ACTIVITY, "Item Clicked = " + movies.get(i));
        Toast.makeText(this, "Item clicked" + movies.get(i), Toast.LENGTH_LONG);
    }

    //displaces database info to logcat
    public void DisplayMovie(Cursor c) {
        Log.w("Main Activity",
                "id: " + c.getString(0) +
                        ", Title: " + c.getString(1) +
                        ", Trailer: " + c.getString(2) +
                        ", Image: " + c.getString(3) +
                        ", Rating: " + c.getString(4) +
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
        long id = db.insertMovie("Ghost Rider", "Ghost_Rider", "Ghost_Rider", "10", "Ghost_Rider");
        id = db.insertMovie("Ghost Rider 2", "Ghost_Rider_2", "Ghost_Rider_2", "10", "Ghost_Rider_2");
        id = db.insertMovie("Gone In 60 Seconds", "Gone_In_60_Seconds", "Gone_In_60_Seconds", "10", "Gone_In_60_Seconds");
        id = db.insertMovie("Drive Angry", "Drive_Angry", "Drive_Angry", "10", "Who Does not want to Drive Angry?");
        id = db.insertMovie("Joe", "Joe", "Joe", "10", "Joe");
        id = db.insertMovie("National Treasure", "National_Treasure", "National_Treasure", "10", "National_Treasure");
        id = db.insertMovie("National Treasure 2", "National_Treasure_2", "National_Treasure_2", "10", "National_Treasure_2");
        id = db.insertMovie("Seeking Justice", "Seeking_Justice", "Seeking_Justice", "10", "Seeking_Justice");
        id = db.insertMovie("The Wicker Man", "The_Wicker_Man", "The_Wicker_Man", "10", "The_Wicker_Man");
        db.close();
    }

}
