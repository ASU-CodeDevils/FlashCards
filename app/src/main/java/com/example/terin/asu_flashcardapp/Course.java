package com.example.terin.asu_flashcardapp;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Class that represents a Course that can hold Decks of flashcards.
 *
 * Created by Terin on 5/14/2017.
 */

public class Course {

    private int courseId;
    private String courseName;
    private int authorId;
    private Date createDate;
    private ArrayList <Deck> decks;

    public Course(String courseName, int authorId){

        this.courseName = courseName;
        this.authorId = authorId;

    }

    //TODO: add a new deck
    public void addDeck(String deckName, int authorId){

    }

    //TODO: delete selected deck
    public void deleteDeck(int deckId){

    }

    //TODO: return selected deck
    public Deck openDeck(int deckPosition) {

        return decks.get(deckPosition);

    }

    //TODO: generate user course stats
    //public UserStats viewDetail (){

        //return UserStats;

    //}

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    //TODO: create date is set from info passed from DB entry
    private void setCreateDate(Date createDate) {

    }
}
