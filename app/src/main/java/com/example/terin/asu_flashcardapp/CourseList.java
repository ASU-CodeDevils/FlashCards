package com.example.terin.asu_flashcardapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Stephanie on 5/21/17.
 * Updated by Cyper on 7/10/2017.
 *
 * Displays the list of courses that the user has created. - AC
 */

public class CourseList extends AppCompatActivity {

    String TAG = "AntonioTesting";
    Deck deck = Deck.getDeckInstance();
    public int courseNum = 0;
    public int createType = 0;

    String TAG2 = "StephanieTesting";
    private static CourseList courseListInstance = null;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        DBHandler myDb = new DBHandler(this);
        ArrayList<Course> courses = myDb.getCourses();

        String[] coursesString = new String[courses.size()];
        //Log.i(TAG, "Array Size: " + String.valueOf(coursesString.length));
        //Log.i(TAG, "ArrayList Size: " + String.valueOf(courses.size()));
        //ArrayList<String> coursesString = new ArrayList<>();

        for (int i = 0; i < coursesString.length; i++) {
            coursesString[i] = courses.get(i).getCourseName().toString();
            //Log.i(TAG, coursesString[i]);
        }

        ListAdapter courseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coursesString);
        ListView coursesListView = (ListView) findViewById(R.id.coursesListView);
        coursesListView.setAdapter(courseAdapter);

        /*
        nested method
         */

        coursesListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                        //String course = String.valueOf(parent.getItemAtPosition(position));

                        /**
                         * The following sets the courseID number, which then pull all decks associated
                         * with that CourseID number.
                         */
                        deck.setCourseId(position);
                        courseNum = deck.getCourseId();
                        createType = CreateItems.getCreateType();
                        System.out.println("CourseList HERE IS COURSE ID: " + Integer.toString(courseNum));
                        System.out.println("CourseList CREATE TYPE TEST: " + createType);

                        //if creating a deck
                        if(createType == 1){
                            Intent coursePicked = new Intent(CourseList.this, CreateItems.class);
                            startActivity(coursePicked);
                        }
                        //if creating a card
                        else if(createType == 2){
                            Intent coursePicked = new Intent(CourseList.this, CreateItems.class);
                            startActivity(coursePicked);
                        }
                        //if creating a course
                        else if(createType == 3){
                            Intent coursePicked = new Intent(CourseList.this, CreateItems.class);
                            startActivity(coursePicked);
                        }
                        //otherwise you must want to study!
                        else{
                            Intent coursePicked = new Intent(CourseList.this, DeckList.class);
                            startActivity(coursePicked);
                            createType = 0;
                        }
                    }
                }
        );
    }

    /**
     * Method to maintain a unified menu across all screens.
     *
     * @param menu The menu being used.
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Method for functionality of buttons in the menu.
     *
     * @param item The item being selected.
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return true;
    }

    /**
     * Method that allows other classes to access the CourseList.
     * @param num The integer value passed to method.
     * @return num The integer value returned from the CourseList.
     */
    public int getCourseNum(int num) {

        return num;
    }

    protected CourseList(){
        /*
        PlaceHolder for Singleton Design.
         */
    }

    /**
     * Method to execute the Singleton Design. Will allow other classes
     * within the same package to use this to create instances of
     * CourseList.
     * @return An instance of CourseList.
     */
    public static CourseList getCourseListInstance() {

        if(courseListInstance == null) {
            courseListInstance = new CourseList();
        }

        return courseListInstance;
    }
}



