package com.example.terin.asu_flashcardapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

/**
 * The "main" activity of all the create options. Because
 * the fragments cannot "talk" directly to one another, we
 * must implement this sort of interface that allows communication
 * between the different fragments. It contains two fragments
 * which will execute the appropriate activity screen(s) when executed.
 * Created by Stephanie on 5/31/17.
 */

public class CreateItems extends AppCompatActivity implements TitleCreateFragment.TitleCreateListener{

    /**
     * The activity that is set when this file is executed.
     * @param savedInstanceState The activity to be set.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

    }

    /**
     * This is called by the bottom fragment, TitleCreateFragment,
     * when the create button is clicked.
     * @param titleString The title created.
     */
    @Override
    public void createPreview(String titleString){
        //Adding code to control the fragment(s)
        FragmentManager fragManage = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragManage.beginTransaction();
        PreviewFragment previewFrag = new PreviewFragment();
        TitleCreateFragment titleFrag = new TitleCreateFragment();

        //Bundle bundle = new Bundle();
        //The following ought to be "Old T." = tempTitleInput & "New T" = title
        ////bundle.putString("Old Text", "New Text");
        //previewFrag.setArguments(bundle);


        createCourse(titleString);
        previewFrag.setPreviewText(titleString);
        fragTrans.add(R.id.fragment1, previewFrag).commit();
        //fragTrans.add(R.id.fragment2, titleFrag).commit();

    }

    /**
     * Method to maintain a unified menu across all screens.
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
     * @param item The item being selected.
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        return true;
    }


    /**
     * Copy and pasted from AddCourse.java, as this class will replace
     * that one for creation of courses, etc.
     * @param courseName the title of the course the user created.
     */
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
