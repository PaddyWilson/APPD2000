package com.example.Assignment_2;

import android.content.Context;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Quiz2 {


    private ArrayList<ArrayList<String>> arrayListOfArrayLists = new ArrayList<ArrayList<String>>();//holds questions and possible answers
    private HashMap<String, String> answers = new HashMap<String, String>();//holds answers

    private String name;//holds the players name

    private int score = 0;
    private int numberOfQuestions = 0;

    private int currentQuestion = -1;
    private String currentAnswer;

    //constructor
    Quiz2(String name, Context context)
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
