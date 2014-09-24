package com.example.evilintent;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	Button btnBrowserButton, btnPhoneButton, btnContactsButton, btnMapsButton;
	int requestCode = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	btnBrowserButton = (Button) findViewById(R.id.btnBrowser);
	btnPhoneButton = (Button) findViewById(R.id.button1);
	btnContactsButton =(Button) findViewById(R.id.button2);
	btnMapsButton = (Button) findViewById(R.id.button3);
	
	btnBrowserButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("android.intent.action.VIEW");
				i.setData(Uri.parse("http://www.nscc.ca"));
				startActivity(i);
			}
		});
	  
	btnPhoneButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(android.content.Intent.ACTION_DIAL);//, Uri.parse("9021234567"));
				i.setData(Uri.parse("tel:9021234567"));
				startActivity(i);
			}
		});
	btnContactsButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(android.content.Intent.ACTION_PICK);
			i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
			startActivity(i);
		}
	});
	
	btnMapsButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.ca/maps/place/Moscow,+Russia/@55.749792,37.632495,10z/data=!3m1!4b1!4m2!3m1!1s0x46b54afc73d4b0c9:0x3d44d6cc5757cf4c"));
			//i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
			startActivity(i);
		}
	});
	
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
