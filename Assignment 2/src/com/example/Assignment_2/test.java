package com.example.Assignment_2;

import android.app.Activity;
import android.content.Context;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class test extends Activity {

    public class Quiz
    {
        private ArrayList<ArrayList<String>> arrayListOfArrayLists = new ArrayList<ArrayList<String>>();
        private HashMap<String, String> answers = new HashMap<String, String>();

        private String name;

        Quiz(String name, Context context)
        {
            writeToLog("Start");
            this.name = name;

            writeToLog("Reading Quiz.txt file");
            InputStream is = context.getResources().openRawResource(R.raw.quiz);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str;

            try{
                int a = 0;
                while((str = br.readLine())!= null)
                {
                    ArrayList<String> list = new ArrayList<String>();
                    String line[] = str.split(";");
                    for (int i = 0; i < line.length + 1; i++)
                    {
                        if (i == 0)
                            list.add(Integer.toString(a));
                        else
                            list.add(line[i-1].toString());

                        if (i == 5)
                            answers.put(Integer.toString(a), line[i-1].toString());

                    }
                    arrayListOfArrayLists.add(list);
                    a++;
                }
            }catch (IOException e){
                e.printStackTrace();
                Log.d("Quiz Debug", "Error reading quiz.txt");
            }
            writeToLog("Done reading quiz.txt file");

            printQuizToLog();

            shuffleQuiz();

            printQuizToLog();

            printHashMap();

        }

        public void shuffleQuiz()
        {
            writeToLog("Shuffling Quiz");
            Collections.shuffle(arrayListOfArrayLists);

            for (int i = 0; i < arrayListOfArrayLists.size(); i++)
            {
                Collections.shuffle(arrayListOfArrayLists.get(i).subList(2, 5));
            }
        }

        private void printQuizToLog()
        {
            writeToLog("printQuizToLog");
            writeToLog("Number of questions in quiz " + Integer.toString(arrayListOfArrayLists.size()));
            for (int i = 0; i < arrayListOfArrayLists.size() - 1; i++)
            {
                String a, b, c, d, e, f;
                a = arrayListOfArrayLists.get(i).get(0);
                b = arrayListOfArrayLists.get(i).get(1);
                c = arrayListOfArrayLists.get(i).get(2);
                d = arrayListOfArrayLists.get(i).get(3);
                e = arrayListOfArrayLists.get(i).get(4);
                f = arrayListOfArrayLists.get(i).get(5);
                writeToLog("Question: " + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f);
            }
        }

        private void printHashMap()
        {
            writeToLog("Answers Hash Map");
            Iterator<String> myIterator = answers.values().iterator();//create iterator obj

            while(myIterator.hasNext())//display colors using toast
            {
                CharSequence text = myIterator.next();//set up string
                writeToLog("Answers: " + text.toString());
            }
        }


        private void writeToLog(String in)
        {
            Log.d("Quiz Debug", in);
        }

        //getter and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HashMap<String, String> getAnswers() {
            return answers;
        }

        public void setAnswers(HashMap<String, String> answers) {
            this.answers = answers;
        }
    }

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
        quiz = new Quiz(name, this);

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

        //Quiz2("name", this);


    }

    private void writeLog(String text)
    {
        Log.d("Test Debug", text);
    }

    //quiz class wont work for some reason
    //for testing
//
//    private ArrayList<ArrayList> arrayListOfArrayLists = new ArrayList<ArrayList>();
//    private HashMap<String, String> answers = new HashMap<String, String>();
//
//    private String name;
//
//    private void Quiz2(String name, Context context)
//    {
//        this.name = name;
//
//        InputStream is = this.getResources().openRawResource(R.raw.quiz);
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        String str = null;
//
//        try{
//            int a = 0;
//            while((str = br.readLine())!= null)
//            {
//                ArrayList<String> list = new ArrayList<String>();
//                String line[] = str.split(";");
//                for (int i = 0; i < line.length; i++)
//                {
//                    list.add(line[i].toString());
//                }
//                arrayListOfArrayLists.add(list);
//
//                for (int i = 0; i < line.length; i++)
//                {
//                    writeLog(arrayListOfArrayLists.get(a).get(i).toString());
//                }
//                a++;
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
}