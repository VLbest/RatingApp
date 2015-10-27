package com.exia.lan.ratingapp.view.QuestionsParties.interfaces;

public interface I_QuestionStatist {

    void set_id(int _id);
    void setText(String text);

    void setA_count(int a_count);
    void setB_count(int b_count);
    void setC_count(int c_count);
    void setD_count(int d_count);

    int get_id();
    String getText();
    int getA_count();
    int getB_count();
    int getC_count();
    int getD_count();



}
