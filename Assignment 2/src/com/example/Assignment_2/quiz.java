package com.example.Assignment_2;

import java.util.ArrayList;
import java.util.HashMap;

public class Quiz {

    private ArrayList<ArrayList> arrayListOfArraryLists = new ArrayList<ArrayList>();
    private HashMap<String, String> answers = new HashMap<String, String>();

    private String name;

    Quiz(String name)
    {
        this.name = name;
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
