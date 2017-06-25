package com.example.terin.asu_flashcardapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Stephanie on 5/21/17.
 * FOR THE MOMENT THIS IS A PLACE HOLDER FOR THE OPTIONS WINDOW.
 */

public class StudyWindow extends AppCompatActivity{


    public void onCreate(Bundle savedInstanceState){

        //Check the if running Android 5.0 or higher i.e Lollipop or other, used for Materials design.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //Material API's called.

        }else{
            //Implement this feature with out Material design.
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
    }

    /**
     * Need to add in the code for the swiping action, and
     * create the actual index card images.
     */

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
}


