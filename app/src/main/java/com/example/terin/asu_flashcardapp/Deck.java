package com.example.terin.asu_flashcardapp;

import java.sql.Date;
import java.util.ArrayDeque;

/**
 * Class that represents a Deck that can hold flashcards, deals, shuffles and resets.
 *
 * Created by Terin on 5/14/2017.
 */

public class Deck {

    private int _deckId;
    private int courseId;
    private String deckName;
    private int authorId;
    private Date createDate;

    private ArrayDeque <Card> cards;

    public Deck(int deckID, int courseId, String deckName, int authorId){

        this._deckId = deckID;
        this.deckName = deckName;
        this.courseId = courseId;
        this.authorId = authorId;

    }

    //TODO: add a new card
    public void addCard(String cardQuestion, String correct, Wrong[] wrongs, int authorId){

    }

    //TODO: delete selected card
    public void deleteCard(int cardId){

    }

    //TODO: generates next card out of deck
    public Card getNextCard() {


        return cards.getFirst();

    }

    //TODO: shuffle deck
    public void shuffle(int shuffleType) {

    }

    //TODO: reset the deck
    public void reset() {

    }

    //TODO: generate user deck stats
    //public UserStats viewDetail (){

        //return UserStats;

    //}

    public int get_deckId() {
        return _deckId;
    }

    public void set_deckId(int _deckId) {
        this._deckId = _deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    //TODO: create date is set from info passed from DB entry
    public void setCreateDate(Date createDate) {

    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
