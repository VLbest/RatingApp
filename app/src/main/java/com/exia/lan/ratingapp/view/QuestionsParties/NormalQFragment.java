package com.exia.lan.ratingapp.view.QuestionsParties;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_NormalQFragment;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_NormalQuestion;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_Question;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NormalQFragment extends Fragment implements I_NormalQFragment{

    @InjectView(R.id.quest_text)
    TextView questText;
    @InjectView(R.id.very_happy)
    ImageButton veryHappy;
    @InjectView(R.id.happy)
    ImageButton happy;
    @InjectView(R.id.sad)
    ImageButton sad;
    @InjectView(R.id.very_sad)
    ImageButton verySad;

    private I_NormalQuestion question;
    private QuestionsSession session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.question_fragment, container, false);
        ButterKnife.inject(this, view);

        questText.setText(question.getText());

        return view;
    }

    @Override
    public void setQuestion(I_Question question) {
        this.question = (I_NormalQuestion)question;
    }

    @Override
    public I_NormalQuestion getQuestion() {
        return this.question;
    }


    @OnClick(R.id.very_happy)
    public void Click_very_happy(){
        this.question.setRating_A();
        this.session.updateQuestion(this.question);
    }

    @OnClick(R.id.happy)
    public void Click_happy(){
        this.question.setRating_B();
        this.session.updateQuestion(this.question);
    }

    @OnClick(R.id.sad)
    public void Click_sad(){
        this.question.setRating_C();
        this.session.updateQuestion(this.question);
    }

    @OnClick(R.id.very_sad)
    public void Click_very_sad(){
        this.question.setRating_D();
        this.session.updateQuestion(this.question);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void setSession(QuestionsSession session) {
        this.session = session;
    }
}