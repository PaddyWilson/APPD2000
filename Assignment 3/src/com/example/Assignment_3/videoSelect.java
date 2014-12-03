package com.example.Assignment_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Paddy Wilson on 28/11/14.
 */
public class videoSelect extends Activity implements OnItemClickListener {

    ListView listView;
    ArrayList<String> movies = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_select);


        movies.add("ghost_rider");
        movies.add("ghost_rider_2");
        movies.add("drive_angry");
        movies.add("gone_in_60_seconds");
        movies.add("joe");
        movies.add("national_treasure");
        movies.add("national_treasure_2");
        movies.add("seeking_justice");
        movies.add("the_wicker_man");

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movies));
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selectedVideo", movies.get(i).toString());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();//return to the previous activity
    }
}