package com.nscc.click;

import android.R.bool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends ActionBarActivity {
	
	//step 1. declare var for controls
	TextView tvAnswer;
	Button btnClick;
	int a = 0;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step 2: assign view to properties
        tvAnswer = (TextView)findViewById(R.id.tvshow);
        btnClick = (Button)findViewById(R.id.button1);
        
        //step 3: create listeners
        
        OnClickListener oclBtnClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(a==0)
				{
					a = 1;
					tvAnswer.setText("more more more");
				}
				else{
					a = 0;
					tvAnswer.setText("mmmmmmmm");
				}
			}
		};// end listener
        
        //step 4: assign listener to properties
		btnClick.setOnClickListener(oclBtnClickListener);
		
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    
    
    
}
