package com.exia.lan.ratingapp.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageButton;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.controller.AbstractQuestion;
import com.exia.lan.ratingapp.controller.Question;
import com.exia.lan.ratingapp.model.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public class CollectionPageAdaper extends FragmentPagerAdapter{

    List<Question> questions;
    QuestionModel Model;

    public CollectionPageAdaper(FragmentManager fm, Context ctx) {
        super(fm);
        this.Model = new QuestionModel();
        this.questions = Model.getAllQuestions();
    }

    @Override
    public Fragment getItem(int position) {

        if (this.questions.size()-1 == position){
            return new EndQuestionFragment();
        }else{
            QuestionFragment fragment = new QuestionFragment();
            fragment.setQuestion(this.questions.get(position));
            return fragment;
        }
    }

    @Override
    public int getCount() {
        return questions.size();
    }

}
