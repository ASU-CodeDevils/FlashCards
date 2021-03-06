package com.example.terin.asu_flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Stephanie on 8/13/17.
 */

public class DeckList extends AppCompatActivity implements DeckListDefaultFrag.ChangeFragListener, DeckCheckListFrag.ChangeFragListener{

    String TAG = "StephanieTesting";
    Deck course = Deck.getDeckInstance();
    public int courseID = course.getCourseId();
    public int deckID = courseID;
    public int createType = 0;

    Intent switchThings = new Intent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decks);

        final Button createButton = (Button) findViewById(R.id.createButton);
        Button studyButton = (Button) findViewById(R.id.studyButton);

        System.out.println("DeckList HERE IS COURSE ID: " + courseID);
        System.out.println("DeckList HERE IS DeckID: " + deckID);


        createButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        //THIS WILL NEED TO GO TO THE ADDCARD.JAVA CLASS
                        //Intent switchThings = new Intent(DeckList.this, CreateItems.class);
                        createType = CreateItems.getCreateType();
                        switch(createType) {
                            case 1:
                                //Create a deck
                                switchThings = new Intent(DeckList.this, AddDeck.class);
                                startActivity(switchThings);
                                break;
                            case 2:
                                //create a card in deck ____
                                switchThings = new Intent(DeckList.this, CreateItems.class);
                                startActivity(switchThings);
                                break;
                            default:
                                Intent deckPicked = new Intent(DeckList.this, StudyWindow.class);
                                startActivity(deckPicked);
                        }
                    }
                }
        );

        studyButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        //This is where SnackBar confirm ought to happen.  createType = course.get_deckId();

                        Intent newWindow = new Intent(DeckList.this, StudyWindow.class);
                        startActivity(newWindow);
                        createType = CreateItems.getCreateType();
                        System.out.println("onCreate Study button Decklist: " + createType);
                    }
                }
        );

        DBHandler myDb = new DBHandler(this);
        /**
         * TODO:
         * The following int value in getDecks() needs to be the number that corresponds with
         * the selected deck's position in the course list array list.
         */
        ArrayList<Deck> decks = myDb.getDecks(courseID);

        System.out.println("DeckList DECKS SIZE: " + decks.size());

        //courseTest.get(courseIDTest);
        String[] decksString = new String[decks.size()];
        //Log.i(TAG, "Array Size: " + String.valueOf(decksString.length));
//        Log.i(TAG, "ArrayList Size: " + String.valueOf(courses.size()));
        //ArrayList<String> coursesString = new ArrayList<>();

        for(int i = 0; i < decksString.length; i++) {
            decksString[i] = decks.get(i).getDeckName().toString();
            //Log.i(TAG, "HERE WE ARE DeckList DeckName: " + decksString[i]);
        }

        ListAdapter deckAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, decksString);
        ListView decksListView = (ListView) findViewById(R.id.decksListView);
        decksListView.setAdapter (deckAdapter);

        decksListView.setOnItemClickListener (
                new AdapterView.OnItemClickListener () {

                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                        //String course = String.valueOf(parent.getItemAtPosition(position));

                        createType = CreateItems.getCreateType();
                        deckID = position;
                        System.out.println("deckListView Deck list createType: " + createType);
                        switch (createType) {
                            case 1:
                                //Create a deck
                                switchThings = new Intent(DeckList.this, AddDeck.class);
                                startActivity(switchThings);
                                break;
                            case 2:
                                //create a card in deck ____
                                switchThings = new Intent(DeckList.this, CreateItems.class);
                                startActivity(switchThings);
                                break;
                            default:
                                Intent deckPicked = new Intent(DeckList.this, StudyWindow.class);
                                startActivity(deckPicked);
                        }
                    }
                }
        );
    }


    /**
     * This method will adjust the fragment from a plain list to a checkbox
     * list, so the user may study multiple decks to study from.
     * @param id
     */
    public void changeFragment(int id){
        FragmentManager fragManage = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragManage.beginTransaction();
        Fragment fragment3 = new Fragment();

        //The following if's work a bit like a switch statement.
        if(id == 1){
            fragment3 = new DeckListDefaultFrag();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.coursesListView, fragment3);
            ft.commit();
        }
        else if(id == 2){
            //fragment3 = new DeckCheckListFrag();
            fragment3 = new DeckCheckListFrag();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.deckschecked, fragment3);
            ft.commit();
        }

    }

    /**
     * Method to maintain a unified menu across all screens.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Method for functionality of button in the menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
