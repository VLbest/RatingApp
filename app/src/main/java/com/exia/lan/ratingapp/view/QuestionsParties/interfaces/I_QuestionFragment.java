package com.exia.lan.ratingapp.view.QuestionsParties.interfaces;

import com.exia.lan.ratingapp.view.QuestionsParties.QuestionsSession;

public interface I_QuestionFragment {

    void setQuestion(I_Question question);
    I_Question getQuestion();

    void setSession(QuestionsSession session);

}
