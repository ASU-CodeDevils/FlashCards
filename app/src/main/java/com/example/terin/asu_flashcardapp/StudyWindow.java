package com.example.terin.asu_flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * This is the main study window for the application.
 * Will show a flashcard image with random information displayed.
 * The user will swipe right if they know the card, and left if
 * they do not know the information on the card.
 * (Will also return a count of wrong's to the wrong db table.
 * Created by Stephanie on 8/13/17.
 */

public class StudyWindow extends AppCompatActivity {

    protected int wrongCount = 0;
    String TAG = "Steph S.W. Gesture Test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        Button rightArrow = (Button) findViewById(R.id.rightButton);
        rightArrow.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        wrongCount = wrongCount + 0;
                        Log.i(TAG, String.valueOf(wrongCount));
                    }
                }
        );

        Button wrongArrow = (Button) findViewById(R.id.wrongButton);
        wrongArrow.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        wrongCount = wrongCount + 1;
                        Log.i(TAG, String.valueOf(wrongCount));
                    }
                }
        );

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
}
