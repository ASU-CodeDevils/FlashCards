package com.example.terin.asu_flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
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

public class StudyWindow extends AppCompatActivity{

    String TAG = "AntonioTesting";


    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        DBHandler myDb = new DBHandler(this);

        ArrayList<Course> courses = myDb.getCourses();
        String[] coursesString = new String[courses.size()];
//        Log.i(TAG, "Array Size: " + String.valueOf(coursesString.length));
//        Log.i(TAG, "ArrayList Size: " + String.valueOf(courses.size()));
        //ArrayList<String> coursesString = new ArrayList<>();

        for(int i = 0; i < coursesString.length; i++) {
            coursesString[i] = courses.get(i).getCourseName().toString();
            Log.i(TAG, coursesString[i]);
        }

        ListAdapter courseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coursesString);
        ListView coursesListView = (ListView) findViewById(R.id.coursesListView);
        coursesListView.setAdapter (courseAdapter);

        coursesListView.setOnItemClickListener (
                new AdapterView.OnItemClickListener () {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String course = String.valueOf(parent.getItemAtPosition(position));

                        // This only shows the course that was clicked.
                        //   This needs to open up a list of the decks that are tied to the course selected
                        Toast.makeText(StudyWindow.this, course, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    /**
     * Need to add in the code for the swiping action, and
     * create the actual index card images.
     */
}


