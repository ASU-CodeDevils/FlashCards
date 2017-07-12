package com.example.terin.asu_flashcardapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;

public class AddCourse extends AppCompatActivity {

    //private static EditText editText;
    private static TextView textView;
    private static EditText editText;
    private static Intent startOptionsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        Button courseAcceptButton = (Button) findViewById(R.id.courseAcceptButton);
        editText = (EditText) findViewById(R.id.newCourseName);
        textView = (TextView) findViewById(R.id.prevCourseName);

        courseAcceptButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view){
                        createCourse(editText.getText().toString());
                        startOptionsActivity = new Intent(getApplicationContext(), Options.class);
                        startActivity(startOptionsActivity);
                    }
                }
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        return true;
    }

    private void createCourse(String courseName){

        DBHandler db = new DBHandler(this);
        db.addCourse(courseName);

        //This will probably close back to the Course List Screen.

        ArrayList<Course> courses = db.getCourses();

        for(int i = 0 ; i < courses.size() ; i++){
            System.out.println("Course Name: " + courses.get(i).getCourseName());
        }

    }
}
