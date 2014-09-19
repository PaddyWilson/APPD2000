package com.example.calculator2;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.*;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.text.*;

public class MainActivity extends Activity {

	private static final String BILL_TOTAL = "BILL_TOTAL";
	private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";
	
	private double currentBillTotal;//bill amount entered by user
	private int customPercent;
	
	private EditText billEditText;
	
	private EditText tip10EditText;
	private EditText tip15EditText;
	private EditText tip20EditText;
	
	private EditText total10EditText;
	private EditText total15EditText;
	private EditText total20EditText;
	
	private EditText tipCustomEditText;
	private EditText totalCustomEditText;
	
	private TextView customTipTextView;
	
	private SeekBar customSeekBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
