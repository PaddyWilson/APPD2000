package com.example.Assignment_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Paddy Wilson on 27/11/14.
 */
public class imageSelect extends Activity {

    ListView list;
    ArrayList<String> web;
    ArrayList<String> imageId;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_select);

        web = new ArrayList<String>();
        imageId = new ArrayList<String>();

        web.add("ghost_rider");
        web.add("ghost_rider_2");
        web.add("drive_angry");
        web.add("gone_in_60_seconds");
        web.add("joe");
        web.add("national_treasure");
        web.add("national_treasure_2");
        web.add("seeking_justice");
        web.add("the_wicker_man");

        imageId.add("ghost_rider.png");
        imageId.add("ghost_rider_2.png");
        imageId.add("drive_angry.png");
        imageId.add("gone_in_60_seconds.png");
        imageId.add("joe.png");
        imageId.add("national_treasure.png");
        imageId.add("national_treasure_2.png");
        imageId.add("seeking_justice.png");
        imageId.add("the_wicker_man.png");

//        ArrayList<String> temp = new ArrayList<String>();
//
//        File dir = new File("/assets/image/");
//        File[] filelist = dir.listFiles();
//        Log.w("Image Select", "number " + filelist);
//
////        for (File f : filelist)
////        {
////            temp.add(filelist.toString());
////        }
//
//        web = new String[temp.size()];
//        for (int i = 0; i < temp.size(); i++)
//            web[i] = temp.get(i).toString();

        CustomListView adapter = new CustomListView(imageSelect.this, web, imageId);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedImage", imageId.get(position).toString());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                //Toast.makeText(imageSelect.this,"You Clicked at " + web.get(+position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}