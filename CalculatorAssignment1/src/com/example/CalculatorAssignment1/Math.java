package com.example.CalculatorAssignment1;

import android.util.Log;

//math operations
public class Math
{
    double divide(double number, double number2)
    {
        if (number == 0 || number == 0)
        {
            return 0;
        }
        return number2 / number;
    }

    double multiply(double number, double number2)
    {
        return number * number2;
    }

    double add(double number, double number2)
    {
        return number + number2;
    }

    double subtract(double number, double number2)
    {
        return number2 - number;
    }

}
