package com.example.listViewExample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;


public class MyActivity extends Activity implements OnItemClickListener {
    /**
     * Called when the activity is first created.
     */
    ListView listView;
    private final static String month[] = {"Jan", "Feb", "mar", "april", "may", "june", "aug", "sept", "oct", "nov", "dec"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(this.android.R.layout.simple_list_item, month));

        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Item clicked" + month[i], Toast.LENGTH_LONG);
    }
}
