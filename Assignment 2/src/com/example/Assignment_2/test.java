package com.example.Assignment_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

/**
 * Created by Paddy Wilson on 22/10/14.
 */

public class test extends Activity {
    Quiz quiz;

    //controls
    Button btnNext;
    RadioButton answer1, answer2, answer3, answer4;
    TextView txtScore;
    TextView txtName, txtQuestion;
    EditText edtQuestion;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        quiz = new Quiz(name);

        btnNext = (Button) findViewById(R.id.btnNext);

        answer1 = (RadioButton) findViewById(R.id.rbAnswer1);
        answer2 = (RadioButton) findViewById(R.id.rbAnswer2);
        answer3 = (RadioButton) findViewById(R.id.rbAnswer3);
        answer4 = (RadioButton) findViewById(R.id.rbAnswer4);

        txtScore = (TextView) findViewById(R.id.txtScore);
        txtName = (TextView) findViewById(R.id.txtName);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);

        edtQuestion = (EditText) findViewById(R.id.edtEnterName);

        txtName.setText("Name: " + quiz.getName());

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeLog("Next Button Click");
            }
        });

    }

    void writeLog(String text)
    {
        Log.d("Debug", text);
    }
}