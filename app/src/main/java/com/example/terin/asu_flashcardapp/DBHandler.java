package com.example.terin.asu_flashcardapp;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Terin on 5/18/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    String TAG = "AntonioTesting";
    //All Static variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FlashCardDB";

    //Table Names
    private static final String TABLE_COURSE_DETAIL = "course";
    private static final String TABLE_DECK_DETAIL = "deck";

    //Column names
    private static final String COURSE_ID = "_courseId";
    private static final String COURSE_NAME = "courseName";
    private static final String AUTHOR_ID = "authorId";
    private static final String CREATE_DATE = "createDate";
    private static final String DECK_ID = "_deckId";
    private static final String DECK_NAME = "deckName";

    //Current date
    private static DateFormat dbDateFormat;
    private static Date currentDate;



    public DBHandler (Context context /*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //Create Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_COURSE_DETAIL_TABLE = "CREATE TABLE " + TABLE_COURSE_DETAIL + "("
                + COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COURSE_NAME + " TEXT, "
                + AUTHOR_ID + " INTEGER, "
                + CREATE_DATE + " DATE" + ");";

        db.execSQL(CREATE_COURSE_DETAIL_TABLE);

        String CREATE_DECK_DETAIL_TABLE = "CREATE TABLE " + TABLE_DECK_DETAIL + "("
                + DECK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COURSE_ID + " INTEGER, "
                + DECK_NAME + " TEXT, "
                + AUTHOR_ID + " INTEGER, "
                + CREATE_DATE + " DATE, "
                + "FOREIGN KEY(" + COURSE_ID + ") REFERENCES " + TABLE_COURSE_DETAIL + "(" + COURSE_ID + "));";

        db.execSQL(CREATE_DECK_DETAIL_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DECK_DETAIL);

        //Create Tables again
        onCreate(db);

    }

    //Add course to DB
    public void addCourse(String courseName) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSE_NAME, courseName);
        values.put(AUTHOR_ID, 1);
        values.put(CREATE_DATE, getCreateDate());

        db.insert(TABLE_COURSE_DETAIL, null, values);

        Log.i(TAG, "addCourse: " + courseName);
        db.close();

        //Testing adding Decks
        /*addDeck("Deck1",1);
        addDeck("Deck2",1);
        addDeck("Deck3",1);
        addDeck("Deck4",1);
        addDeck("Deck5",1);

        getDecks(1);*/
    }

    //Delete course from DB
    public boolean deleteCourse(int courseID) {

        boolean value;
        SQLiteDatabase db = getWritableDatabase();

        value = db.delete(TABLE_COURSE_DETAIL, COURSE_ID + "=" + courseID, null) > 0;

        Log.i(TAG, "deleteCourse: " + courseID);
        db.close();

        return value;
    }

    //Get courses
    public ArrayList<Course> getCourses(){
        ArrayList<Course> courseList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COURSE_DETAIL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        //loop through all rows to return
        while(!cursor.isAfterLast()){

            Course course = new Course(cursor.getString(1),Integer.parseInt(cursor.getString(2)));
            course.set_courseId(Integer.parseInt(cursor.getString(0)));
            courseList.add(course);

            //Log.i(TAG, "Get Course: " + course.getCourseName() + course.get_courseId());
            cursor.moveToNext();
        }

        //Log.i(TAG, "Return All Courses");
        db.close();

        return courseList;
    }

    // Antonio Added for StudyWindow courses collection view
    // May be unnecessary
    public Cursor getCoursesViewList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select " + COURSE_ID + ", " + COURSE_NAME + " from " + TABLE_COURSE_DETAIL, null);
        return result;
    }

    //Add deck to DB
    public void addDeck(String deckName, int courseID) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DECK_NAME, deckName);
        values.put(COURSE_ID, courseID);
        values.put(AUTHOR_ID, 1);
        values.put(CREATE_DATE, getCreateDate());

        db.insert(TABLE_DECK_DETAIL, null, values);

        Log.i(TAG, "addDeck: " + deckName);
        db.close();

    }

    //Delete deck from DB
    public boolean deleteDeck(int deckID) {

        boolean value;
        SQLiteDatabase db = getWritableDatabase();

        value = db.delete(TABLE_DECK_DETAIL, DECK_ID + "=" + deckID, null) > 0;

        Log.i(TAG, "deleteDeck: " + deckID);
        db.close();

        return value;

    }

    //Get decks
    public ArrayList<Deck> getDecks(int courseID){
        ArrayList<Deck> deckList = new ArrayList<Deck>();
        String selectQuery = "SELECT * FROM " + TABLE_DECK_DETAIL + " WHERE " + COURSE_ID + "=" + courseID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        //loop through all rows to return
        while(!cursor.isAfterLast()){

            Deck deck = new Deck(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), Integer.parseInt(cursor.getString(3)));

            Log.i(TAG, "Get Deck: " + deck.getDeckName() + " DeckID: " + deck.get_deckId() + " CourseID: " + deck.getCourseId());
            cursor.moveToNext();
        }

        Log.i(TAG, "Return All Decks");
        db.close();

        return deckList;
    }

    public String getCreateDate () {
        dbDateFormat = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
        currentDate = new Date();

        return dbDateFormat.format(currentDate);
    }
}
