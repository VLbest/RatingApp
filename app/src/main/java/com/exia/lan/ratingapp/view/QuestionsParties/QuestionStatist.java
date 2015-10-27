package com.exia.lan.ratingapp.view.QuestionsParties;

import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_QuestionStatist;

public class QuestionStatist  implements I_QuestionStatist {

    private int _id;
    private String text;
    private int a_count;
    private int b_count;
    private int c_count;
    private int d_count;


    public void set_id(int _id) {
        this._id = _id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setA_count(int a_count) {
        this.a_count = a_count;
    }

    public void setB_count(int b_count) {
        this.b_count = b_count;
    }

    public void setC_count(int c_count) {
        this.c_count = c_count;
    }

    public void setD_count(int d_count) {
        this.d_count = d_count;
    }

    public int get_id() {
        return _id;
    }

    public String getText() {
        return text;
    }

    public int getA_count() {
        return a_count;
    }

    public int getB_count() {
        return b_count;
    }

    public int getC_count() {
        return c_count;
    }

    public int getD_count() {
        return d_count;
    }
}
