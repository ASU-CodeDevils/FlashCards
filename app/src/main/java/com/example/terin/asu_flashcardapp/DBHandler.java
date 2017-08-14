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
    private static final String TABLE_CARD_DETAIL = "card";
    private static final String TABLE_WRONG_DETAIL = "wrongs";

    //Column names
    private static final String COURSE_ID = "_courseId";
    private static final String COURSE_NAME = "courseName";
    private static final String AUTHOR_ID = "authorId";
    private static final String CREATE_DATE = "createDate";
    private static final String DECK_ID = "_deckId";
    private static final String DECK_NAME = "deckName";
    private static DateFormat dbDateFormat;
    private static Date currentDate;
    private static final String CARD_ID = "_cardId";
    private static final String CARD_QUESTION = "cardQuestion";
    private static final String CORRECT = "correct";
    private static final String CORRECT_COUNT = "correctCount";
    private static final String TOTAL_COUNT = "cardQuestion";
    private static final String WRONG_ID = "_wrongId";
    private static final String WRONG = "wrong";

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

        String CREATE_CARD_DETAIL_TABLE = "CREATE TABLE " + TABLE_CARD_DETAIL + "("
                + CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DECK_ID + " INTEGER, "
                + CARD_QUESTION + " TEXT, "
                + CORRECT + " TEXT, "
                + CORRECT_COUNT + " INTEGER, "
                + TOTAL_COUNT + " INTEGER, "
                + AUTHOR_ID + " INTEGER, "
                + CREATE_DATE + " DATE, "
                + "FOREIGN KEY(" + DECK_ID + ") REFERENCES " + TABLE_DECK_DETAIL + "(" + DECK_ID + "));";

        db.execSQL(CREATE_CARD_DETAIL_TABLE);

        String CREATE_WRONG_DETAIL_TABLE = "CREATE TABLE " + TABLE_WRONG_DETAIL + "("
                + WRONG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CARD_ID + " INTEGER, "
                + WRONG + " TEXT, "
                + "FOREIGN KEY(" + CARD_ID + ") REFERENCES " + TABLE_CARD_DETAIL + "(" + CARD_ID + "));";

        db.execSQL(CREATE_CARD_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DECK_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD_DETAIL);

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

    // Antonio Added for CourseList courses collection view
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

    //Add card to DB
    public void addCard(String cardQuestion, String correct, int deckId) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CARD_QUESTION, cardQuestion);
        values.put(CORRECT, correct);
        values.put(CORRECT_COUNT, 0);
        values.put(TOTAL_COUNT, 0);
        values.put(AUTHOR_ID, 1);
        values.put(CREATE_DATE, 05/20/2017);
        values.put(DECK_ID, deckId);

        db.insert(TABLE_COURSE_DETAIL, null, values);

        Log.d(TAG, "addCard: " + cardQuestion);
        db.close();


        //Testing adding Cards
        /*addDeck("Deck1",1);
        addCard("Why is the sky blue?", "Because molecules in the air scatter blue light from the sun more than they scatter red light.", 1);
        addCard("What is the maximum size of a Java int?", "2,147,483,647", 1);
        addCard("How many bits in a byte?", "8", 1);
        addCard("What is the result of 17%5?", "2", 1);*/
    }

    //Delete card from DB
    public boolean deleteCard(int cardID) {
        boolean isCardRemoved;
        SQLiteDatabase db = getWritableDatabase();

        // return from delete is number of rows deleted
        isCardRemoved = db.delete(TABLE_CARD_DETAIL, CARD_ID + "=" + cardID, null) > 0;
        Log.d(TAG, "deleteCard: " + cardID);
        db.close();
        return isCardRemoved;
    }

    //Get cards by deck id
    public ArrayList<Card> getCards(int deckID){
        ArrayList<Card> cardList = new ArrayList<Card>();
        String selectQuery = "SELECT * FROM " + TABLE_CARD_DETAIL + " WHERE " + DECK_ID + " = " + deckID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        //loop through all rows to return
        while(!cursor.isAfterLast()){
            /*
        String CREATE_CARD_DETAIL_TABLE = "CREATE TABLE " + TABLE_CARD_DETAIL + "("
            // below is column indexes.
             0   + CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
             1   + DECK_ID + " INTEGER, "
             2   + CARD_QUESTION + " TEXT, "
             3   + CORRECT + " TEXT, "
             4   + CORRECT_COUNT + " INTEGER, "
             5   + TOTAL_COUNT + " INTEGER, "
             6   + AUTHOR_ID + " INTEGER, "
             7   + CREATE_DATE + " DATE, "
                + "FOREIGN KEY(" + DECK_ID + ") REFERENCES " + TABLE_DECK_DETAIL + "(" + DECK_ID + "));";
            */
            Wrong wrongs[] = new Wrong[10]; // need to populate after making getWrongs method

            //Card(String cardQuestion, String correct, Wrong[] wrongs, int deckId, int authorId){
            Card card = new Card(cursor.getString(2), cursor.getString(3), wrongs, Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(6)));
            card.setCorrectCount(Integer.parseInt(cursor.getString(4)));
            card.setTotalCount(Integer.parseInt(cursor.getString(5)));
            card.set_cardId(Integer.parseInt(cursor.getString(0)));
            //Date c = new Date(cursor.getLong(7));
            //card.setCreateDate(c);
            Log.d(TAG, "Get Card: " + card.getCardQuestion() + " CardID: " + card.get_cardId() + " DeckID: " + card.getDeckId());
            cursor.moveToNext();
        }

        Log.d(TAG, "Return All Cards in Deck");
        db.close();

        return cardList;
    }
}
