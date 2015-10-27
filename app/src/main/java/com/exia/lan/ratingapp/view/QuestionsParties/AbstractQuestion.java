package com.exia.lan.ratingapp.view.QuestionsParties;

import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_Question;

public abstract class AbstractQuestion implements I_Question{

    protected String text;

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

}
