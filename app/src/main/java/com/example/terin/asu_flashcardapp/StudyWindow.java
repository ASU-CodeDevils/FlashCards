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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
    String TAG = "Stephanie Testing S.W.";
    TextView cardInformation;
    Random randCard = new Random();
    DBHandler db = new DBHandler(this);
    Card card = new Card();
    Deck course = Deck.getDeckInstance();
    public int courseID = course.getCourseId();
    public int deckID = courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        ArrayList<Card> cardList = new ArrayList<Card>();
        db.getCards(deckID);
        cardInformation  = (TextView) findViewById(R.id.cardInformation);

        /**TODO:
         * Need to get teh actual string from the deck, and not the address.
         * currently printing the address. i.e. Change the Java string to
         * something compatable with TextView.
         * see: https://stackoverflow.com/questions/12201834/cast-string-to-textview
         */
        int index = randCard.nextInt(cardList.size()+1);
        //System.out.println("HERE'S THE CARD QUESTION @ 0: " + cardList.get(0).getCardQuestion());
        //String cardInfo = cardList.get(index).toString();
        String[] cardInfo = new String[index];
        //card = String.valueOf(cardList.get(index));
        System.out.println("\nHereis card info: " + cardList.get(index); //need to print the string, not address
        //System.out.println("\nHERE'S card.GetCardQuestion: " + card.getCardQuestion());
        displayCardInfo(String.valueOf(cardList.get(index)));

        for(int i = 0; i < cardList.size(); i++) {
            //String temp = cardList.get(i).getCardQuestion();
            String temp = cardList.get(i).getCardQuestion();
            cardInfo[i] = temp;
            //displayCardInfo(Arrays.toString(cardInfo));
            System.out.println("HERE'S TEMP: " + temp);
        }

        /*
        Need to collect the cards, in random order from the selected deck and
        display the information on the lined white flashcard. The textView from
        the StudyWindow Activity is: cardInformation, and the variable from
        the db handler is: cardQuestion.
         */


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

    /**
     * A method that pulls random card information from the user's selected
     * deck and displays that information onto the lined white flash
     * card image of the Study Window.
     * @param cardInfo The information to be displayed.
     */
    public void displayCardInfo(String cardInfo){
        cardInformation.setText(cardInfo);
        //System.out.print("HERE IS THE CARD INFO: " + cardInfo);
        System.out.print("HERE IS THE CARD INFO: " + cardInformation);
    }
}

