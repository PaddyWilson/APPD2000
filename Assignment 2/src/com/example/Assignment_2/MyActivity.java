package com.example.Assignment_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyActivity extends Activity {

    //controls
    Button btnName;
    EditText edtName;

    Intent i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnName = (Button) findViewById(R.id.btnSubmit);

        edtName = (EditText) findViewById(R.id.edtEnterName);

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeLog("Button Click Start");
                String name = edtName.getText().toString();
                writeLog("Entered Name: " + name);
                if (name.equals(""))
                {
                    writeLog("Invalid Name");
                    Toast.makeText(MyActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    writeLog("Valid Name");
                    i = new Intent(MyActivity.this, test.class);
                    i.putExtra("name", name);
                    startActivity(i);
                }
                writeLog("Button Click End");
            }
        });

    }

    private void writeLog(String text)
    {
        Log.d("MyActivity Debug", text);
    }
}
