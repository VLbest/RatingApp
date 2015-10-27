package com.exia.lan.ratingapp.view.QuestionsParties.interfaces;

import java.util.List;

public interface I_QuestionsDBHandler {

    List<I_NormalQuestion> getQuestions();
    I_NormalQuestion getQuestionByText(String text);
    void insertQuestion(I_NormalQuestion Quest);
    void updateQuestionTextByText(String newText, String oldText);
    void deleteQuestionByText(String text);
    void updateQuestionStatByText(String text, char rating);
    List<I_QuestionStatist> getQuestionsStats();

}
