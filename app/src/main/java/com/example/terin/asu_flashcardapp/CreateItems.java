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

    public static int createType = 0;
    Deck course = Deck.getDeckInstance();
    public int courseID = course.getCourseId();
    Deck deck = Deck.getDeckInstance();
    public int deckID = deck.get_deckId();
    public int creationTypeFromOpts = 0;

    /**
     * The activity that is set when this file is executed.
     * @param savedInstanceState The activity to be set.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        CreateItems item = new CreateItems();
        creationTypeFromOpts = Integer.valueOf(item.getCreateType());
        System.out.println("HERE WE ARE Create Items type: " + deck.getCourseId());
        System.out.println("CREATE ITEMS course id on create: " + courseID);
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

        Bundle bundle = new Bundle();
        //The following ought to be "Old T." = tempTitleInput & "New T" = title
        bundle.putString("Old Text", "New Text");
        previewFrag.setArguments(bundle);


        System.out.println("Create Items course ID: " + courseID);
        switch (createType) {
            case 1:
                createDeck(titleString, courseID);
                System.out.println("CREATE ITEMS CASE 1: " + createType);
                break;
            case 2:
                createCard(titleString, deckID);
                System.out.println("CREATE ITEMS CASE 2: " + createType);
                break;
            case 3:
                createCourse(titleString);
                System.out.println("CREATE ITEMS CASE 2: " + createType);
                break;
            default:
                CreateItems.setCreateType(createType);
                System.out.println("Create Items CreateType Default Case: " + createType);
                System.out.println("OR This is an invalid number of createType.");
                break;
        }

        previewFrag.setPreviewText(titleString);
        fragTrans.add(R.id.fragment1, previewFrag).commit();

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
        //this.createType = createType;

        //This will probably close back to the Course List Screen.

        ArrayList<Course> courses = db.getCourses();
        //System.out.println("HERE IS Create Items create Type createDeck method: " + createType);
        createType = 0;

        //for(int i = 0 ; i < courses.size() ; i++){
            //System.out.println("CreateItems Course Name: " + courses.get(i).getCourseName());
        //}

    }

    /**
     * This will allow decks to be added to the db.
     * @param deckName The title of the deck the user created.
     */
    private void createDeck(String deckName, int courseID){

        DBHandler db = new DBHandler(this);
        db.addDeck(deckName, courseID);
        ArrayList<Deck> decks = db.getDecks(courseID);
        createType = 0;

        System.out.println("HERE IS Create Items course ID createDeck method: " + courseID);
        System.out.println("HERE IS Create Items create Type createDeck method: " + createType);
        //for(int i = 0 ; i < decks.size() ; i++){
        //    System.out.println("Create Items Deck Name: " + decks.get(i).getDeckName());
        //}

    }

    /**
     * This will allow cards to be added to the db.
     * @param cardName The title of the card the user created.
     */
    private void createCard(String cardName, int courseID){

        DBHandler db = new DBHandler(this);
        db.addCard(cardName, courseID);

        ArrayList<Card> cards = db.getCards(courseID);
        createType = 0;

        for(int i = 0 ; i < cards.size() ; i++){
            System.out.println("Card Name: " + cards.get(i).getCardQuestion());
        }
    }

    public static int getCreateType(){
        return createType;
    }

    public static void setCreateType(int buttonNum) {
        createType = buttonNum;
    }


    //TODO: Pause the thread to allow the change in text to be seen!
    /*
    Android
    executes in a single thread, so pausing this thread should allow the user to
    actually see their text on the flashcard image.
     */


}
