package com.example.Assignment_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MyActivity extends Activity {

    String name;
    Button btnName, btnNext;
    RadioButton answer1, answer2, answer3, answer4;
    TextView txtScore;
    EditText edtName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnName = (Button) findViewById(R.id.btnSubmit);
        btnNext = (Button) findViewById(R.id.btnNext);

        answer1 = (RadioButton) findViewById(R.id.rbAnswer1);
        answer2 = (RadioButton) findViewById(R.id.rbAnswer2);
        answer3 = (RadioButton) findViewById(R.id.rbAnswer3);
        answer4 = (RadioButton) findViewById(R.id.rbAnswer4);

        txtScore = (TextView) findViewById(R.id.txtYourScore);

        edtName = (EditText) findViewById(R.id.edtEnterName);

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edtName.getText().toString();

                //System.out.println(name);

                if (name.equals(""))//if no string is entered
                {
                    //System.out.println("no name entered");
                }
                else//string is entered
                    setContentView(R.layout.test);
            }
        });

        //next button for next question
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
