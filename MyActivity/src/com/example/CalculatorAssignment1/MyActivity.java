package com.example.CalculatorAssignment1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnDivide, btnMult, btnAdd, btnSub;
    Button btnDot, btnNegPos, btnEnter, btnBack, btnClear;
    EditText edtDisplay;

    int select = 0;//1=divide, 2=multiply, 3=add, 4=subtract

    double number1 = 0;
    double number2 = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //buttons
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnNegPos = (Button) findViewById(R.id.btnNegPos);
        btnClear = (Button) findViewById(R.id.btnClear);

        //editTexts
        edtDisplay = (EditText) findViewById(R.id.editText);

        //number buttons
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDisplay.setText(edtDisplay.getText()+"0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDisplay.setText(edtDisplay.getText()+"1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDisplay.setText(edtDisplay.getText()+"2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDisplay.setText(edtDisplay.getText()+"3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDisplay.setText(edtDisplay.getText()+"4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDisplay.setText(edtDisplay.getText()+"5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDisplay.setText(edtDisplay.getText()+"6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDisplay.setText(edtDisplay.getText()+"7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDisplay.setText(edtDisplay.getText()+"8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDisplay.setText(edtDisplay.getText()+"9");
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logWriteVar();
                logWrite("Divide pushed");
                getNumber();

                if (select > 0)
                {
                    logWrite("Divide pushed 2");
                    number2 = Double.parseDouble(mathEnter());
                }

                clearText();
                select = 1;
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logWriteVar();
                logWrite("Mult pushed");
                getNumber();

                if (select > 0)
                {
                    logWrite("Mult pushed 2");
                    number2 = Double.parseDouble(mathEnter());
                }
                clearText();
                select = 2;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logWriteVar();
                logWrite("Add Pushed");
                getNumber();

                if (select > 0)
                {
                    logWrite("Add Pushed 2");
                    number2 = Double.parseDouble(mathEnter());
                }
                clearText();
                select = 3;
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logWriteVar();
                logWrite("Sub Pushed");
                getNumber();

                if (select > 0)
                {
                    logWrite("Sub Pushed 2");
                    number2 = Double.parseDouble(mathEnter());
                }
                clearText();
                select = 4;
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtDisplay.getText().toString().contains("."))
                    edtDisplay.setText(edtDisplay.getText()+".");
            }
        });

        btnNegPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtDisplay.getText().toString().length() != 0) {
                    if (Double.parseDouble(edtDisplay.getText().toString()) > 0) {
                        edtDisplay.setText("-" + edtDisplay.getText().toString());
                    } else {
                        edtDisplay.setText(edtDisplay.getText().toString().substring(1));
                    }
                }
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNumber();
                logWriteVar();
                edtDisplay.setText(mathEnter());
                select = 0;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edtDisplay.getText().toString();
                str = removeLastChar(str);
                edtDisplay.setText(str);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearText();
                number1 = 0;
                number2 = 0;
            }
        });

    }

    void clearText()
    {
        select = 0;
        edtDisplay.setText("");
    }

    //gets the entered number and stores it in the correct variable
    void getNumber()
    {
        //removes the NaN if the user did not clear the screan before entering numbers
        if (edtDisplay.getText().toString().contains("NaN"))
            edtDisplay.setText(edtDisplay.getText().toString().substring(3));
        else if (edtDisplay.getText().toString().contains("Na"))
            edtDisplay.setText(edtDisplay.getText().toString().substring(2));
        else if (edtDisplay.getText().toString().contains("N"))
            edtDisplay.setText(edtDisplay.getText().toString().substring(1));

        //if there is just a dot return 0
        if  (edtDisplay.getText().toString().equals("."))
            edtDisplay.setText("0");

        //logWrite(edtDisplay.getText().toString());


        if (!edtDisplay.getText().toString().equals("") && select != 0)
        {
            number1 = Double.parseDouble(edtDisplay.getText().toString());
        }
        else if (!edtDisplay.getText().toString().equals("") && select == 0)
        {
            number2 = Double.parseDouble(edtDisplay.getText().toString());
        }
    }

    //removes the last charater from the supplied string
    public String removeLastChar(String s)
    {
        if (s != null && s.length() > 0) {
           return s.substring(0, s.length() - 1);
        }
        return s;
    }

    //calculates things
    String mathEnter()
    {
        String i = "";
        Math math = new Math();
        if (select == 1)
        {
            if (number1 == 0 || number2 == 0)
            {
                //logWrite("Divide Zero");
                 i = "NaN";
            }
            else
                i = Double.toString(math.divide(number1, number2));
        }
        else if (select == 2)
        {
            i = Double.toString(math.multiply(number1, number2));
        }
        else if (select == 3)
        {
            i = Double.toString(math.add(number1, number2));
        }
        else if (select == 4)
        {
            i = Double.toString(math.subtract(number1, number2));
        }
        else if (select == 0)
            i = Double.toString(number2);

        //select = 0;
        return i;
    }

    //writes to logcat
    public void logWrite(String write)
    {
        Log.w("", write);
    }
    //writes global vars to logcat
    void logWriteVar()
    {
        Log.w("Number 1 ", Double.toString(number1));
        Log.w("Number 2 ", Double.toString(number2));
        Log.w("Select ", Integer.toString(select));
    }

}
