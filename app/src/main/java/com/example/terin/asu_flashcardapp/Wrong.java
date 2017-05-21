package com.example.terin.asu_flashcardapp;

import java.sql.Date;

/**
 * Class that represents wrong choice answers.
 *
 * Created by Terin on 5/14/2017.
 */

public class Wrong {

    private int _wrongId;
    private String answer;
    private int usedCount, selectedCount;
    private int authorId;
    private Date createDate;
    private int cardId;

    public Wrong(String answer, int cardId, int authorId){

        this.answer = answer;
        this.cardId = cardId;
        this.authorId = authorId;

    }

    public void incrementUsedCount(){

        //may want to do other things here.
        usedCount++;

    }

    public void incrementSelectedCount(){

        //may want to do other things here.
        selectedCount++;

    }

    public int get_wrongId() {
        return _wrongId;
    }

    public void set_wrongId(int _wrongId) {
        this._wrongId = _wrongId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
