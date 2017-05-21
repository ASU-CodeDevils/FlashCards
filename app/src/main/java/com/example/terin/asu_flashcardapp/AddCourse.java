package com.example.terin.asu_flashcardapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AddCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Layout
        RelativeLayout layout = new RelativeLayout(this);
        layout.setBackgroundColor(Color.rgb(00,66,99));

        //Widget Info
        Button button = new Button(this);
        button.setId(1);
        button.setText("Add Course");


        TextView textView = new TextView(this);
        textView.setId(2);
        textView.setTextColor(Color.WHITE);
        textView.setText("Enter Course Name");
        final EditText editText = new EditText(this);
        editText.setId(3);
        button.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        createCourse(editText.getText().toString());
                    }
                }
        );

        RelativeLayout.LayoutParams buttonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams textViewDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams editTextDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        textViewDetails.addRule(RelativeLayout.ABOVE,editText.getId());
        textViewDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textViewDetails.setMargins(0,0,0,50);
        editTextDetails.addRule(RelativeLayout.ABOVE,button.getId());
        editTextDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        editTextDetails.setMargins(0,0,0,50);
        buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonDetails.addRule(RelativeLayout.CENTER_VERTICAL);


        layout.addView(textView, textViewDetails);
        layout.addView(editText, editTextDetails);
        layout.addView(button, buttonDetails);

        setContentView(layout);



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

        Course course = new Course(courseName,1);

        DBHandler db = new DBHandler(this);
        db.addCourse(course);

        //This will probably close back to the Course List Screen.

        ArrayList<Course> courses = db.getCourses();

        for(int i = 0 ; i < courses.size() ; i++){
            System.out.println("Course Name: " + courses.get(i).getCourseName());
        }

    }

}
