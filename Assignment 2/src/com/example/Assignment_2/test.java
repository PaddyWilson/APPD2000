package com.example.Assignment_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
        private ArrayList<ArrayList<String>> arrayListOfArrayLists = new ArrayList<ArrayList<String>>();//holds questions and possible answers
        private HashMap<String, String> answers = new HashMap<String, String>();//holds answers

        private String name;//holds the players name

        private int score = 0;
        private int numberOfQuestions = 0;

        private int currentQuestion = -1;
        private String currentAnswer;

        //constructor
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
                writeToLog("Error reading quiz.txt");
            }
            writeToLog("Done reading quiz.txt file");
            numberOfQuestions = arrayListOfArrayLists.size();

            //printQuizToLog();

            shuffleQuiz();

            //printQuizToLog();
            //printHashMap();

        }

        //gets the current question
        public ArrayList<String> nextQuestion()
        {
            writeToLog("nextQuestion");
            currentQuestion++;
            ArrayList<String> questions = new ArrayList<String>();
            questions.addAll(arrayListOfArrayLists.get(currentQuestion).subList(1, 6));
            currentAnswer = answers.get(arrayListOfArrayLists.get(currentQuestion).get(0));
            writeToLog("Answer To Current Question: " + currentAnswer);
            return questions;
        }

        //checks to see if they answer correctly
        public boolean isCorrect(String answer)
        {
            writeToLog("isCorrect");
            if (answer.equals(this.currentAnswer))
            {
                score++;
                return true;
            }
            return false;
        }

        // shuffles the quiz and quiz questions
        public void shuffleQuiz()
        {
            writeToLog("Shuffling Quiz");
            Collections.shuffle(arrayListOfArrayLists);

            for (int i = 0; i < arrayListOfArrayLists.size(); i++)
            {
                Collections.shuffle(arrayListOfArrayLists.get(i).subList(2, 6));
            }
        }

        //logging things
        private void printQuizToLog()
        {
            writeToLog("printQuizToLog");
            writeToLog("Number of questions in quiz " + Integer.toString(arrayListOfArrayLists.size()));
            for (int i = 0; i < arrayListOfArrayLists.size(); i++)
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

        public int getScore() {
            return score;
        }

        public int getNumberOfQuestions() {
            return numberOfQuestions;
        }

        public int getCurrentQuestion() {
            return currentQuestion + 1;
        }
    }
    //end of quiz class

    //*********************************************************************************************************
    Quiz quiz;

    //controls
    Button answer1, answer2, answer3, answer4;
    TextView txtScore;
    TextView txtName, txtQuestion;
    EditText edtQuestion;

    ArrayList<String> question;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        quiz = new Quiz(name, this);

        answer1 = (Button) findViewById(R.id.btnAnswer1);
        answer2 = (Button) findViewById(R.id.btnAnswer2);
        answer3 = (Button) findViewById(R.id.btnAnswer3);
        answer4 = (Button) findViewById(R.id.btnAnswer4);

        txtScore = (TextView) findViewById(R.id.txtScore);
        txtName = (TextView) findViewById(R.id.txtName);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);

        edtQuestion = (EditText) findViewById(R.id.edtQuestion);

        txtName.setText("Name: " + quiz.getName());

        question = quiz.nextQuestion();

        //printQuizToLog();

        updateScore();
        updateQuestionNumber();
        displayQuestionAndAnswers();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeLog("Answer1 pressed");
                go(answer1.getText().toString());
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeLog("Answer2 pressed");
                go(answer2.getText().toString());
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeLog("Answer3 pressed");
                go(answer3.getText().toString());
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeLog("Answer4 pressed");
                go(answer4.getText().toString());
            }
        });
    }

    public void go(String input)
    {
        if (quiz.isCorrect(input))
        {
            Toast.makeText(test.this, "Correct", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(test.this, "Incorrect", Toast.LENGTH_SHORT).show();
        }

        writeLog(quiz.getCurrentQuestion() + " " + quiz.getNumberOfQuestions());
        if (quiz.getCurrentQuestion() < quiz.getNumberOfQuestions())
        {
            question.clear();
            question = quiz.nextQuestion();
            displayQuestionAndAnswers();
            writeLog("More Questions");
            updateScore();
            updateQuestionNumber();
        }
        else
        {
            writeLog("No More Questions");
            Intent i = new Intent(test.this, finalScreen.class);
            i.putExtra("Score", quiz.getScore());
            i.putExtra("NumberQuestions", quiz.getNumberOfQuestions());
            i.putExtra("Name", quiz.getName());
            startActivity(i);
        }
    }

    public void displayQuestionAndAnswers()
    {
        writeLog("displayQuestion");
        edtQuestion.setText(question.get(0));
        answer1.setText(question.get(1));
        answer2.setText(question.get(2));
        answer3.setText(question.get(3));
        answer4.setText(question.get(4));
    }

    public void updateScore() {
        writeLog("updateScore");
        txtScore.setText("Your score is: " + " " + quiz.getScore() + "/" + quiz.getNumberOfQuestions());
    }

    public void updateQuestionNumber() {
        writeLog("updateQuestionNumber");
        txtQuestion.setText("Question: " + " " + (quiz.getCurrentQuestion()));
    }

    //logging things
    private void printQuizToLog()
    {
        writeLog("printQuizToLog");
        for (int i = 0; i < question.size(); i++)
            writeLog("Question: " + question.get(i));
    }

    private void writeLog(String text)
    {
        Log.d("Test Debug", text);
    }
}