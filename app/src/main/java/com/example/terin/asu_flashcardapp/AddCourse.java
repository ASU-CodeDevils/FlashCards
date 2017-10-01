package com.example.terin.asu_flashcardapp;

import android.os.Build;
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

/**
 * Adds a course created by the user to the db.
 */

public class AddCourse extends AppCompatActivity {

    private static TextView textView;
    private static EditText editText;
    private static Intent startOptionsActivity;
    //create a createItems object that can reference the createType number.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

//<<<<<<< Updated upstream
        Button courseAcceptButton = (Button) findViewById(R.id.courseAcceptButton);
        editText = (EditText) findViewById(R.id.newCourseName);
//=======
        //Check the if running Android 5.0 or higher i.e Lollipop or other, used for Materials design.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //Material API's called.

        }else{
            //Implement this feature with out Material design.
        }

        /*

>>>>>>> Stashed changes
*/
        //Button courseAcceptButton = (Button) findViewById(R.id.courseAcceptButton);
        textView = (TextView) findViewById(R.id.prevCourseName);

        courseAcceptButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view){
                        createCourse(editText.getText().toString(), 3);
                        startOptionsActivity = new Intent(getApplicationContext(), Options.class);
                        startActivity(startOptionsActivity);
                    }
                }
        );

    }


    /**
     * This method sends the string inputted by the user to the CourseList.
     * @param courseName The string inputted by the user.
     */
    private void createCourse(String courseName, int createType){

        DBHandler db = new DBHandler(this);
        db.addCourse(courseName);

        //This will probably close back to the Course List Screen.

        ArrayList<Course> courses = db.getCourses();

        for(int i = 0 ; i < courses.size() ; i++){
            System.out.println(" AddCourse Course Name: " + courses.get(i).getCourseName());
        }

    }
}
