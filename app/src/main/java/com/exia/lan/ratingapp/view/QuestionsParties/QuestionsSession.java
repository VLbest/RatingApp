package com.exia.lan.ratingapp.view.QuestionsParties;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.utils.DB;
import com.exia.lan.ratingapp.utils.I_ProsgressIndicator;
import com.exia.lan.ratingapp.utils.ProgressIndicator;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_QuestionsDBHandler;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_EndQuestion;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_NormalQuestion;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_Question;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_QuestionFragment;

import java.util.ArrayList;
import java.util.List;

public class QuestionsSession extends FragmentPagerAdapter{

    private List<I_Question> questions;
    private I_QuestionsDBHandler DataBase;
    private QuestionsActivity myActivity;


    public QuestionsSession(FragmentManager fm, Context ctx) {
        super(fm);
        this.DataBase = DB.getDBHandler();
        this.questions = new ArrayList<>();
        this.prepareQuestions();
    }
    // TODO Dynamically save questions to db
    private void prepareQuestions() {
        List<I_NormalQuestion> questions = DataBase.getQuestions();
        this.questions.addAll(questions);

        I_EndQuestion endQuestion = new EndQuestion();
        endQuestion.setText("Terminer");
        this.questions.add(endQuestion);
    }


    @Override
    public Fragment getItem(int position) {

        I_QuestionFragment fragment;
        I_Question question = this.questions.get(position);

        if (question instanceof I_EndQuestion){
            fragment = new EndQuestionFragment();
        }else{
            fragment = new NormalQFragment();
        }
        fragment.setQuestion(question);
        fragment.setSession(this);
        return (Fragment)fragment;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    public void updateQuestion(I_NormalQuestion question) {
        if (!question.isReadOnly()){
            this.DataBase.updateQuestionStatByText(question.getText(), question.getRating());
        }
        question.setReadOnly(true);
    }

    public void endSession() {
        this.myActivity.endQuestionning();

    }

    public void setMyActivity(QuestionsActivity myActivity) {
        this.myActivity = myActivity;
    }

    public void autoGoToNext(){
        this.myActivity.Click_right_frame();
    }
}
