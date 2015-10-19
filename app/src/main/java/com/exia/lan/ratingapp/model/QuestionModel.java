package com.exia.lan.ratingapp.model;

import com.exia.lan.ratingapp.controller.AbstractQuestion;
import com.exia.lan.ratingapp.controller.Question;
import com.exia.lan.ratingapp.utils.DB;
import com.exia.lan.ratingapp.utils.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class QuestionModel {



    public List<Question> getAllQuestions(){
        return DB.getDBHandler().getQuestionsData();
    }

}
