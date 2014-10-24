package com.example.Assignment_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Paddy Wilson on 24/10/14.
 */
public class finalScreen extends Activity {

    TextView txtScore;
    Button newGame;

    String name;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        txtScore = (TextView) findViewById(R.id.txtScore);
        newGame = (Button) findViewById(R.id.btnNewGame);

        Bundle bundle = getIntent().getExtras();
        int Score = bundle.getInt("Score");
        int questionNumber = bundle.getInt("NumberQuestions");
        name = bundle.getString("Name");

        txtScore.setText(Score + "/" + questionNumber);

        newGame.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(finalScreen.this, test.class);
                i.putExtra("name", name);
                startActivity(i);
            }
        });

    }
}