package com.example.Assignment_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_MOVIE_TITLE = "title";
    public static final String KEY_MOVIE_TRAILER = "trailer";
    public static final String KEY_MOVIE_IMAGE = "image";
    public static final String KEY_MOVIE_RATING = "rating";
    public static final String KEY_MOVIE_DESCRIPTION = "description";
    public static final String TAG = "DBAdapter";//for logcat

    private static final String DATABASE_NAME = "MovieTrailer";
    private static final String DATABASE_TABLE = "movie";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table movie(" +
                    "_id integer primary key autoincrement," +
                    "title text not null," +
                    "trailer text not null," +
                    "image text not null," +
                    "rating text not null," +
                    "description text not null);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, "Create database");
            try {
                Log.w(TAG, "Create database yes");
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                Log.w(TAG, "Create database faled");
                e.printStackTrace();
            }
        }//end method onCreate

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrade database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }//end method onUpgrade
    }

    //open the database
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //close the database
    public void close() {
        DBHelper.close();
    }

    //retrieve all the Movies
    public Cursor getAllMovies() {

        return db.query(DATABASE_TABLE, new String[]{KEY_ROW_ID, KEY_MOVIE_TITLE, KEY_MOVIE_TRAILER,
                KEY_MOVIE_IMAGE, KEY_MOVIE_RATING, KEY_MOVIE_DESCRIPTION}, null, null, null, null, null);
    }

    //retrieve a single Movie
    public Cursor getMovie(long rowId) throws SQLException {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROW_ID, KEY_MOVIE_TITLE,
                KEY_MOVIE_TRAILER, KEY_MOVIE_IMAGE, KEY_MOVIE_RATING,
                KEY_MOVIE_DESCRIPTION}, KEY_ROW_ID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //delete a Movie contact
    public boolean deleteMovie(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROW_ID + "=" + rowId, null) > 0;
    }

    //insert a Movie into the database
    public long insertMovie(String title, String trailer, String image, String rating, String description) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_MOVIE_TITLE, title);
        initialValues.put(KEY_MOVIE_TRAILER, trailer);
        initialValues.put(KEY_MOVIE_IMAGE, image);
        initialValues.put(KEY_MOVIE_RATING, rating);
        initialValues.put(KEY_MOVIE_DESCRIPTION, description);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //update movie rating
    public boolean updateMovieRating(long rowId, String rating) {
        ContentValues values = new ContentValues();
        values.put(KEY_MOVIE_RATING, rating);
        return db.update(DATABASE_TABLE, values, KEY_ROW_ID + "=" + rowId, null) > 0;
    }

}//end class DBAdapter
