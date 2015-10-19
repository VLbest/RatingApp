package com.exia.lan.ratingapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.controller.AbstractQuestion;
import com.exia.lan.ratingapp.controller.Question;

public class QuestionFragment extends Fragment implements View.OnClickListener{

    private Question question;
    private ImageButton veryHappyBtn;
    private ImageButton happyBtn;
    private ImageButton sadBtn;
    private ImageButton verySadBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.question_fragment, container, false);

        TextView quest = (TextView)view.findViewById(R.id.quest_text);
        quest.setText(question.getQuestion());

        this.initComponents(view);

        return view;
    }

    private void initComponents(View view) {
        this.veryHappyBtn = (ImageButton)view.findViewById(R.id.very_happy);
        this.happyBtn = (ImageButton)view.findViewById(R.id.happy);
        this.sadBtn= (ImageButton)view.findViewById(R.id.sad);
        this.verySadBtn = (ImageButton)view.findViewById(R.id.very_sad);


        this.veryHappyBtn.setOnClickListener(this);
        this.happyBtn.setOnClickListener(this);
        this.sadBtn.setOnClickListener(this);
        this.verySadBtn.setOnClickListener(this);
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.very_happy:
                this.question.setRating_A(this.question.getRating_A()+1) ;
                break;
            case R.id.happy:
                this.question.setRating_B(this.question.getRating_B() + 1) ;
                break;
            case R.id.sad:
                this.question.setRating_C(this.question.getRating_C() + 1) ;
                break;
            case R.id.very_sad:
                this.question.setRating_D(this.question.getRating_D() + 1) ;
                break;
            default:

                break;
        }
    }
}