package com.exia.lan.ratingapp.view.QuestionsParties;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_EndQFragment;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_EndQuestion;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_Question;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EndQuestionFragment extends Fragment implements I_EndQFragment {


    @InjectView(R.id.quest_text)
    TextView questText;
    @InjectView(R.id.end_btn)
    Button endBtn;
    private I_EndQuestion question;
    private QuestionsSession session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.end_question_fragment, container, false);
        ButterKnife.inject(this, view);

        questText.setText(question.getText());

        return view;
    }

    @OnClick(R.id.end_btn)
    public void Click_end_btn(){
        this.session.endSession();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void setQuestion(I_Question question) {
        this.question = (I_EndQuestion) question;
    }

    @Override
    public I_EndQuestion getQuestion() {
        return this.question;
    }

    public void setSession(QuestionsSession session) {
        this.session = session;
    }
}
