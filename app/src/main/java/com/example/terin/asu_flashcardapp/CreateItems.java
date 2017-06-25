package com.example.terin.asu_flashcardapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.*;

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
     * This is executed when the user clicks the button on the create
     * screen.
     * @param titleString The name of the card, course, or deck being created.
     */
    @Override
    public void createPreview(String titleString) {
        PreviewFragment previewFragment = (PreviewFragment) getSupportFragmentManager().
                findFragmentById(R.id.fragment1);
        previewFragment.setPreviewText(titleString);
    }


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
