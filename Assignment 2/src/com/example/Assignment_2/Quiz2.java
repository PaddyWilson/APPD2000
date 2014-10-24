package com.example.Assignment_2;

import android.content.Context;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Quiz2 {

    private ArrayList<ArrayList> arrayListOfArrayLists = new ArrayList<ArrayList>();
    private HashMap<String, String> answers = new HashMap<String, String>();

    private String name;

    Quiz2(String name, Context context)
    {
        this.name = name;

        InputStream is = context.getResources().openRawResource(R.raw.quiz);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = null;

        try{
            int a = 0;
            while((str = br.readLine())!= null)
            {
                ArrayList<String> list = new ArrayList<String>();
                String line[] = str.split(";");
                for (int i = 0; i < line.length; i++)
                {
                    list.add(line[i].toString());
                }
                arrayListOfArrayLists.add(list);

                for (int i = 0; i < line.length; i++)
                {
                    Log.d("*****List: ", arrayListOfArrayLists.get(a).get(i).toString());
                }
                a++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }

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

    public ArrayList<ArrayList> getArrayListOfArraryLists() {
        return arrayListOfArraryLists;
    }

    public void setArrayListOfArraryLists(ArrayList<ArrayList> arrayListOfArraryLists) {
        this.arrayListOfArraryLists = arrayListOfArraryLists;
    }
}
