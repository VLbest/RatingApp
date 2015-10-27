package com.exia.lan.ratingapp.view.QuestionsParties;

import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_NormalQuestion;

import java.util.Observable;

public class Question extends AbstractQuestion implements I_NormalQuestion{

    private int _id;
    private boolean rating_A;
    private boolean rating_B;
    private boolean rating_C;
    private boolean rating_D;
    private boolean readOnly;

    public Question() {
        this.setAllToZero();
        this.readOnly = false;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setRating_A() {
        this.setAllToZero();
        this.rating_A = true;
    }

    public void setRating_B() {
        this.setAllToZero();
        this.rating_B = true;
    }

    public void setRating_C() {
        this.setAllToZero();
        this.rating_C = true;
    }

    public void setRating_D() {
        this.setAllToZero();
        this.rating_D = true;
    }

    public char getRating(){
        if (this.rating_A)
            return 'A';
        if (this.rating_B)
            return 'B';
        if (this.rating_C)
            return 'C';
        if (this.rating_D)
            return 'D';
        return 'X';
    }

    private void setAllToZero(){
        this.rating_A = false;
        this.rating_B = false;
        this.rating_C = false;
        this.rating_D = false;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
