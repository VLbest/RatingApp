package com.exia.lan.ratingapp.view.QuestionsParties.interfaces;

public interface I_NormalQuestion extends I_Question{

    int get_id();
    void set_id(int _id);

    void setRating_A();
    void setRating_B();
    void setRating_C();
    void setRating_D();
    char getRating();

    boolean isReadOnly();
    void setReadOnly(boolean readOnly);

}
