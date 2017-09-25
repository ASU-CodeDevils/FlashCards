package com.example.terin.asu_flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * This class allows the user to choose which item they would like to create.
 * Deck, Course, or card. (if the user chooses Card, then they'll be prompted
 * to choose a deck to create a card for).
 * Created by Stephanie on 8/10/17.
 */

public class CreateOptions extends AppCompatActivity {

    private static EditText tempTextInputField;
    private static Intent switchThings;
    public int buttonNum = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_options);


        final Button deckButton = (Button)findViewById(R.id.deckButton);
        final Button cardButton = (Button)findViewById(R.id.cardButton);
        final Button courseButton = (Button)findViewById(R.id.courseButton);



        //The action listener for deck button.
        deckButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        buttonNum = 1;
                        switchThings = new Intent(CreateOptions.this,
                                CourseList.class);
                                //CreateItems.class);
                        CreateItems.setCreateType(buttonNum);
                        startActivity(switchThings);
                    }
                }
        );

        //The action listener for card button.
        cardButton.setOnClickListener(
                /**
                 * TODO
                 * Needs to prompt user with options for which deck to
                 * create a card for.
                 */
                new Button.OnClickListener(){
                    public void onClick(View v){
                        buttonNum = 2;
                        switchThings = new Intent(CreateOptions.this,
                                DeckList.class);
                                //CreateItems.class);
                        CreateItems.setCreateType(buttonNum);
                        startActivity(switchThings);
                    }
                }
        );

        //The action listener for course button.
        courseButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        buttonNum = 3;
                        switchThings = new Intent(CreateOptions.this,
                                //AddCourse.class);
                                CreateItems.class);
                        CreateItems.setCreateType(buttonNum);
                        startActivity(switchThings);
                    }
                }
        );
        buttonNum = 0;
        System.out.println("HERE WE ARE Create Options type: " + buttonNum);

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

}
