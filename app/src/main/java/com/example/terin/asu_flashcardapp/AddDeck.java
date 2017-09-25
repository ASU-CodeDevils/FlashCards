package com.example.terin.asu_flashcardapp;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
 * Adds a Deck to a course.
 * Created by Stephanie on 9/16/17.
 */

public class AddDeck extends AppCompatActivity {

    private static TextView textView;
    private static EditText editText;
    private static Intent switchThings;

    Deck course = Deck.getDeckInstance();
    public int courseID = course.getCourseId();

    String TAG = "Stephanie Testing: ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Need to alter the preview text in the next activity.
        setContentView(R.layout.activity_create);

        Button deckAcceptButton = (Button) findViewById(R.id.courseAcceptButton);
        editText = (EditText) findViewById(R.id.newCourseName);

        textView = (TextView) findViewById(R.id.prevCourseName);
        //System.out.println("HERE WE ARE COURSE ID: " + courseID);

        deckAcceptButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view){
                        createDeck(editText.getText().toString(), courseID);
                        switchThings = new Intent(AddDeck.this, DeckList.class);
                        startActivity(switchThings);
                    }
                }
        );
    }

    /**
     * Method that sends the string name inputted by the user to the
     * DeckList.
     * @param deckName The string inputted by the user.
     * @param deckNumber The number of the deck from the courseList.
     */
    public void createDeck(String deckName, int deckNumber) {
        System.out.println("HERE IS AddDeck DECKNUMBER: " + deckNumber);

        DBHandler db = new DBHandler(this);
        db.addDeck(deckName, deckNumber);

        ArrayList<Deck> decks = db.getDecks(deckNumber);

        for(int i = 0 ; i < decks.size() ; i++){
            Log.i(TAG, "AddDeck courseID: " + courseID);
            System.out.println("AddDeck Deck Name: " + decks.get(i).getDeckName() + "Course ID: " + courseID);
        }
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
