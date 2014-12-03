package com.example.thingers;

import android.app.Activity;
import android.os.Bundle;
import java.io.*;
import android.app.Activity;
import android.view.Display;
import android.widget.*;
import android.database.*;
import android.database.sqlite.*;

public class MyActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try
        {
            String dest = "/data/data" + getPackageName() + "/databases/MyDB";
            File f = new File(dest);
            if (f.exists())
            {
                copyDB(getBaseContext().getAssets().open("mydb"), new FileOutputStream(dest));
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        DBAdapter db = new DBAdapter(this);

        db.open();
        Cursor c = db.getAllContacts();
        if (c.moveToFirst())
        {
            do
            {
                DisplayContact(c);
            }while(c.moveToNext());
        }
        db.close();

        //add contact
        db.open();
        long id = db.insertContact("bob lablaw", "bob@things.net");
        id = db.insertContact("t g", "tg@things.com");
        db.close();

        //get contact
        db.open();
        Cursor b = db.getAllContacts(2);
        //things goes here`

        db.close();

    }

    public void copyDB(InputStream inputStream, OutputStream outputStream) throws IOException
    {
        //copy 1kb at a time
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) > 0)
        {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }



}


