package com.example.terin.asu_flashcardapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;



/**
 * Created by Terin on 5/18/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    String TAG = "TerinTest";
    //All Static variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FlashCardDB";

    //Table Names
    private static final String TABLE_COURSE_DETAIL = "course";

    //Course Column names
    private static final String COURSE_ID = "_courseId";
    private static final String COURSE_NAME = "courseName";
    private static final String AUTHOR_ID = "authorId";
    private static final String CREATE_DATE = "createDate";

    public DBHandler (Context context /*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //Create Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_COURSE_DETAIL_TABLE = "CREATE TABLE " + TABLE_COURSE_DETAIL + "("
                + COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + COURSE_NAME + " TEXT "
                + AUTHOR_ID + " INTEGER "
                + CREATE_DATE + " DATE " + ");";

        db.execSQL(CREATE_COURSE_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE_DETAIL);

        //Create Tables again
        onCreate(db);

    }

    //Add course to DB
    public void addCourse(Course course) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSE_NAME, course.getCourseName());
        values.put(AUTHOR_ID, course.getAuthorId());
        values.put(CREATE_DATE, 05/20/2017);

        db.insert(TABLE_COURSE_DETAIL, null, values);

        Log.d(TAG, "addCourse: " + course.getCourseName());
        db.close();

    }

    //Get courses
    public ArrayList<Course> getCourses(){
        ArrayList<Course> courseList = new ArrayList<Course>();
        String selectQuery = "SELECT * FROM " + TABLE_COURSE_DETAIL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        //loop through all rows to return
        while(!cursor.isAfterLast()){

                Course course = new Course(cursor.getString(1),Integer.parseInt(cursor.getString(2)));
                course.set_courseId(Integer.parseInt(cursor.getString(0)));

                Log.d(TAG, "Get Course: " + course.getCourseName() + course.get_courseId());
                cursor.moveToNext();
        }

        Log.d(TAG, "Return All Courses");
        db.close();

        return courseList;
    }
}
