package com.example.terin.asu_flashcardapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Terin on 5/14/2017.
 */

//TODO: create UserStats class.
public class UserStats extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState){

        //Check the if running Android 5.0 or higher i.e Lollipop or other, used for Materials design.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //Material API's called.

        }else{
            //Implement this feature with out Material design.
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stats);

    }

    /**
     * Will need to display the statistics of wrong vs. right vs. unknown
     * answers. Also, the activity_user_stats screen will need to hold
     * trophies on a shelf. We imagine a trophy room would display, with
     * trophies that could be clicked on and that are linked to different
     * stats, such as 100% correct, 95% correct, etc.
     */
}
