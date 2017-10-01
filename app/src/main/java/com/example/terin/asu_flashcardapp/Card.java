package com.example.terin.asu_flashcardapp;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Class that represents a flashcard to answer.
 *
 * Created by Terin on 5/14/2017.
 */

public class Card {

    private int _cardId;
    private String cardQuestion;
    private String correct;
    private int correctCount, totalCount, deckId;
    private int authorId;
    private Date createDate;
    private ArrayList<Wrong> wrongs;

    public Card ( ) {

    }

    public Card(String cardQuestion, int deckId) {
        this.cardQuestion = cardQuestion;
        this.deckId = deckId;
    }

    public Card(String cardQuestion, String correct, Wrong[] wrongs, int deckId, int authorId){

        this.cardQuestion = cardQuestion;
        this.correct = correct;
        this.deckId = deckId;
        this.authorId = authorId;

        for(int i = 0 ; i < wrongs.length || wrongs[i]==null; i++){
            this.wrongs.add(wrongs[i]);
        }

    }

    //TODO: generates a random set of wrong answers
    public ArrayList<Wrong> generateWrongAnswers() {

        return wrongs;

    }

    public void incrementCorrectCount() {
        //will probably want to do other things here like updating stats or db info
        correctCount++;
    }

    public void incrementTotalCount() {

        //will probably want to do other things here like updating stats or db info
        totalCount++;

    }

    public void swipe(int swipeDirection) {

        //TODO: implement switch to update stats on swipe direction

    }

    //TODO: generate user card stats
    //public UserStats viewDetail (){

        //return UserStats;

    //}

    public String getCardQuestion() {
        return cardQuestion;
    }

    public void setCardQuestion(String cardQuestion) {
        this.cardQuestion = cardQuestion;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int get_cardId() {
        return _cardId;
    }

    public void set_cardId(int _cardId) {
        this._cardId = _cardId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setCreateDate(Date createDate) {this.createDate = createDate;}
}
